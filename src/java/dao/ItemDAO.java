/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionBuilder;
import dto.CartItem;
import dto.EncodeImageToBase64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import model.Product;
import model.ProductItem;
import model.Promotion;
import model.VariationOption;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class ItemDAO {

    public List<ProductItem> getProductListWithLimit(int startPoint, int resultCount, Session session) {
        List<ProductItem> itemList = null;
        Criteria criteria = session.createCriteria(ProductItem.class);
        criteria.add(Restrictions.eq("status", true));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(startPoint);
        criteria.setMaxResults(resultCount);
        itemList = criteria.list();
        return itemList;

    }

    public List<ProductItem> searchByIdLetters(String  partialName, Session session) {
        List<ProductItem> itemList = null;
        Criteria criteria = session.createCriteria(ProductItem.class);
        criteria.add(Restrictions.like("name", partialName, MatchMode.START));
        criteria.add(Restrictions.eq("status", true));
        itemList = criteria.list();
        return itemList;

    }

    public void save(ProductItem productItem) throws Exception{
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            tr = session.beginTransaction();
            session.save(productItem);
            tr.commit();
            session.flush();
            session.refresh(productItem);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public long GetRowCount() {
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ProductItem.class);
        criteria.setProjection(org.hibernate.criterion.Projections.rowCount());
        long rowCount = (long) criteria.uniqueResult();

        return rowCount;

    }

    public List<ProductItem> getItemList(Session session) {
        List<ProductItem> productItemList = null;
        Query query = null;
        try {
            query = session.createQuery("FROM ProductItem");
        } catch (Exception e) {
            e.printStackTrace();
        }
        productItemList = query.list();
        return productItemList;
    }

    public Promotion getRelatedPromotion(String productItemId) {
        Promotion relatedPromotion = null;
        Set<Promotion> promotionSet = null;
        ProductItem productItem = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ProductItem.class);
        criteria.add(Restrictions.eq("id", productItemId));
        productItem = (ProductItem) criteria.uniqueResult();
        promotionSet = productItem.getProduct().getProductCategory().getPromotions();

        Date nowDate = new Date();
        for (Promotion promotion : promotionSet) {
            if (promotion.isStatus() && nowDate.after(promotion.getStartDate()) && nowDate.before(promotion.getEndDate())) {
                relatedPromotion = promotion;
                break;
            }
        }

        return relatedPromotion;
    }

    private String createHQLForUpdate(String newPath1, String newPath2, String newPath3) {
        String path = "";
        if (newPath1 != null) {
            path += "itemImgOnePath = :newPath1,";
        }
        if (newPath2 != null) {
            path += "itemImgTwoPath = :newPath2,";
        }
        if (newPath3 != null) {
            path += "itemImgThreePath = :newPath3,";
        }
        String HQL = "UPDATE ProductItem SET "
                + "name = :newName,"
                + "sku = :newSku,"
                + "qty = :newqty,"
                + path
                + "price = :newPrice WHERE id = :itemId";
        return HQL;
    }

    public void Update(String itemId,
            String newName,
            String newSku,
            int newqty,
            double newPrice,
            String newPath1,
            String newPath2,
            String newPath3) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            ProductItem item = searchById(itemId);
            tr = session.beginTransaction();
            String hql = createHQLForUpdate(newPath1, newPath2, newPath3);

            System.out.println(hql);
            Query query = session.createQuery(hql);

            query.setParameter("itemId", itemId);
            query.setParameter("newName", newName);
            query.setParameter("newSku", newSku);
            query.setParameter("newqty", newqty);
            query.setParameter("newPrice", newPrice);

            if (newPath1 != null) {
                query.setParameter("newPath1", newPath1);
            }
            if (newPath2 != null) {
                query.setParameter("newPath2", newPath2);
            }
            if (newPath3 != null) {
                query.setParameter("newPath3", newPath3);
            }

            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(item);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {

        }
    }

    public void updateStatus(boolean value, String id) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            ProductItem item = searchById(id);
            tr = session.beginTransaction();
            String hql = "UPDATE ProductItem SET status = :newValue WHERE id = :itemId";
            Query query = session.createQuery(hql);
            query.setParameter("newValue", value);
            query.setParameter("itemId", id);
            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(item);
            session.refresh(item.getProduct());
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {

        }
    }

    public void updateQty(String id, int qty) {
        Transaction tr = null;
        Session session = ConnectionBuilder.hibSession();
        try {
            ProductItem item = searchById(id);
            tr = session.beginTransaction();
            String hql = "UPDATE ProductItem SET qty = :qty WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("qty", qty);
            query.setParameter("id", id);
            query.executeUpdate();
            tr.commit();
            session.flush();
            session.refresh(item);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {

        }
    }

    public ProductItem searchById(String id) throws Exception {
        ProductItem productItem = null;
        Session session = ConnectionBuilder.hibSession();
        Criteria criteria = session.createCriteria(ProductItem.class);
        criteria.add(Restrictions.eq("id", id));
        productItem = (ProductItem) criteria.uniqueResult();

        if (productItem != null) {
            return productItem;
        } else {
            throw new NullPointerException("Item not found !");
        }
    }

    public ProductItem searchById(String id, Session session) throws Exception {
        ProductItem productItem = null;
        Criteria criteria = session.createCriteria(ProductItem.class);
        criteria.add(Restrictions.eq("id", id));
        productItem = (ProductItem) criteria.uniqueResult();
        if (productItem != null) {
            return productItem;
        } else {
            throw new NullPointerException("Item not found !");
        }
    }

    public String generateCart(List<CartItem> cartItemList) throws Exception {

        EncodeImageToBase64 encodeImage = new EncodeImageToBase64();
        String cartItemRowHTML = "";
        for (CartItem cartItem : cartItemList) {
            Session session = ConnectionBuilder.hibSession();
            String variationOptionsHTML = "";
            ProductItem productItem = null;
            Criteria criteria = session.createCriteria(ProductItem.class);
            criteria.add(Restrictions.eq("id", cartItem.getProductItem().getId()));
            productItem = (ProductItem) criteria.uniqueResult();
            Set<VariationOption> voSet = productItem.getVariationOptions();
            int voCount = 0;
            int voSetSize = voSet.size();
            int voTotalCount = 0;
            for (VariationOption vo : voSet) {

                voTotalCount++;
                if (voCount == 0) {
                    variationOptionsHTML += "<p class=\"m-0\">";
                }
                if (voCount < 3) {
                    if (voCount < 2 && voTotalCount != voSetSize) {
                        variationOptionsHTML += vo.getVariation().getName() + " : " + vo.getValue() + " , ";
                    } else {
                        variationOptionsHTML += vo.getVariation().getName() + " : " + vo.getValue();
                    }
                }
                if (voCount == 3 || voTotalCount == voSetSize) {
                    variationOptionsHTML += "</p>\n";
                    voCount = 0;
                } else {
                    voCount++;
                }

            }
            String itemPriceDiv = "";
            if (cartItem.getPromotion() != null) {
                double discountRt = cartItem.getPromotion().getDiscountRate();
                double itemPrice = productItem.getPrice();
                double discountAmount = (itemPrice / 100) * discountRt;
                double finalItemPrice = itemPrice - discountAmount;
                itemPriceDiv = "<div class='mt-2'>"
                        + "      <p class='text-center fw-bold fs-6 m-0' style='color: brown;'>" + discountRt + "% Discount</p>"
                        + "      <p class='text-center fw-bold fs-6 m-0' style='color: rgb(99, 99, 105);'><del>Rs. " + itemPrice + "</del></p>"
                        + "      <p class='text-center fw-bold fs-6 m-0'>Rs. " + finalItemPrice + "</del></p>"
                        + "     </div>";
            } else {
                itemPriceDiv = "<div class=\"mt-2\">\n"
                        + "<p class=\"text-center fw-bold fs-5 m-0\">Rs. " + cartItem.getProductItem().getPrice() + "</p>\n"
                        + "</div>\n";
            }
            String checkBox = "";
            if (cartItem.isSelected()) {
                checkBox = "<input class=\"form-check-input cbx-itemSelect-cart\" type=\"checkbox\" value=\"" + cartItem.getProductItem().getId() + "\" id=\"flexCheckDefault\" checked>\n";
            } else {
                checkBox = "<input class=\"form-check-input cbx-itemSelect-cart\" type=\"checkbox\" value=\"" + cartItem.getProductItem().getId() + "\" id=\"flexCheckDefault\">\n";
            }
            cartItemRowHTML += ""
                    + " <div class=\"row over div-itemRow-cart m-0\">\n"
                    + " <div class=\"col-1 d-flex align-items-center justify-content-center\">\n"
                    + "<div class=\"form-check\">\n"
                    + checkBox
                    + "</div>\n"
                    + "</div>\n"
                    + "<div class=\"col-6 d-flex p-0\">\n"
                    + "<div class=\"div-imgFrame\">\n"
                    + "<img src=\"" + encodeImage.encodeImage(productItem.getItemImgOnePath()) + "\" class=\"img-item-cart rounded-1\" alt=\"...\">\n"
                    + "</div>\n"
                    + "<div class=\"div-itemDescription-cart\">\n"
                    + "<div class=\"div-itemName ms-2\">\n"
                    + "<p class=\"txt-itemName m-0\">" + productItem.getName() + "</p>\n"
                    + "</div>\n"
                    + "<div class=\"overflow-auto div-itemVariationOptions ms-2\">\n"
                    + variationOptionsHTML
                    + "</div>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "<div class=\"col-3 p-0\">\n"
                    + itemPriceDiv
                    + "<div class=\"d-flex align-items-center justify-content-center mt-4\">\n"
                    + "<button class=\"btn-qty btn btn-primary d-flex align-items-center justify-content-center\" onclick=\"AddItemQty_cart('" + cartItem.getProductItem().getId() + "')\">\n"
                    + "<i class=\"fa-solid fa-plus\"></i>\n"
                    + "</button>\n"
                    + "<p class=\"fw-bold fs-5 m-0 mx-3\" id=\"txt-qty-cartItem-" + productItem.getId() + "-cart\">" + cartItem.getQty() + "</p>\n"
                    + "<button class=\"btn-qty btn btn-danger d-flex align-items-center justify-content-center\" onclick=\"subtractItemQty_cart('" + cartItem.getProductItem().getId() + "')\">\n"
                    + "<i class=\"fa-solid fa-minus\"></i>\n"
                    + "</button>\n"
                    + "</div>\n"
                    + "</div>\n"
                    + "<div class=\"col-2 d-flex align-items-center justify-content-center p-0\">\n"
                    + "<button class=\"btn-removeItem rounded\" onclick=\"onclickTrashBtn_cart('" + productItem.getId() + "')\">\n"
                    + "<i class=\"fa-solid fa-trash fa-xl\"></i>\n"
                    + "</button>\n"
                    + "</div>\n"
                    + "</div>";

        }
        return cartItemRowHTML;
    }

    public String generateItemRowIndex(int startIndex, int maxResult, Session hSession) {
        String itemRowHtml = " <div class=\"row px-5 mt-3\">";
        List<ProductItem> itemList = new ItemDAO().getProductListWithLimit(startIndex, maxResult, hSession);
        for (int i = 0; i < 5; i++) {
            if (i < itemList.size()) {
                // Set price and discount 
                String priceHtml = "";
                Promotion relatedPromotion = getRelatedPromotion(itemList.get(i).getId());
                if (relatedPromotion != null) {
                    double newPrice = itemList.get(i).getPrice() - (itemList.get(i).getPrice() * relatedPromotion.getDiscountRate()) / 100;
                    priceHtml += "                      <div class=\"mt-1\" style=\"height: 32px;\">\n"
                            + "                                <p class=\"fw-bold fs-5 m-0\"><del>LKR" + itemList.get(i).getPrice() + "/-</del></p>\n"
                            + "                            </div>\n"
                            + "                            <div class=\"mt-1\" style=\"height: 32px;\">\n"
                            + "                                <p class=\"fw-bold fs-5 m-0\">LKR " + newPrice + "/-</p>\n"
                            + "                            </div>\n";
                } else {
                    priceHtml += "                      <div class=\"mt-1\" style=\"height: 32px;\">\n"
                            + "                                <p class=\"fw-bold fs-5 m-0\">LKR" + itemList.get(i).getPrice() + "/-</p>\n"
                            + "                            </div>\n"
                            + "                            <div class=\"mt-1\" style=\"height: 32px;\">\n"
                            + "                            </div>\n";
                }

                itemRowHtml += "<div class=\"col\">\n"
                        + "                        <div class=\"w-100 px-2 border rounded-3 border-3\" style=\"height: 540px;\">\n"
                        + "                            <div class=\"mt-2 border-bottom\" style=\"height: 300px;\">\n"
                        + "                                <img src=\"" + new EncodeImageToBase64().encodeImage(itemList.get(i).getItemImgOnePath()) + "\" alt=\"\" style=\"width: 100%; height: 300px;\">\n"
                        + "                            </div>\n"
                        + "                            <div class=\"overflow-auto mt-2\" style=\"height: 62px;\">\n"
                        + "                                <p class=\"fs-5 fw-bold m-0\">" + itemList.get(i).getName() + "</p>\n"
                        + "                            </div>\n"
                        + "                            <div class=\"mt-1\" style=\"height: 28px;\">\n"
                        + "                                <i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                        + "                                <i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                        + "                                <i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                        + "                                <i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                        + "                                <i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                        + "                            </div>\n"
                        + priceHtml
                        + "                            <div class=\"mt-2 mb-3\">\n"
                        + "                                <button onclick=\"viewItem_index('" + itemList.get(i).getId() + "')\" class=\"btn btn-secondary w-100\">View</button>\n"
                        + "                            </div>\n"
                        + "                        </div>\n"
                        + "                    </div>";
            } else {
                itemRowHtml += "<div class=\"col\">\n"
                        + "       <div class=\"w-100 px-2 border rounded-3 border-3\" style=\"height: 540px;\">\n"
                        + "       </div>\n"
                        + "     </div>";
            }
        }
        itemRowHtml += "</div>";
        int itemRowCount = (int) GetRowCount();
        if (startIndex != 0) {
            if (itemRowCount / startIndex != 0) {
                return itemRowHtml;
            } else {
                return "";
            }
        } else {
            return itemRowHtml;
        }
    }
}
