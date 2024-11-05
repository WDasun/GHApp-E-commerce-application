/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.text.SimpleDateFormat;
import java.util.Set;
import model.Customer;
import model.ProductItem;
import model.Review;
import model.ReviewHelpful;

/**
 *
 * @author ASUS
 */
public class GenerateTopReviews {
    
    public String generate(ProductItem productItem, Customer customer, Review customerReviewForTheProductItem){
         Set<Review> productReviewSet = productItem.getReviews();
                                        String cmntHTML = "";
                                        System.out.println("Size of productReviewSet : " + productReviewSet.size());
                                        for (Review rvw : productReviewSet) {
                                            // HelpFull count config
                                            int helpfulCount = 0;
                                            Set<ReviewHelpful> reviewHelpfulSet = rvw.getReviewHelpfuls();
                                            helpfulCount = reviewHelpfulSet.size();
                                            // Helpfull Button config Start
                                            String reviewHelpFullButton = "<button class='btn btn-secondary btn-helpful'>Helpful</button>";
                                            for (ReviewHelpful rvwhlp : reviewHelpfulSet) {
                                                if (rvwhlp.getReview().getCustomer().getCustomerId() == customer.getCustomerId()) {
                                                    reviewHelpFullButton = "<button class='btn btn-secondary btn-helpful'>Remove helpful</button>";
                                                }
                                            }
                                            if (customerReviewForTheProductItem != null && customerReviewForTheProductItem.getId() == rvw.getId()) {
                                                reviewHelpFullButton = " <button disabled class='btn btn-secondary btn-helpful'>Helpful</button>";
                                            }
                                            // Helpfull Button config End
                                            // Review Date config
                                            String date = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss").format(rvw.getDate());
                                            // Rating config
                                            int rating = rvw.getRate();
                                            String ratingHTML = "";
                                            for (int i = 0; i < rating; i++) {
                                                ratingHTML += "<i class='fa-solid fa-star fa-sm me-1' style='color: #ffa41c;'></i>\n";
                                            }

                                            cmntHTML += "<div class=\"mb-4\">\n"
                                                    + "                                        <div class=\"d-flex align-items-center div-customer-review-head\">\n"
                                                    + "                                            <div>\n"
                                                    + "                                                <div class=\"div-img-customer-review\">\n"
                                                    + "                                                    <img src=\"images/CustomerIcon.webp\" class=\"rounded-circle h-100 w-100\" alt=\"Cinque Terre\">\n"
                                                    + "                                                </div>\n"
                                                    + "                                            </div>\n"
                                                    + "                                            <div>\n"
                                                    + "                                                <p class=\"m-0 fw-bold ms-3\">" + rvw.getCustomer().getFname() + " " + rvw.getCustomer().getLname() + "</p>\n"
                                                    + "                                            </div>\n"
                                                    + "                                        </div>\n"
                                                    + "                                        <div class=\"mt-1 d-flex align-items-center div-customer-review-rating\">\n"
                                                    + "                                            <div>\n"
                                                    + "                                                <div>\n"
                                                    + ratingHTML
                                                    + "                                                </div>\n"
                                                    + "                                            </div>\n"
                                                    + "                                            <div>\n"
                                                    + "                                                <p class=\"m-0 fw-bold\">" + rvw.getCommentTitle() + "</p>\n"
                                                    + "                                            </div>\n"
                                                    + "                                        </div>\n"
                                                    + "                                        <div>\n"
                                                    + "                                            <p class=\"m-0\">" + date + "</p>\n"
                                                    + "                                        </div>\n"
                                                    + "                                        <div class=\"div-customer-review-body\">\n"
                                                    + "                                            <p class=\"m-0 fw-bold\">" + rvw.getComment() + "</p>\n"
                                                    + "                                        </div>\n"
                                                    + "                                        <div>\n"
                                                    + "                                            <div class=\"mt-3 div-customer-review-footer\">\n"
                                                    + "                                                <p class=\"m-0\">" + helpfulCount + " people found this helpful</p>\n"
                                                    + "                                            </div>\n"
                                                    + "                                            <div class=\"mt-1\">\n"
                                                    + reviewHelpFullButton
                                                    + "                                            </div>\n"
                                                    + "                                        </div>\n"
                                                    + "                                    </div>";

                                        }
                                        return cmntHTML;
    }
    
}
