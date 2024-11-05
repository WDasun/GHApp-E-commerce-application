<%-- 
    Document   : itemViewer
    Created on : Oct 4, 2024, 8:07:34 PM
    Author     : ASUS
--%>

<%@page import="java.util.List"%>
<%@page import="dao.CartDAO"%>
<%@page import="model.ShoppingCart"%>
<%@page import="dao.CartItemDAO"%>
<%@page import="model.ShoppingCartItem"%>
<%@page import="dao.WishListDAO"%>
<%@page import="model.WishList"%>
<%@page import="model.ReviewHelpful"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.CustomerOrder"%>
<%@page import="model.OrderLine"%>
<%@page import="dao.ReviewDAO"%>
<%@page import="model.Review"%>
<%@page import="model.Promotion"%>
<%@page import="model.Variation"%>
<%@page import="model.VariationOption"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Set"%>
<%@page import="dto.EncodeImageToBase64"%>
<%@page import="connection.ConnectionBuilder"%>
<%@page import="org.hibernate.Session"%>
<%@page import="dao.ItemDAO"%>
<%@page import="model.ProductItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        Session hSession = ConnectionBuilder.hibSession();
        String productItemId = request.getParameter("itemId");
        ProductItem productItem = new ItemDAO().searchById(productItemId, hSession);

        Set<VariationOption> variationOptionList = productItem.getVariationOptions();

    %>
    <%        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <%                out.print(productItem.getName());
            %>
        </title>
        <link rel="stylesheet" href="style/itemViewer.css">
        <script src="script/itemViewer.js"></script>
        <%@ include file="links.jsp" %>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <section>
            <div class="container-fluid div-content">
                <!-- Main details with Images -->
                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-8">
                        <div class="row h-100">
                            <div class="col-4 p-0">
                                <div class="div-img">
                                    <img src="
                                         <%                                             out.print(new EncodeImageToBase64().encodeImage(productItem.getItemImgOnePath()));
                                         %>
                                         " class="img-item" alt="...">
                                </div>
                                <div class="div-allImages">
                                    <div class="row h-100 m-0">
                                        <div class=" d-flex align-items-center justify-content-center">
                                            <div class="div-allImages-imgContainer">
                                                <div class="div-border border">
                                                    <img src="
                                                         <%
                                                             out.print(new EncodeImageToBase64().encodeImage(productItem.getItemImgOnePath()));
                                                         %>
                                                         " class="img-otherImages" alt="...">
                                                </div>
                                            </div>
                                            <div class="div-allImages-imgContainer">
                                                <div class="div-border border">
                                                    <img src="
                                                         <%
                                                             out.print(new EncodeImageToBase64().encodeImage(productItem.getItemImgOnePath()));
                                                         %>
                                                         " class="img-otherImages" alt="...">
                                                </div>
                                            </div>
                                            <div class="div-allImages-imgContainer">
                                                <div class="div-border border">
                                                    <img src="
                                                         <%
                                                             out.print(new EncodeImageToBase64().encodeImage(productItem.getItemImgThreePath()));
                                                         %>
                                                         " class="img-otherImages" alt="...">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6 pt-3">
                                <div class="border-bottom">
                                    <h4>
                                        <%
                                            out.print(productItem.getName());
                                        %>
                                    </h4>
                                    <div>
                                        <div class="row">
                                            <div class="col">
                                                <div class="d-flex align-items-center">
                                                    <div class="me-2">
                                                        <i class="fa-solid fa-star fa-sm me-1" style="color: #ffa41c;"></i>
                                                        <i class="fa-solid fa-star fa-sm me-1" style="color: #ffa41c;"></i>
                                                        <i class="fa-solid fa-star fa-sm me-1" style="color: #ffa41c;"></i>
                                                        <i class="fa-solid fa-star fa-sm me-1" style="color: #ffa41c;"></i>
                                                        <i class="fa-solid fa-star fa-sm me-1" style="color: #ffa41c;"></i>
                                                    </div>
                                                    <p class="m-0">(265)</p>
                                                </div>
                                                <p class="fs-6">
                                                    <%
                                                        int variationIdOfBrand = 1;
                                                        String brandName = "None";
                                                        for (VariationOption variationOption : variationOptionList) {
                                                            if (variationOption.getVariation().getId() == variationIdOfBrand) {
                                                                brandName = variationOption.getValue();
                                                            }
                                                        }
                                                        out.print("Brand : <span class='fw-bold'>" + brandName + "</span></p>");
                                                    %>  
                                            </div>
                                            <div class="col">
                                                <!--^^^^^^^^^^^^ Add to wish list button ^^^^^^^^^^^^-->
                                                <div class="h-100 d-flex" id="wishListbtn_div_itemViewer">
                                                    <%
                                                    WishList wishList = new WishListDAO().searchByCustomerAndProductItem(customer, productItem);
                                                    if(wishList != null){
                                                        out.print("<button class=\"btn-itemActions\" onclick=\"removeWishList("+wishList.getId()+","+customer.getCustomerId()+",'"+productItem.getId()+"')\">Remove from wish list<i class=\"fa-solid fa-heart fa-2xl ms-2\" style=\"color: #ff8787;\"></i></button>");
                                                    }else if(wishList == null && customer != null){
                                                        out.print("<button class=\"btn-itemActions\" onclick=\"saveWishList("+customer.getCustomerId()+",'"+productItem.getId()+"')\">Add to wish list<i class=\"fa-solid fa-heart fa-2xl ms-2\" style=\"color: rgb(169, 142, 213);\"></i></button>");
                                                    }
                                                    %>
            
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div class="mt-3">
                                        <h3>
                                            <%
                                                out.print("Rs. " + productItem.getPrice());
                                            %>
                                        </h3>
                                    </div>
                                    <div class="mt-3">
                                        <p>Promotions :</p>
                                        <%
                                            String promotionHTML = "<p class='ms-5'>None</p>";
                                            Promotion relatedPromotion = new ItemDAO().getRelatedPromotion(productItem.getId());
                                            if(relatedPromotion != null){
                                                promotionHTML = "                                <div class='alert alert-success alert-dismissible fw-bold mb-0'>"
                                                    + "                                        <!-- <button type='button' class='btn-close' data-bs-dismiss='alert'></button>  -->"
                                                    + "                                        " + relatedPromotion.getName() + " RT : " + relatedPromotion.getDiscountRate() + "%"
                                                    + "                                    </div>";
                                            }else{
                                                promotionHTML = "<p class=\"ms-5\">None</p>";
                                            }
                                            out.print(promotionHTML);
                                        %>
                                    </div>
                                </div>
                                    <div class="d-flex mt-4 align-items-center justify-content-center" id="cartbtn_div_itemViewer">
                                    <!--^^^^^^^^^^^^ Add to Cart button ^^^^^^^^^^^^-->
                                    <%
                                        ShoppingCart customerShoppingCart = new CartDAO().searchByCustomer(customer);
                                        ShoppingCartItem shoppingCartItem = new CartItemDAO().searchByProductItemAndCustomer(productItem, customerShoppingCart);
                                        if(shoppingCartItem != null){
                                            out.print("<button class=\"btn btn-danger w-100\" style=\"height: 3rem;\" onclick=\"removeFromCart_itemViewer('"+productItem.getId()+"')\">Remove from the Cart</button>");
                                        }else{
                                            out.print("<button class=\"btn btn-danger w-100\" style=\"height: 3rem;\" onclick=\"addToCart_itemViewer('"+productItem.getId()+"')\">Add to Cart</button>");
                                        }
                                    %>
                            
                                </div>
                            </div>
                            <div class="col-2 p-0">
                                <div class="py-3 mt-2 px-2 div-itemInforRight">
                                    <p class="m-0">Item ID :</p>
                                    <p class="m-0 fw-bold">
                                        <%
                                            out.print(productItem.getId());
                                        %>
                                    </p>
                                    <p class="m-0 mt-2">SKU :</p>
                                    <p class="m-0 fw-bold">
                                        <%
                                            out.print(productItem.getSku());
                                        %>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
                <!-- Description and other details -->
                <div class="row border border-bottom pb-2">
                    <div class="col-2"></div>
                    <div class="col-8">
                        <div class="row">
                            <div class="col-4"></div>
                            <div class="col-8">
                                <div class="row p-0 m-0 div-description-head">
                                    <p class="p-0 m-0 mt-1 mb-1 fs-5 fw-bold">Item Description</p>
                                </div>
                                <div class="row p-0 m-0">
                                    <p class="m-0 p-0">
                                        <%
                                            out.print(productItem.getDescription());
                                        %>
                                    </p>
                                </div>
                                <div class="row p-0 m-0">
                                    <p class="p-0 m-0 mt-1 fs-5 fw-bold">About this item</p>
                                </div>
                                <div class="row p-0 m-0">
                                    <%
                                        for (VariationOption variationOption : variationOptionList) {
                                            String variationNameHTML = "<p class='p-0 m-0 fs-6 fw-bold'>" + variationOption.getVariation().getName() + "</p>";
                                            String variationOptionValueHTML = "<p class='p-0 m-0 fs-6'>" + variationOption.getValue() + "</p>";
                                            String completeHTML = variationNameHTML + variationOptionValueHTML;
                                            out.print(completeHTML);
                                        }
                                    %>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
                <!-- Ratings & Reviews -->
                <div class="row">
                    <div class="col-5">
                        <%
                            CustomerOrder customerOrder = null;
                            Review customerReviewForTheProductItem = null;

                            if(customer != null){
                                Set<OrderLine> orderLineSet = productItem.getOrderLines();
                            if (orderLineSet != null) {
                                for (OrderLine orderLine : orderLineSet) {
                                    if (customer.getCustomerId() == orderLine.getCustomerOrder().getCustomer().getCustomerId()) {
                                        customerOrder = orderLine.getCustomerOrder();
                                        break;
                                    }
                                }
                            }
                             customerReviewForTheProductItem = new ReviewDAO().getReviewByCustomerAndProductItem(customer, productItem, hSession);
                            }
                            
                            // ^^^^^^^ Comment button manage ^^^^^^^^
                            if (customerOrder != null && customerReviewForTheProductItem == null) {
                                out.print("<button class='btn btn-danger w-100 mt-3 btn-writeACustomerReview' onclick='writeAReview_itemViewer(\"" + productItem.getId() + "\"," + customer.getCustomerId() + ")'>Write a customer review</button>");
                            } else if (customerOrder == null) {
                                out.print("<button disabled class='btn btn-danger w-100 mt-3 btn-writeACustomerReview'>To write a Comment you must buy the product !</button>");
                            } else if (customerReviewForTheProductItem != null) {
                                out.print("<button class='btn btn-danger w-100 mt-3 btn-writeACustomerReview' onclick='writeAReview_itemViewer(\"" + productItem.getId() + "\"," + customer.getCustomerId() + ")'>Edit your review</button>");
                            }
                        %>

                    </div>
                    <div class="col-6 p-0">
                        <div class="row m-0">

                            <div class="col">
                                <p class="fs-5 fw-bold mt-3">Top reviews</p>
                                <div class="h-75" style='overflow-y: auto;' id="topReviewDiv_itemViewer">
                                    <!-- Review box -->
                                    <%
                                        List<Review> productReviewSet = new ReviewDAO().getRiviewListForProduct(productItem, hSession);
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
                                        out.print(cmntHTML);
                                    %>
                                    <!-- Review box  End-->


                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-1"></div>
                </div>
            </div>
        </section>
    </body>
    <!--Modals-->
    <!-- Modal write comment -->
    <div class="modal fade" id="writeAComment-modal-itemViewer" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content" id="writeAComment-modal-itemViewer-content">
                <!-- ---------- -->

                <div class="modal-header">
                    <h5>Write your comment</h5>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row p-0">
                            <div class="d-flex align-items-center mb-2 p-0 ps-1">
                                <p class="m-0 me-3">Give a score :</p>
                                <div class="d-flex p-2" id="star-container">
                                    <button class="btn" id="btn-star-1" onclick="setRate_itemViewer(1)"><i class="fa-solid fa-star" style="color: #ffa41c;"></i></button>
                                    <button class="btn" id="btn-star-2" onclick="setRate_itemViewer(2)"><i class="fa-regular fa-star" style="color: #ffa41c;"></i></button>
                                    <button class="btn" id="btn-star-3" onclick="setRate_itemViewer(3)"><i class="fa-regular fa-star" style="color: #ffa41c;"></i></button>
                                    <button class="btn" id="btn-star-4" onclick="setRate_itemViewer(4)"><i class="fa-regular fa-star" style="color: #ffa41c;"></i></button>
                                    <button class="btn" id="btn-star-5" onclick="setRate_itemViewer(5)"><i class="fa-regular fa-star" style="color: #ffa41c;"></i></button>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <input type="text" class="form-control" id="title_review_modal_itemViewer" placeholder="Enter title">
                        </div>
                        <div class="row mt-2">
                            <textarea class="form-control" id="comment_review_modal_itemViewer" rows="6" placeholder="Enter your comment"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer ">
                    <%
                        if (customerReviewForTheProductItem != null) {
                            out.print("<button class=\"btn btn-primary \" style=\"width: 100px; \" onclick=\"updateReview_itemViewer()\">Update</button>");
                        } else {
                            out.print("<button class=\"btn btn-primary \" style=\"width: 100px; \" onclick=\"saveReview_itemViewer()\">Save</button>");
                        }
                    %>         
                    <button class="btn btn-danger " style="width: 100px; " data-bs-dismiss="modal">Close</button>
                </div>

                <!-- ---------- -->
            </div>
        </div>
    </div>
                    <%@ include file="footer.jsp" %>
</body>
</html>
