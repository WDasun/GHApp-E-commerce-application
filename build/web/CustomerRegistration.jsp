<%-- 
    Document   : CustomerRegistration
    Created on : Jul 16, 2024, 8:18:35 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Registration</title>

        <%@ include file="links.jsp" %>
        
        <script src="script/customerRegistration.js"></script>

        <link rel="stylesheet" href="style/customerRegistration.css">
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <section class="vh-100">
            <div class="container-fluid p-0">
                <div class="row pt-2 m-0">
                    <div class="col-3 p-0"></div>
                    <div class="col-6 p-0">
                        <p class="txt-heading-customerRegistration m-0 mt-3 mb-4"><span class="fs-2">Glimmer Heaven</span> Customer registration</p>
                    </div>
                    <div class="col-3 p-0"></div>
                </div>
                <div class="row div-registration m-0">
                    <div class="col-3 p-0"></div>
                    <div class="col-6 p-0">
                        <div class="rounded-3 h-100 div-registrationForm pt-2">
                            <div class="row h-100 m-0 ">
                                <form id="form-customerRegistration" class="p-0 ps-3 pe-3 pt-4">
                                    <div class="d-flex mb-3">
                                        <input type="text" class="form-control fw-bold me-3" id="txt-fname-customerRegistration" name="fname" value="" placeholder="Enter first name">
                                        <input type="text" class="form-control fw-bold" id="txt-lname-customerRegistration" name="lname" value="" placeholder="Enter last name">
                                    </div>
                                    <div class="mb-3">
                                        <input type="email" class="form-control fw-bold" id="txt-email-customerRegistration" name="email" value="" placeholder="Email">
                                    </div>
                                    <div class="mb-3">
                                        <input type="password" class="form-control fw-bold" id="txt-password-customerRegistration" name="password" value="" placeholder="Password">
                                    </div>
                                    <div class="mb-3">
                                        <input type="text" class="form-control fw-bold" id="txt-cnt-customerRegistration" name="cnt" value="" placeholder="Mobile number">
                                    </div>
                                    <div class="mb-3">
                                        <input type="date" class="form-control fw-bold" id="input-dob-customerRegistration" name="dob" value="">
                                    </div>
                                    <div class="d-flex align-items-center justify-content-center d-flex mb-3 mt-5">  
                                        <button type="button" class="btn btn-danger fw-bold w-50 btn-signup-customerRegistration" id="btn-signUp-customerRegistration">Sign Up</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-3 p-0"></div>
                </div>
            </div>
        </section>
            <!-- Modal  Customer Registration -->
    <div class="modal fade" id="defModelCustomerRegistration" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
        <div class="modal-dialog modal-xl modal-dialog-centered">
            <div class="modal-content" id="defModel-content-customerRegistration">
                 <!--------------> 

                 <!--------------> 
            </div>
        </div>
    </div>
            <%@ include file="footer.jsp" %>
    </body>
</html>
