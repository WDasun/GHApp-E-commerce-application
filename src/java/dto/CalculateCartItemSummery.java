package dto;

import connection.ConnectionBuilder;
import dao.ItemDAO;
import java.util.AbstractList;
import java.util.ArrayList;
import model.Customer;
import model.ProductItem;
import org.hibernate.Session;

/**
 *
 * @author Dasun Wimukthi
 */
public class CalculateCartItemSummery {

    double itemTotal = 0.0;
    double deliveryFee = 0.0;
    double totalPayment = 0.0;
    double totalDiscount = 0.0;
    ArrayList<CartItem> cartItemList;
    ArrayList<Integer> discountIdList;
    boolean loggedUser = false;

    public String CalculateAndGetSummeryHTML() {

        String promotionHTML = "";

        return crateOderSummeryHTML(itemTotal, deliveryFee, totalDiscount, totalPayment, promotionHTML);

    }

    public String CalculateAndGetSummeryHTML(ArrayList<CartItem> cartItemListOld, double deliveryFee, boolean loggedUser) throws Exception {

        this.loggedUser = loggedUser;
        
        Session session = ConnectionBuilder.hibSession();
        String html = null;
        this.cartItemList = cartItemListOld;

        discountIdList = new ArrayList();
        String promotionHTML = "";

        for (CartItem cartItem : cartItemList) {
            if (cartItem.isSelected()) {
                ProductItem pi = new ItemDAO().searchById(cartItem.getProductItem().getId(), session);
                if (cartItem.getPromotion() != null) {
                    int qty = cartItem.getQty();
                    double discountRt = cartItem.getPromotion().getDiscountRate();
                    double itemPrice = pi.getPrice();
                    itemTotal += qty * (itemPrice - (itemPrice * (discountRt / 100)));
                    totalDiscount += itemPrice * (discountRt / 100);
                    if (!discountIdList.contains(cartItem.getPromotion().getId())) {
                        discountIdList.add(cartItem.getPromotion().getId());
                        promotionHTML += createPromotionSubHTML(cartItem.getPromotion().getName());
                    }

                } else {
                    int qty = cartItem.getQty();
                    double itemPrice = pi.getPrice();
                    itemTotal += qty * itemPrice;
                }
            }
        }

        totalPayment = itemTotal + deliveryFee;

        html = crateOderSummeryHTML(itemTotal, deliveryFee, totalDiscount, totalPayment, promotionHTML);
//        session.close();
        return html;
    }

    // Creating a Promotion label
    private String createPromotionSubHTML(String promotionName) {
        String html = "                                <div class='alert alert-success alert-dismissible'>"
                + "                                        <!-- <button type='button' class='btn-close' data-bs-dismiss='alert'></button>  -->"
                + "                                        " + promotionName + ""
                + "                                    </div>";
        return html;
    }

    // Creating full summery HTML Code
    private String crateOderSummeryHTML(double itemTotal, double deliveryFee, double totalDiscount, double totalPayment, String promotionHTML) {

        String finalPromotionHTML = "";
        String checkoutButton = "";
        //Checking if there are promotions or not and create suitable Promotions for the Div
        if (!promotionHTML.equals("")) {
            finalPromotionHTML = promotionHTML;
        } else {
            finalPromotionHTML = "                         <div class='alert alert-success alert-dismissible'>"
                    + "                                        <!-- <button type='button' class='btn-close' data-bs-dismiss='alert'></button>  -->"
                    + "                                        none"
                    + "                                    </div>";
        }
        // Checkout button changes
 
            if (cartItemList != null) {
            if (cartItemList.size() == 0) {
                checkoutButton = "<button class='btn btn-primary' onclick='checkoutNow_cart()' disabled>Checkout Now</button>";
            } else {
                boolean selectedItemFound = false;
                for (CartItem cartItem : cartItemList) {
                    if (cartItem.isSelected()) {
                        selectedItemFound = true;
                        break;
                    }
                }
                if (selectedItemFound && loggedUser) {
                    checkoutButton = "<button class='btn btn-primary' onclick='checkoutNow_cart()'>Checkout Now</button>";
                } else {
                    checkoutButton = "<button class='btn btn-primary' onclick='checkoutNow_cart()' disabled>Checkout Now</button>";
                }
            }
        } else {
            checkoutButton = "<button class='btn btn-primary' onclick='checkoutNow_cart()' disabled>Checkout Now</button>";
        }

        String OrderSummeryHTML = "<div class='row m-0'>"
                + "                            <div>"
                + "                                <p class='m-0 fw-bold mt-2 mb-2'>Discounts and other promotions</p>"
                + "                                <div>"
                + finalPromotionHTML
                + "                                </div>"
                + "                            </div>"
                + "                        </div>"
                + "                        <div class='row px-3 fw-bold'>"
                + "                            <div class='border-bottom'>"
                + "                                <p class='m-0 fs-5 mb-2 mt-3'>Order Summery</p>"
                + "                                <div class='mb-2 d-flex align-items-center justify-content-between'>"
                + "                                    <p class='m-0'>Items Total</p>"
                + "                                    <p class='m-0'>Rs. " + itemTotal + "</p>"
                + "                                </div>"
                + "                                <div class='mb-2 d-flex align-items-center justify-content-between'>"
                + "                                    <p class='m-0'>Delivery Fee</p>"
                + "                                    <p class='m-0'>Rs. " + deliveryFee + "</p>"
                + "                                </div>"
                + "                                <div class='mb-2 d-flex align-items-center justify-content-between'>"
                + "                                    <p class='m-0'>Total Payment</p>"
                + "                                    <p class='m-0'>Rs. " + totalPayment + "</p>"
                + "                                </div>"
                + "                            </div>"
                + "                        </div>"
                + "                        <div class='row px-3 py-2'>"
                + checkoutButton
                + "                        </div>";

        return OrderSummeryHTML;

    }

}
