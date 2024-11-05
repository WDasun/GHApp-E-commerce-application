<%-- 
    Document   : collection
    Created on : Jun 25, 2024, 6:44:20 PM
    Author     : ASUS
--%>

<%@page import="java.util.Iterator"%>
<%@page import="dao.WishListDAO"%>
<%@page import="model.WishList"%>
<%@page import="connection.ConnectionBuilder"%>
<%@page import="org.hibernate.Session"%>
<%@page import="dto.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.EncodeImageToBase64"%>
<%@page import="model.Product"%>
<%@page import="model.ProductItem"%>
<%@page import="model.VariationOption"%>
<%@page import="java.util.Set"%>
<%@page import="model.Variation"%>
<%@page import="model.ProductCategory"%>
<%@page import="dao.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><%
            String categoryId = request.getParameter("categoryId");
            ProductCategory subCategory = null;
            Session hsession_collection = ConnectionBuilder.hibSession();
            if (!categoryId.equals("") && categoryId != null) {
                subCategory = new CategoryDAO().searchById(categoryId, hsession_collection);
                out.print(subCategory.getName());
            }
            %></title>

        <%@ include file="links.jsp" %>

        <script src="script/collection.js"></script>
        <script src="script/collectionWishList.js"></script>

        <link rel="stylesheet" href="style/collectionStyle.css">

    </head>

    <body>
        <%@ include file="navBar.jsp" %>
        <section id="resultDetails">
            <div class="container-fluid shadow-sm p-0 ps-3 d-flex align-items-center justify-content-start" style="height: 37px;">

            </div>
        </section>
        <section id="resultBody">
            <div class="container-fluid ">
                <div class="row">
                    <!-- Left side  -->
                    <div class="col-lg-2 ps-5">
                        <div class="mt-4 div-siteDetails">
                            <p class="m-0 fw-bold txt-title-siteDetails">Site Details</p>
                            <div class="ps-3">
                                <%                                    if (subCategory != null) {
                                        out.print("<p class=\"m-0 fw-bold\">Parent category</p>\n"
                                                + "                                <p class=\"m-0\">- " + subCategory.getProductCategory().getName() + "</p>\n"
                                                + "                                <p class=\"m-0 fw-bold\">Sub category</p>\n"
                                                + "                                <p class=\"m-0\">- " + subCategory.getName() + "</p>");
                                    }
                                %>

                            </div>

                        </div>
                        <div class="mt-3 div-variationsAndOptins">
                            <p class="m-0 fw-bold mb-1">Filter products</p>

                            <%
                                if (subCategory != null) {

                                    String html = "";

                                    Set<Variation> variationSet = subCategory.getVariations();
                                    for (Variation v : variationSet) {
                                        html += "<div class=\"ps-2\">\n"
                                                + "<p class=\"m-0 fw-bold\">" + v.getName() + "</p>\n"
                                                + "<div class=\"ms-3 pt-2 select-variationOptions\">";
                                        Set<VariationOption> variationOptionSet = v.getVariationOptions();
                                        for (VariationOption vo : variationOptionSet) {
                                            html += "<div class=\"form-check\">\n"
                                                    + "<input class=\"form-check-input\" type=\"checkbox\" value=\"" + vo.getId() + "\" id=\"flexCheckDefault\">\n"
                                                    + "<label class=\"form-check-label\" for=\"flexCheckDefault\">\n"
                                                    + "" + vo.getValue() + "\n"
                                                    + "</label>\n"
                                                    + "</div>";
                                        }
                                        html += "</div></div>";
                                    }
                                    out.print(html);
                                }
                            %>                        

                        </div>

                    </div>
                    <!-- Right side -->
                    <div class="col-lg-10 p-0">
                        <div class="container-fluid p-0">
                            <!-- Title -->
                            <div class="row my-2 ps-2 pt-2 pb-2">
                                <%
                                    String title = "<p class=\"txt-title-leftSideDiv m-0\">Item result for "+subCategory.getName()+"</p>";
                                    out.print(title);
                                %>
                               
                            </div>
                            <!-- Item List -->
                            <div class="row">
                                <div>
                                    <div class="container-fluid p-0" id="div-items-collection">
                                        <!-- -------- Item row Start -------- -->

                                        <%
                                            if (subCategory != null) {

                                                EncodeImageToBase64 e64 = new EncodeImageToBase64();

                                                String html = "";

                                                Set<Product> productSet = subCategory.getProducts();

                                                for (Product p : productSet) {
                                                    Set<ProductItem> itemSet = p.getProductItems();
                                                    Iterator<ProductItem> iterator = itemSet.iterator();
                                                    while (iterator.hasNext()) {
                                                        if (!iterator.next().isStatus()) {
                                                            iterator.remove();
                                                        }
                                                    }
                                                    ProductItem[] itemList = itemSet.toArray(new ProductItem[itemSet.size()]);

                                                    int itemAmount = itemList.length;
                                                    int itemAmountMin = itemAmount - 1;

                                                    int itemAmountForARow = 4;

                                                    int loopAmount = itemAmountMin + (itemAmountForARow - (itemAmountMin % itemAmountForARow));

                                                    for (int i = 0; i < loopAmount; i++) {
                                                        if ((i % itemAmountForARow) == 0) {
                                                            html += "<!-- New row -->"
                                                                    + "<div class=\"row m-0 mb-2\">";
                                                        }

                                                        if (itemAmountMin >= i) {
                                                            //Cart button manage
                                                            String addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickAddToCartBtn('" + itemList[i].getId() + "','addtoCartBtnDiv" + itemList[i].getId() + "')\"><i class=\"fa-solid fa-cart-shopping fa-2xl me-2\" style=\"color: #B197FC;\"></i></button>\n";
                                                            if (cartItemList != null) {
                                                                for (CartItem cartItem : cartItemList) {
                                                                    if (cartItem.getProductItem().getId().equals(itemList[i].getId())) {
                                                                        addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickRemoveItemCart('" + itemList[i].getId() + "','addtoCartBtnDiv" + itemList[i].getId() + "')\"><i class=\"fa-solid fa-trash fa-2xl\" style=\"color: #74C0FC;\"></i></button>";
                                                                        break;
                                                                    } else {
                                                                        addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickAddToCartBtn('" + itemList[i].getId() + "','addtoCartBtnDiv" + itemList[i].getId() + "')\"><i class=\"fa-solid fa-cart-shopping fa-2xl me-2\" style=\"color: #B197FC;\"></i></button>\n";
                                                                    }
                                                                }
                                                            } else {
                                                                addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickAddToCartBtn('" + itemList[i].getId() + "','addtoCartBtnDiv" + itemList[i].getId() + "')\"><i class=\"fa-solid fa-cart-shopping fa-2xl me-2\" style=\"color: #B197FC;\"></i></button>\n";
                                                            }

                                                            //Wish list manage
                                                            String wishListBtnHTML = "";
                                                            WishList wishList = new WishListDAO().searchByCustomerAndProductItem(customer, itemList[i]);
                                                            if (customer != null) {
                                                                if (wishList != null) {
                                                                    wishListBtnHTML = "<button class='btn-itemActions border-end' onclick='removeWishList_collection(" + wishList.getId() + "," + customer.getCustomerId() + ",\"" + itemList[i].getId() + "\")'><i class='fa-solid fa-heart fa-2xl me-2' style='color: #ff8787;'></i> </button>";
                                                                } else {
                                                                    wishListBtnHTML = "<button class='btn-itemActions border-end' onclick='saveWishList_collection(" + customer.getCustomerId() + ", \"" + itemList[i].getId() + "\")'><i class='fa-solid fa-heart fa-2xl me-2' style='color: rgb(169, 142, 213);'></i> </button>";
                                                                }
                                                            }

                                                            html += "<div class=\"col p-0\">\n"
                                                                    + "<!-- Card -->\n"
                                                                    + "<div class=\"card card-item\">\n"
                                                                    + "<div class=\"div-img-item\" style=\"height: 50%;\">\n"
                                                                    + "<img src=\"" + e64.encodeImage(itemList[i].getItemImgOnePath()) + "\" class=\"card-img-top\" alt=\"...\">\n"
                                                                    + " </div>\n"
                                                                    + "\n"
                                                                    + "<div class=\"card-body p-0 p-2\">\n"
                                                                    + "<h5 class=\"card-title m-0\">" + itemList[i].getName() + "</h5>\n"
                                                                    + "<div class=\"d-flex py-1 mt-1\">\n"
                                                                    + "<div>\n"
                                                                    + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                                                    + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                                                    + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                                                    + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                                                    + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                                                    + "</div>\n"
                                                                    + "<p class=\"m-0 ms-2\">(256)</p>\n"
                                                                    + "</div>\n"
                                                                    + "<div class=\"d-flex\">\n"
                                                                    + "<p class=\"m-0 fs-6 me-1\">Price (LKR) : </p>\n"
                                                                    + "<p class=\"m-0 fs-5\">" + itemList[i].getPrice() + "</p>\n"
                                                                    + "</div>\n"
                                                                    + "<div class=\"d-flex mt-3\">\n"
                                                                    + "<div style='width: 100%;' id='wishListbtn_collection" + itemList[i].getId() + "'>"
                                                                    + wishListBtnHTML
                                                                    + "</div>"
                                                                    + "<div style=\"width: 100%;\" id=\"addtoCartBtnDiv" + itemList[i].getId() + "\">"
                                                                    + addToCartBtnHTML
                                                                    + "</div>"
                                                                    + "</div>\n"
                                                                    + "<div class=\"mt-3\">\n"
                                                                    + "<button class=\"w-100 btn-item-view\" onclick=\"onclickView_collection('" + itemList[i].getId() + "')\">View</button>\n"
                                                                    + "</div>\n"
                                                                    + "</div>\n"
                                                                    + "</div>\n"
                                                                    + "</div>";
                                                        } else {
                                                            html += "<div class=\"col p-0\">\n"
                                                                    + "<!-- Card -->\n"
                                                                    + "<div class=\"card card-item\">\n"
                                                                    + "\n"
                                                                    + "</div>\n"
                                                                    + "</div>";
                                                        }

                                                        if ((i % itemAmountForARow) == 3) {
                                                            html += "</div>"
                                                                    + "<!-- Row End -->";
                                                        }

                                                    }
                                                }

                                                out.print(html);
                                            }

                                        %>
                                        <!-- ---------- item rwo end -------- -->

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Offcanvas content -->
        <div class="offcanvas offcanvas-start offCanvas-index" tabindex="-1" id="offcanvasMain" aria-labelledby="offcanvasMain">
            <div class="offcanvas-header border offCanvas-head-index">
                <h5 class="offcanvas-title" id="offcanvasExampleLabel">Hello, Dasun Wimukthi</h5>
                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body p-0" id="div-categoryList-header">
                <div class="pt-4 pb-3 border-bottom border-2">
                    <p class="ms-5 txt-patentCategory">Electronic Accessories</p>
                    <button class="btn p-0 ps-5 d-flex align-items-center btn-subCategory" onclick="clickOnSubCategory_index('xxx')">Laptops</button>
                </div>
            </div>
        </div>

        <!-- Modal Def -->
        <div class="modal fade" id="defModal-collection" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
            <!------------->

            <!------------->
        </div>
 <%@ include file="footer.jsp" %>
    </body>

</html>
