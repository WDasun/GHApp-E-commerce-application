/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import connection.ConnectionBuilder;
import dao.ItemDAO;
import java.util.ArrayList;
import model.ProductItem;
import org.hibernate.Session;

/**
 *
 * @author Dasun Wimukthi
 */
public class CalculateCheckoutSummery {

    private double shippingCost;
    private double itemsTotal;
    private double discountAmount;
    private double totalPayment;
    private ArrayList<CartItem> cartItemList;
    private String orderSummeryHTML;
    private ArrayList<Integer> promIdList;

    public CalculateCheckoutSummery(double shippingCost, ArrayList<CartItem> cartItemList) {
        this.shippingCost = shippingCost;
        this.cartItemList = cartItemList;
        try {
            CalculateItemTotal();
            CalculateDiscountAmount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CalculateTotalPayment();
        GenerateOrderSummeryHTML();
    }

    public String generateOrderSummeryHTML() {
        return orderSummeryHTML;
    }

    private void GenerateOrderSummeryHTML() {
        orderSummeryHTML = "<div class='container-fluid p-0'>"
                + "                                    <div class='row pt-3 m-0'>"
                + "                                        <div>"
                + "                                            <p class='m-0 fw-bold mt-2 mb-2'>Discounts and other promotions</p>"
                + "                                            <div>"
                + GeneratePromotionHTML()
                + "                                            </div>"
                + "                                        </div>"
                + "                                    </div>"
                + "                                    <div class='row px-3 fw-bold'>"
                + "                                        <div>"
                + "                                            <div class='border-bottom'>"
                + "                                                <p class='m-0 fs-5 mb-2 mt-3'>Order Summery</p>"
                + "                                                <div class='mb-2 d-flex align-items-center justify-content-between'>"
                + "                                                    <p class='m-0'>Items Total</p>"
                + "                                                    <p class='m-0'>Rs. " + itemsTotal + "</p>"
                + "                                                </div>"
                + "                                                <div class='mb-2 d-flex align-items-center justify-content-between'>"
                + "                                                    <p class='m-0'>Shipping cost</p>"
                + "                                                    <p class='m-0'>Rs. " + shippingCost + "</p>"
                + "                                                </div>"
                + "                                                <div class='mb-2 d-flex align-items-center justify-content-between'>"
                + "                                                    <p class='m-0'>Discounts and others</p>"
                + "                                                    <p class='m-0'>-Rs. " + discountAmount + "</p>"
                + "                                                </div>"
                + "                                                <div class='mb-2 d-flex align-items-center justify-content-between'>"
                + "                                                    <p class='m-0'>Total Payment</p>"
                + "                                                    <p class='m-0'>Rs. " + totalPayment + "</p>"
                + "                                                </div>"
                + "                                            </div>"
                + "                                        </div>"
                + "                                    </div>"
                + "                                    <div class='row'>"
                + "                                        <div class='mt-3 px-4'>"
                + "                                            <button class='btn btn-primary w-100' onclick='PayNow_checkout()'>Pay now</button>"
                + "                                        </div>"
                + "                                    </div>"
                + "                                </div>";

    }

    private String GeneratePromotionHTML() {
        String promotionHTML = "";
        promIdList = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            if (cartItem.isSelected() && cartItem.getPromotion() != null) {
                if (notDuplicatePromId(cartItem.getPromotion().getId())) {
                    promotionHTML += "<div class='alert alert-success alert-dismissible mb-0 mb-1'>"
                            + "" + cartItem.getPromotion().getName() + ""
                            + "</div>";
                    promIdList.add(cartItem.getPromotion().getId());
                }
            }
        }
        if (promotionHTML.equals("")) {
            return "<div class='alert alert-success alert-dismissible mb-0 mb-1'>"
                    + "none"
                    + "</div>";
        } else {
            return promotionHTML;
        }

    }

    private boolean notDuplicatePromId(int promId) throws NullPointerException {
        if (promIdList != null) {
            boolean notDuplicate = true;
            for (int id : promIdList) {
                if (id == promId) {
                    notDuplicate = false;
                    break;
                }
            }
            return notDuplicate;
        } else {
            throw new NullPointerException();
        }
    }

    private void CalculateItemTotal() throws Exception {
        Session session = ConnectionBuilder.hibSession();
        for (CartItem cartItem : cartItemList) {
            if (cartItem.isSelected()) {
                itemsTotal += cartItem.getQty() * getProductItem(cartItem.getProductItem().getId(), session).getPrice();
            }
        }
//        session.close();
    }

    private void CalculateDiscountAmount() throws Exception {
        Session session = ConnectionBuilder.hibSession();
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getPromotion() != null && cartItem.isSelected()) {
                discountAmount += cartItem.getQty() * ((getProductItem(cartItem.getProductItem().getId(), session).getPrice() / 100) * cartItem.getPromotion().getDiscountRate());
            }
        }
//        session.close();
    }

    private void CalculateTotalPayment() {
        totalPayment = itemsTotal - discountAmount + shippingCost;
    }

    private ProductItem getProductItem(String productId, Session session) throws Exception {
        ProductItem productItem = new ItemDAO().searchById(productId, session);
        return productItem;
    }
}
