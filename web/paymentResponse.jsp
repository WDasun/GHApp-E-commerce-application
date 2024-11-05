<%-- 
    Document   : paymentResponse
    Created on : Oct 3, 2024, 7:40:09 PM
    Author     : ASUS
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Response</title>
        <%@ include file="links.jsp" %>
    </head>
    <body>
        <%@ include file="navBar.jsp" %>
        <% 
            String filePath = request.getParameter("filePath");
            String decodedFilePath = filePath.replace("\\", "\\\\");
        %>
        <div class="d-flex align-items-center justify-content-center vh-100" style="margin-top: 7rem">
            <div class="w-75">
                <p class="fs-3 text-center">Payment Successful!</p>
                <p class="text-center">Thank you for your purchase.<br> An invoice has been generated and sent to your email.</p>
                <div class="d-flex align-items-center justify-content-center">
                    <%
                        out.print("<button class='btn btn-primary w-25 me-2' onclick='downloadPdf_paymentResponse(\"" + decodedFilePath + "\")'>Download Invoice</button>");
                    %>
                    <button class="btn btn-primary w-25 ms-2" onclick="goToIndex_paymentResponse()">Go to Home</button>
                </div>
            </div>
        </div>
                     <%@ include file="footer.jsp" %>
    </body>
    <script>
        function goToIndex_paymentResponse(){
            window.location.href = 'index.jsp';
        }
        function downloadPdf_paymentResponse(filePath) {
            window.location.href = 'DowloadInvoice?filePath=' + encodeURIComponent(filePath);
        }
    </script>
</html>
