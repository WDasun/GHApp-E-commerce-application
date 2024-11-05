<%-- 
    Document   : Checkout
    Created on : Sep 25, 2024, 2:52:10 PM
    Author     : ASUS
--%>

<%@page import="org.hibernate.Session"%>
<%@page import="connection.ConnectionBuilder"%>
<%@page import="dto.CalculateCheckoutSummery"%>
<%@page import="dao.ShippingTypeDAO"%>
<%@page import="model.ShippingType"%>
<%@page import="dao.CountryDAO"%>
<%@page import="model.Country"%>
<%@page import="dao.AddressDAO"%>
<%@page import="model.Address"%>
<%@page import="model.CreditOrDebitCardType"%>
<%@page import="dao.CardTypeDAO"%>
<%@page import="dao.CustomerDAO"%>
<%@page import="dao.CardDetailsDAO"%>
<%@page import="model.CardDetails"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
        <%@ include file="links.jsp" %>

        <script src="script/checkoutCommon.js"></script>
        <script src="script/checkoutAddress.js"></script>
        <script src="script/checkoutSummery.js"></script>
        <script src="script/checkoutPayment.js"></script>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <%            if (customer == null) {
                response.sendRedirect("CustomerLogin.jsp");
            }
        Session hSession = ConnectionBuilder.hibSession();
        %>
        <div class="container-fluid">
            <div class="row ps-5 div-txt-head-checkout border d-flex align-items-center">
                <p class="m-0 p-0">Checkout (<span><%
                            int selectedCount = 0;
                            for (CartItem ci : cartItemList) {
                                    if(ci.isSelected()){
                                        selectedCount ++;
                                    }
                                }
                            out.print(selectedCount);
                        %></span> item)</p>
            </div>
            <div class="row mt-3">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">
                    <div class="row">
                        <div class="col-8">
                            <div class="h-100 w-100 px-4 pt-4 pb-5 div-infor">
                                <!-- Enter div to here  -->
                                <div class="creditCartPaymentGateway">
                                    <div class="d-flex align-items-center justify-content-start">
                                        <div>
                                            <i class="fa-brands fa-cc-visa fa-2xl"></i>
                                            <i class="fa-brands fa-cc-mastercard fa-2xl"></i>
                                        </div>
                                        <div class="ms-5">
                                            <div class="dropdown">
                                                <button class="btn btn-secondary dropdown-toggle" type="button" id="Aoutofill" data-bs-toggle="dropdown" aria-expanded="false">
                                                    Aoutofill
                                                </button>
                                                <ul class="dropdown-menu" aria-labelledby="Aoutofill">
                                                    <%
                                                        try {

                                                            if (customer != null) {
                                                                List<CardDetails> cdl = new CardDetailsDAO().searchByCustomer(customer);
                                                                for (CardDetails cd : cdl) {
                                                                    CreditOrDebitCardType cardType = new CardTypeDAO().searchById(cd.getCreditOrDebitCardType().getId());
                                                                    out.print("<li><button onclick='loadCardInfo_chekout(" + cd.getId() + ")' type='button' class='btn p-0'><a class='dropdown-item'>" + cardType.getValue() + " | " + cd.getCardNumber() + "</a></button></li>");
                                                                }
                                                            } else {
                                                                response.sendRedirect("CustomerLogin.jsp");
                                                            }
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    %>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <select class="form-select w-75 mt-3" aria-label="Default select example" name="cardType" id="cardTypeSelect_checkout">
                                        <%
                                            try {
                                                List<CreditOrDebitCardType> cardTypeList = new CardTypeDAO().getTypeList();
                                                String cardTypeHTML = "<option selected disabled>Select card type</option>";
                                                for (CreditOrDebitCardType cardType : cardTypeList) {
                                                    cardTypeHTML += "<option value='" + cardType.getId() + "'>" + cardType.getValue() + "</option>";
                                                }
                                                out.print(cardTypeHTML);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        %>
                                    </select>
                                    <div class="mt-3">
                                        <label for="formFile" class="form-label fw-bold">Card number</label>
                                        <input class="form-control w-75" type="text" placeholder="Enter card number" id="cardNumber_checkout" name="cardNumber">
                                    </div>
                                    <div class="mt-3">
                                        <label for="formFile" class="form-label fw-bold">Name on card</label>
                                        <input class="form-control w-75" type="text" placeholder="Name on card" id="nameOnCard_checkout" name="nameOnCard">
                                    </div>
                                    <div class="d-flex w-75 mt-3">
                                        <div class="me-2">
                                            <label class="form-label fw-bold">Expire Date</label>
                                            <div class="input-group mb-3">
                                                <input type="text" class="form-control" placeholder="Year" aria-label="exYear" id="exYear_checkout" name="expYear">
                                                <input type="text" class="form-control" placeholder="Month" aria-label="exMonth" id="exMonth_checkout" name="expMonth">
                                            </div>
                                        </div>
                                        <div>
                                            <label for="formFile" class="form-label fw-bold">cvv</label>
                                            <input class="form-control" type="text" placeholder="cvv" id="cvv_checkout" name="cvv">
                                        </div>
                                    </div>
                                    <div class="mt-3">
                                        <input class="form-check-input cbx-itemSelect-cart me-2" type="checkbox" value="1" id="saveCardInfor_checkout" name="saveCardInfo">
                                        <label for="">Save card info</label>
                                    </div>
                                    <label for="shippingType_select_checkout" class="form-label fw-bold mt-3">Select Shipping Type</label>
                                    <select class="form-select w-75" aria-label="Default select example" name="shippingType" id="shippingType_checkout">
                                        <%
                                            if (customer != null) {
                                                List<ShippingType> shippingTypeList = new ShippingTypeDAO().ShippingTypeList();
                                                String addressListHTML = "<option selected disabled>Select Shipping Type</option>";
                                                for (ShippingType type : shippingTypeList) {
                                                    addressListHTML += "<option value='" + type.getId() + "'>" + type.getName() + " : " + type.getPrice() + "</option>";
                                                }
                                                out.print(addressListHTML);
                                            }
                                        %>
                                    </select>
                                    <label for="formFile" class="form-label fw-bold mt-3">Select Address</label>
                                    <select class="form-select w-75" aria-label="Default select example" name="address" id="address_checkout">
                                        <%
                                            if (customer != null) {
                                                List<Address> addressList = new AddressDAO().getSpecificCustomerAddressList(customer, hSession);
                                                String addressListHTML = "<option selected disabled>Select Address</option>";
                                                for (Address address : addressList) {
                                                    addressListHTML += "<option value='" + address.getId() + "'>" + address.getAddressLine1() + " " + address.getAddressLine2() + "</option>";
                                                }
                                                out.print(addressListHTML);
                                            }
                                        %>
                                    </select>
                                    <p class="fw-bold m-0 my-2">or</p>
                                    <%
                                        out.print("<button class='btn btn-primary' type='button' onclick='showNewAddressModal_checkout(" + customer.getCustomerId() + ")'>Add new address</button>");
                                    %>
                                </div>
                            </div>
                        </div>
                        <div class="col-4 p-0">
                            <div id="orderSummery_checkout">
                                <%
                                    if (customer != null) {
                                        out.print(new CalculateCheckoutSummery(0, cartItemList).generateOrderSummeryHTML());
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2"></div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="modalDef_checkout" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content" id="defModel-content-checkout">
                    <!-- ---------- -->

                    <div class="modal-header">
                        <h5 class="modal-title" id="defModal_title_checkout">Add new Address</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" id="defModal_body_checkout">
                        <form id="newAddress_checkout_form" class="was-validated">
                            <input type="text" name="addressLineOne" id="addressLineOne" class="form-control my-2" placeholder="Address line one" required>
                            <input type="text" name="addressLineTwo" id="addressLineTwo" class="form-control my-2" placeholder="Address line two" required>
                            <div class="d-flex my-2">
                                <input type="text" name="city" id="city" class="form-control me-1" placeholder="City" required>
                                <input type="text" name="stateOrDistrict" id="stateOrDistrict" class="form-control ms-1" placeholder="State/District" required>
                            </div>
                            <div class="d-flex my-2">
                                <input type="text" name="postalCode" id="postalCode" class="form-control me-1" placeholder="Postal code" required>
                                <select class="form-select ms-1" name="country" id="country" aria-label="Selecting country" required>
                                    <%
                                        List<Country> countryList = new CountryDAO().getCoutryList();
                                        String countryHTML = "<option value='' selected disabled>Select country</option>";
                                        for (Country country : countryList) {
                                            countryHTML += "<option value='" + country.getId() + "'>" + country.getCountryName() + "</option>";
                                        }
                                        out.print(countryHTML);
                                    %>        
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="saveAddress_checkout()">Save Address</button>
                    </div>

                    <!-- ---------- -->
                </div>
            </div>
        </div>

        <!-- Error Modal -->
        <div class="modal fade" id="errorModal-checkout" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content" id="errorModal-content-checkout">
                    <!-- ---------- -->

 

                    <!-- ---------- -->
                </div>
            </div>
        </div>
         <%@ include file="footer.jsp" %>
    </body>
</html>
