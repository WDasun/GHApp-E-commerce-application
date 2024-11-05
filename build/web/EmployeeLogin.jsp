<%-- 
    Document   : EmployeeLogin
    Created on : Jun 24, 2024, 11:23:57 PM
    Author     : ASUS
--%>
<%@page import="dto.CurrentEmployee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Employee login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <script src="https://kit.fontawesome.com/d13ca0de67.js" crossorigin="anonymous"></script>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

        <script src="script/employeeLogin.js"></script>

        <link rel="stylesheet" href="style/emplyeeLoginStyle.css">

    </head>

    <%
        CurrentEmployee currentEmployee = (CurrentEmployee) session.getAttribute("CurrentEmployee");
        if (currentEmployee != null) {
            response.sendRedirect(request.getContextPath() + "/dashBoard.jsp");
        }
    %> 

    <body>
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
                            <div class="w-100">
                                <h3 class="card-title text-center">Employee Login</h3>
                                <p class="text-center text-muted">Employee Login</p>
                                <form id="loginForm">
                                    <div class="fw-bold was-validated">
                                        <div class="mb-3">
                                            <label for="username" class="form-label">Username</label>
                                            <input type="text" class="form-control" id="username" name="username" placeholder="user name" required>
                                            <div class="invalid-feedback">Please enter a user name.</div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="password" class="form-label">Password</label>
                                            <input type="password" class="form-control" id="password" name="password" placeholder="password" required>
                                            <div class="invalid-feedback">Please enter your password.</div>
                                        </div>
                                    </div>
                                    <button type="button" class="btn btn-warning w-100" id="btn-login">Log In</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>

</html>
