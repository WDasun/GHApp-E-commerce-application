<%-- 
    Document   : Recovery
    Created on : Oct 6, 2024, 3:55:54 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recovery</title>
        <%@ include file="links.jsp" %>
        <script src="script/recovery.js"></script>
        <link rel="stylesheet" href="style/emplyeeLoginStyle.css">
    </head>
    <body>
         <main>
        <section class="c-navbar container-fluid border border-bottom-1">
            <div class="row">
                <div class="col-3"></div>
                <div class="col-6">
                    <div class="row h-100">
                <div class="d-flex align-items-center justify-content-center">
                    <div class="c-img-container d-flex align-items-center justify-content-center">
                        <p class="c-logo m-0 fw-bold my-2 fs-1">Glimmer <span class="c-text-heaven">Heaven</span></p>
                    </div>
                </div>
            </div>
                </div>
                <div class="col-3 d-flex align-items-center justify-content-end">
                    <a href="CustomerLogin.jsp" class="float-end me-3">Login</a>
                </div>
            </div>
        </section>
    </main>
    <section>
        <div class="container mt-5">
            <div class="row div-login justify-content-center">
                <div class="col-md-6">
                    <div class="h-100 w-100 d-flex align-items-center">
                        <div class="w-100">
                            <h3 class="card-title text-center">Account Recovery</h3>
                            <p class="text-center text-muted">Recovery</p>
                                <div class="fw-bold was-validated">
                                    <div class="mb-3">
                                        <label for="email_recovery" class="form-label">Email</label>
                                        <input type="text" class="form-control" id="email_recovery" placeholder="Enter email" required>
                                        <div class="invalid-feedback">Please enter a user email.</div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="RecoveryCode_recovery" class="form-label">Recovery code</label>
                                        <input type="text" class="form-control" id="recoveryCode_recovery" placeholder="Enter code" required>
                                        <div class="invalid-feedback">Please enter your recovery code.</div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="RecoveryCode_recovery" class="form-label">Enter new password</label>
                                        <input type="text" class="form-control" id="newPassword_recovery" placeholder="New password" required>
                                        <div class="invalid-feedback">Enter new password.</div>
                                    </div>
                                </div>
                            <button type="button" class="btn btn-warning fw-bold w-100" onclick="request_recovery()">Request</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
            <!-- Password reset Modal -->
    <div class="modal fade" id="passwordReset_modal" tabindex="-1" aria-labelledby="defModel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content" id="passwordReset_modal_content">
                <!-- ---------- -->
                <!-- Modal content Add new Address -->
                
                <!-- ---------- -->
            </div>
        </div>
    </div>
            <%@ include file="footer.jsp" %>
    </body>
</html>
