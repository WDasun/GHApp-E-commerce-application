<%-- 
    Document   : customerProfile
    Created on : Jul 24, 2024, 1:51:24 PM
    Author     : ASUS
--%>

<%@page import="dao.CustomerDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Profile</title>
        <%@ include file="links.jsp" %>
        <%
            Customer c = (Customer) session.getAttribute("currentCustomerAccount");
            if (c == null) {
                response.sendRedirect("CustomerLogin.jsp");
            }
        %>
        <link rel="stylesheet" href="style/customerProfileStyle.css">
        <script src="script/customerProfile.js"></script>
        <script src="script/customerProfileWishList.js"></script>
        <script src="script/customerProfilePaymentOption.js"></script>
        <script src="script/customerProfileOrder.js"></script>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <div class="container-fluid p-0">
            <div class="row m-0">
                <div class="col-1 p-0"></div>
                <div class="col-2 p-0 pt-4" style="height: 50rem;">
                    <div class="border-bottom">
                        <p class="fs-4 m-0 ps-2">Wellcome,</p>
                        <p class="fs-4 m-0 ps-5"><%
                            if (c != null) {
                                out.print(c.getFname() + " " + c.getLname());
                            }
                            %></p>
                    </div>
                    <div class="mt-3 ms-4">
                        <p class="fs-5 fw-bold m-0">Manage Account</p>
                        <button class="d-flex align-items-center w-100 btn-subTopic-cProfile p-0 ps-2 mt-2" onclick="setProfileDetailsContent_customerProfile()">Profile details</button>
                        <button class="d-flex align-items-center w-100 btn-subTopic-cProfile p-0 ps-2" onclick="setAddressPoolContent_customerProfile()">Address pool</button>
                        <button class="d-flex align-items-center w-100 btn-subTopic-cProfile p-0 ps-2" onclick="paymentOption_customerProfile()">Payment options</button>
                    </div>
                    <div class="mt-3 ms-4">
                        <p class="fs-5 fw-bold m-0">Orders</p>
                        <button class="d-flex align-items-center w-100 btn-subTopic-cProfile p-0 ps-2 mt-2" onclick="recentCustomerOrders_customerProfile()">Recent</button>
                    </div>
                    <div class="mt-3 ms-4">
                        <%
                            out.print("<button onclick=\"loadWishList_customerProfile("+customer.getCustomerId()+")\" class=\"d-flex fs-5 fw-bold align-items-center w-100 btn-subTopic-cProfile p-0 mt-2\">Wish List</button>");
                        %>
                        
                    </div>
                </div>
                <div class="col-9 p-0 pt-4 ps-2 pe-5">
                    <div class="h-100 was-validated w-100 div-dContent-cProfile px-4 py-4" id="div-content-customerProfile">

                        <!-- content start -->




                        <!-- content end -->

                    </div>

                </div>
            </div>
        </div>
    </div>
    <!-- Def Modal -->

    <!-- Modal -->
    <div class="modal fade" id="defModal-customerProfile" tabindex="-1" aria-labelledby="defModal-customerProfile" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-xl">
            <div class="modal-content" id="defModel-content-customerProfile">
            <!-- body start -->

            <!-- body end -->
            </div>
        </div>
    </div>
    
        <!-- Address Update Modal -->
    <div class="modal fade" id="updateAddressModal_customerProfile" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content" id="updateAddressModal_customerProfile_content">
                <!-- ---------- -->
                
                <!-- ---------- -->
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
