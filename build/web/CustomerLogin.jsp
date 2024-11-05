<%-- 
    Document   : CustomerLogin
    Created on : Jul 23, 2024, 12:14:04 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Login</title>
        <%@ include file="links.jsp" %>

        <script src="script/customerLogin.js"></script>

        <link rel="stylesheet" href="style/customerLoginStyle.css">
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <section class="vh-100">
            <div class="container mt-5">
                <div class="row div-login justify-content-center">
                    <div class="col-md-6">
                        <div class="h-100 w-100 d-flex align-items-center">
                            <div class="w-100">
                                <h3 class="card-title text-center">Customer Login</h3>
                                <p class="text-center text-muted">Customer login</p>
                                <form id="form-customerLogin">
                                    <div class="fw-bold was-validated">
                                        <div class="mb-3">
                                            <label for="email-customerLogin" class="form-label">Email</label>
                                            <input type="text" class="form-control" id="email-customerLogin" name="email" placeholder="Email" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="password" class="form-label">Password</label>
                                            <input type="password" class="form-control" id="password-customerLogin" name="password" placeholder="Password" required>
                                        </div>
                                    </div>
                                    <div class="form-check mb-3">
                                        <a href="RequestRecovery.jsp" class="float-end">Forgot Password</a>
                                        <a href="CustomerRegistration.jsp" class="float-end me-3">Register</a>  
                                    </div>
                                    <button type="button" class="btn btn-warning w-100" onclick="customerLoginProcess()">Log In</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Modal  Customer Login -->
        <div class="modal fade" id="defModelCustomerLogin" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
            <div class="modal-dialog modal-xl modal-dialog-centered">
                <div class="modal-content" id="defModel-content-customerLogin">
                    <!--------------> 

                    <!--------------> 
                </div>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
