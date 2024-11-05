<%-- 
    Document   : GetRecovery
    Created on : Oct 6, 2024, 4:07:32 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request recovery</title>
        <%@ include file="links.jsp" %>
        <script src="script/requestRecovery.js"></script>
        <link rel="stylesheet" href="style/emplyeeLoginStyle.css">
    </head>
    <body>
        <div class="vh-100">
            <main>
            <section class="c-navbar container-fluid border border-bottom-1">
                <div class="row h-100">

                    <div class="d-flex align-items-center justify-content-center">
                        <div class="c-img-container d-flex align-items-center justify-content-center">
                            <p class="c-logo m-0 fw-bold my-2 fs-1">Glimmer <span class="c-text-heaven">Heaven</span></p>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <section>
            <div class="container mt-5">
                <div class="row div-login justify-content-center">
                    <div class="col-md-6">
                        <div class="h-100 w-100 d-flex align-items-center">
                            <div class="w-100" id="div_requestRecivery">
                                <h3 class="card-title text-center">Request Recovery</h3>
                                <p class="text-center text-muted">Recovery</p>
                                <div class="fw-bold was-validated">
                                    <div class="mb-3">
                                        <label for="email_recovery" class="form-label">Email</label>
                                        <input type="text" class="form-control" id="email_requestRecovery" placeholder="Enter email" required>
                                        <div class="invalid-feedback">Please enter a user email.</div>
                                    </div>     
                                </div>
                                <button type="button" class="btn btn-warning fw-bold w-100" onclick="requestRecovery()">Request</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
