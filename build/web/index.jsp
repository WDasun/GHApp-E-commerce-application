<%-- 
    Document   : index
    Created on : Jun 25, 2024, 6:44:20 PM
    Author     : ASUS
--%>

<%@page import="dao.PromotionDAO"%>
<%@page import="dto.EncodeImageToBase64"%>
<%@page import="model.ProductItem"%>
<%@page import="dao.ItemDAO"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page import="connection.ConnectionBuilder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <%
            Session hSession = ConnectionBuilder.hibSession();
        %>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Page</title>

        <%@ include file="links.jsp" %>

        <link rel="stylesheet" href="style/indexStyle.css">
        <script src="script/IndexBestSellerProduct.js"></script>
        <script src="script/IndexForYou.js"></script>
    </head>

    <body>
        <main>
            <%@ include file="navBar.jsp" %>
            <section class="c-carousel d-none d-lg-block">
                <div id="carouselExampleCaptions" class="carousel slide mx-5 my-4" data-bs-ride="carousel">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner c-carousel-item">
                        <div class="carousel-item active c-item">
                            <img src="https://images.unsplash.com/photo-1472851294608-062f824d29cc?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0,0,0,0.2)">
                                <h5>Welcome to Glimmer Heaven</h5>
                                <p>Where every sparkle tells a story. Discover elegance, style, and timeless treasures tailored just for you.</p>
                            </div>
                        </div>
                        <div class="carousel-item c-item">
                            <img src="https://plus.unsplash.com/premium_photo-1664202526744-516d0dd22932?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0,0,0,0.2)">
                                <h5>Step into Elegance</h5>
                                <p>Discover the perfect blend of fashion and comfort with every pair. Let your style shine with Glimmer Heaven.</p>
                            </div>
                        </div>
                        <div class="carousel-item c-item">
                            <img src="https://images.unsplash.com/photo-1519033628719-72e1861f20cf?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" class="d-block w-100" alt="...">
                            <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0,0,0,0.2)">
                                <h5>Work with Excellence</h5>
                                <p>Where creativity meets productivity. Transform your workspace with Glimmer Heaven’s inspired office essentials.</p>
                            </div>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </section>

            <!-- Best seller product -->
            <section class="">
                <div class="container-fluid">
                    <div class="row w-100 py-5">
                        <p class="txt-sectionHeadTextStyle-1 text-center">Best seller product</p>
                    </div>
                    <div class="row">
                        <div class="col d-flex align-items-center justify-content-end">
                            <button onclick="rightBtnClick()" class="btn btn-secondary w-25 h-25 me-5"><i class="fa-solid fa-chevron-left fa-2xl"></i></button>
                        </div>
                        <div class="col mx-2 border rounded-3">
                            <div id="product_placeOne">

                            </div>
                        </div>
                        <div class="col mx-2 border rounded-3">
                            <div id="product_placeTwo">

                            </div>
                        </div>
                        <div class="col mx-2 border rounded-3">
                            <div id="product_placeThree">

                            </div>
                        </div>
                        <div class="col mx-2 border rounded-3">
                            <div id="product_placeFour">

                            </div>
                        </div>
                        <div class="col d-flex align-items-center justify-content-start">
                            <button onclick="leftBtnClick()" class="btn btn-secondary w-25 h-25 ms-5"><i class="fa-solid fa-chevron-left fa-flip-horizontal fa-2xl"></i></button>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Latest Deals -->
            <section>
                <div class="container-fluid">
                    <div class="row">
                        <p class="txt-sectionHeadTextStyle-1 text-center my-5">Latest Deals</p>
                    </div>
                    <div class="row">
                        <div class="col d-flex align-items-center justify-content-end">
                            <img src="/GHApp/images/add4.webp" alt="" class="w-75 rounded-3" style="height: 400px;">
                        </div>
                        <div class="col d-flex align-items-center justify-content-center">
                            <img src="/GHApp/images/add3.webp" alt="" class="w-75 rounded-3" style="height: 400px;">
                        </div>
                        <div class="col d-flex align-items-center justify-content-start">
                            <img src="/GHApp/images/add2.webp" alt="" class="w-75 rounded-3" style="height: 400px;">
                        </div>
                    </div>
                </div>
            </section>

            <!-- For you -->
            <section>
                <div class="container-fluid px-5 pb-5">
                    <div class="row px-5 pb-3">
                        <p class="txt-sectionHeadTextStyle-1 pt-5">For you</p>
                    </div>
                    <div id="div-forYou-index">
                        <%                            out.print(new ItemDAO().generateItemRowIndex(0, 5, hSession));
                        %>
                    </div>
                    <div class="d-flex align-items-center justify-content-center my-3">
                        <button class="btn fs-5 border-bottom" onclick="loadMoreItemRow_index()">Load more..</button>
                    </div>
                </div>
            </section>

        </main>
                        <%@ include file="footer.jsp" %>
    </body>

    <%

    %>
</html>