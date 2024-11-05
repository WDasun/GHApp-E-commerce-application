/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.CreateInvoice;
import common.SendInvoiceToEmail;
import connection.ConnectionBuilder;
import dto.CartItem;
import dto.CheckoutInfo;
import dto.InvoiceHeadDetails;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.CardDetails;
import model.Customer;
import model.CustomerOrder;
import model.Invoice;
import model.OrderLine;
import model.OrderStatus;
import model.ProductItem;
import model.ShippingType;
import model.ShoppingCartItem;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class CustomerOrderDAO {

    private void Save(CustomerOrder customerOrder) throws Exception {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(customerOrder);
            tr.commit();
            session.flush();
            session.refresh(customerOrder);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }finally{
         
        }
    }

    public void updateOrderStatus(CustomerOrder customerOrder, OrderStatus orderStatus, Session session) throws Exception {
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            String hql = "UPDATE CustomerOrder SET"
                    + " orderStatus = :orderStatus"
                    + " WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("orderStatus", orderStatus);
            query.setParameter("id", customerOrder.getId());
            query.executeUpdate();
            session.flush();
            session.refresh(customerOrder);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public List<CustomerOrder> searchCustomerOrder(Customer customer, int orderId, OrderStatus orderStatus, Session session) {
        List<CustomerOrder> orderList = null;
        Criteria criteria = session.createCriteria(CustomerOrder.class);
        if (orderId != 0) {
            criteria.add(Restrictions.eq("id", orderId));
        }
        if (customer != null) {
            criteria.add(Restrictions.eq("customer", customer));
        }
        if (orderStatus != null) {
            criteria.add(Restrictions.eq("orderStatus", orderStatus));
        }
        criteria.setCacheable(false);
        orderList = (List<CustomerOrder>) criteria.list();
        return orderList;
    }

    public CustomerOrder getCustomerOrderById(int id, Session session) {
        CustomerOrder order = null;
        Criteria criteria = session.createCriteria(CustomerOrder.class);
        criteria.add(Restrictions.eq("id", id));
        order = (CustomerOrder) criteria.uniqueResult();
        return order;
    }

    public String SaveCustomerOrder(CheckoutInfo ci, List<CartItem> itemList, Customer customer) {
        List<OrderLine> orderLineList = null;
        String pdfFilePath = null;
        try {
            if (ci != null && itemList != null && customer != null) {
                //Create and save Customer Order
                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setAddress(new AddressDAO().searchById(ci.getAddressId()));
                customerOrder.setCustomer(customer);
                customerOrder.setOrderStatus(new OrderStatusDAO().searchById(1));
                ShippingType shippingType = new ShippingTypeDAO().searchById(ci.getShippingTypeId());
                customerOrder.setShippingType(shippingType);
                customerOrder.setOrderTotal(CalculateTotal(itemList, shippingType));
                customerOrder.setOrderDate(new Date());
                // 1 mean save the Info
                if (ci.getSaveCardInfo() == 1) {
                    //Create and saving Card info If requested
                    CardDetails cardDetail = new CardDetailsDAO().SearchByCardNumber(ci.getCardNumber());
                    if (cardDetail == null) {
                        CardDetails c = new CardDetails();
                        c.setCreditOrDebitCardType(new CardTypeDAO().searchById(ci.getCardTypeId()));
                        c.setCustomer(customer);
                        c.setCardNumber(ci.getCardNumber());
                        c.setCardName(ci.getNameOnCard());
                        c.setExpYear(Integer.parseInt(ci.getExpYear()));
                        c.setExpMonth(Byte.parseByte(ci.getExpMonth()));
                        c.setIsDefault(false);
                        new CardDetailsDAO().save(c);
                        CardDetails cardDetailNew = new CardDetailsDAO().SearchByCardNumber(ci.getCardNumber());
                        customerOrder.setCardDetails(cardDetailNew);
                    } else {
                        customerOrder.setCardDetails(cardDetail);
                    }
                }
                Save(customerOrder);
                //Create and saving customer orderline
                orderLineList = new ArrayList<>();
                Session session = ConnectionBuilder.hibSession();
                for (CartItem cartItem : itemList) {
                    ProductItem pi = getProductItem(cartItem.getProductItem().getId(), session);
                    if (cartItem.isSelected()) {
                        OrderLine ol = new OrderLine();
                        ol.setProductItem(pi);
                        ol.setCustomerOrder(customerOrder);
                        ol.setQty(cartItem.getQty());
                        double itemPrice = pi.getPrice();
                        double discountRt = 0;
                        if (cartItem.getPromotion() != null) {
                            discountRt = cartItem.getPromotion().getDiscountRate();
                            ol.setPromotion(cartItem.getPromotion());
                        }
                        ol.setPrice((itemPrice - ((itemPrice / 100) * discountRt)) * cartItem.getQty());
                        new OrderLineDAO().Save(ol);
                        //new Order line list
                        orderLineList.add(ol);
                    }
                }
                
                // Create and save invoice
                Invoice invoice = new Invoice();
                invoice.setCustomerOrder(customerOrder);
                String invoiceId = generateInvoiceId(customerOrder.getId(), customer.getCustomerId());
                invoice.setId(invoiceId);
                new InvoiceDAO().Save(invoice);

                //Create Invoice pdf
                String customerFullName = customer.getFname() + " " + customer.getLname();
                InvoiceHeadDetails ihd = new InvoiceHeadDetails();
                ihd.setCompanyAddress("134/A Gampaha Yakkala");
                ihd.setCompanyEmail("Ghapp@gmail.com");
                ihd.setCompanyCnt("03322652423");
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
                ihd.setCurrentDate(sdf.format(date));
                ihd.setInvoiceNumber(invoiceId);
                ihd.setCustomerName(customerFullName);
                ihd.setEmail(customer.getEmail());
                String shippingAddress = customerOrder.getAddress().getAddressLine1() + " " + customerOrder.getAddress().getAddressLine2();
                ihd.setShippingAddress(shippingAddress);
                SimpleDateFormat sdfForOrderedDate = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
                String orderedDate = sdfForOrderedDate.format(customerOrder.getOrderDate());
                ihd.setOrderedDate(orderedDate);
                ihd.setItemTotal(String.valueOf(itemTotal));
                ihd.setDiscountAmount(String.valueOf(discountAmount));
                ihd.setShippingCost(String.valueOf(shippingCost));
                ihd.setTotalPayment(String.valueOf(totalPayment + shippingCost));

                String pdfSavingPath = createInvoicePDF(invoiceId, (ArrayList<OrderLine>) orderLineList, ihd);
                pdfFilePath = pdfSavingPath;
                //Send invoice pdf to customer email
                sendInvoiceEmailToCustomer(pdfSavingPath, customer.getEmail(), customerFullName);

                // Remove Selected items from the DB Cart item
                for (CartItem cartItem : itemList) {
                    if (cartItem.isSelected()) {
                        removeShoppingCartItemDB(cartItem.getShoppingCartItemId());
                        reduceProductItemQTY(cartItem.getProductItem().getId(), cartItem.getQty());
                    }
                }
                session.refresh(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdfFilePath;
    }

    private void sendInvoiceEmailToCustomer(String filePath, String customerEmail, String customerName) {
        new SendInvoiceToEmail(filePath, customerEmail, customerName).Post();
    }

    private String createInvoicePDF(String invoiceId, ArrayList<OrderLine> orderLineList, InvoiceHeadDetails ihd) {
        String savingPath = new CreateInvoice().crate(invoiceId, orderLineList, ihd);
        return savingPath;
    }

    private String generateInvoiceId(int orderId, int customerId) {
        String invoiceId = "";
        invoiceId = "INVODI" + orderId + "CID" + customerId;
        return invoiceId;
    }

    private void reduceProductItemQTY(String productItemId, int reduceAmount) {
        try {
            ProductItem pi = new ItemDAO().searchById(productItemId);
            int cQty = pi.getQty();
            int nQty = cQty - reduceAmount;
            new ItemDAO().updateQty(pi.getId(), nQty);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeShoppingCartItemDB(int cartItemId) {
        System.out.println("removeShoppingCartItemDB called --------- !");
        try {
            System.out.println("Checking Id -------");
            System.out.println("Id : " + cartItemId);
            ShoppingCartItem sci = new ShoppingCartItemDAO().searchById(cartItemId);
            new ShoppingCartItemDAO().delete(sci);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double itemTotal;
    private double discountAmount;
    private double totalPayment;
    private double shippingCost;

    private double CalculateTotal(List<CartItem> itemList, ShippingType shippingType) throws Exception {
        totalPayment = 0;
        shippingCost = shippingType.getPrice();
        Session session = ConnectionBuilder.hibSession();
        for (CartItem cartItem : itemList) {
            if (cartItem.isSelected()) {
                ProductItem pi = getProductItem(cartItem.getProductItem().getId(), session);
                itemTotal += pi.getPrice() * cartItem.getQty();
                double itemPrice = pi.getPrice();
                double discountRt = 0;
                if (cartItem.getPromotion() != null) {
                    discountRt = cartItem.getPromotion().getDiscountRate();
                }
                discountAmount += ((itemPrice / 100) * discountRt) * cartItem.getQty();
                totalPayment += (itemPrice - (itemPrice / 100) * discountRt) * cartItem.getQty();
            }
        }
        return totalPayment + shippingCost;
    }

    private ProductItem getProductItem(String productId, Session session) throws Exception {
        ProductItem productItem = new ItemDAO().searchById(productId, session);
        return productItem;
    }
}
