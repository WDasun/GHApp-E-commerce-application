<%-- 
    Document   : Cart
    Created on : Jul 31, 2024, 8:08:53 PM
    Author     : ASUS
--%>

<%@page import="model.ProductItem"%>
<%@page import="dao.ItemDAO"%>
<%@page import="dto.CalculateCartItemSummery"%>
<%@page import="java.util.Set"%>
<%@page import="model.VariationOption"%>
<%@page import="dto.EncodeImageToBase64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <%@ include file="links.jsp" %>
        <link rel="stylesheet" href="style/cartStyle.css">

        <script src="script/cart.js"></script>
        <script src="script/newCart.js"></script>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <section class="vh-100">
            <div class="container p-0 mt-5">
                <div class="row m-0">
                    <div class="col-lg-9 p-0">
                        <div class="row m-0 mb-3">
                            <p class="fw-bolder fs-5 m-0">Cart :</p>
                        </div>
                        <!-- Item row Start -->
                        <%                            if (cartItemList != null) {
                            out.print(new ItemDAO().generateCart(cartItemList));
                            } else {
                                String cartItemRowHTML = "";
                                cartItemRowHTML = "<p class=\"text-center\">No Cart items ...</p>";
                                out.print(cartItemRowHTML);
                            }
                        %>
                        <!-- Item row End -->
                    </div>
                    <div class="col-lg-3 border p-0" style="height: fit-content;">
                        <div class="container-fluid rounded p-0">
                            <div id="div-summery-cart">
                                <%
                                    if (cartItemList != null) {
                                        Customer currentCustomer = (Customer) session.getAttribute("currentCustomerAccount");
                                        out.print(new CalculateCartItemSummery().CalculateAndGetSummeryHTML(cartItemList,0, currentCustomer != null));
                                    } else {
                                        // Set all Zero
                                        out.print(new CalculateCartItemSummery().CalculateAndGetSummeryHTML());
                                    }


                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Modal Def Cart message -->
        <div class="modal fade" id="modalDefCart" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
            <!---------->

            <!---------->
        </div>
         <%@ include file="footer.jsp" %>
    </body>
</html>
