/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.PaymentResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "DowloadInvoice", urlPatterns = {"/DowloadInvoice"})
public class DowloadInvoice extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reqFilePath = request.getParameter("filePath");

        // Set the content type for PDF
        response.setContentType("application/pdf");

        // Set the header to trigger a file download
        response.setHeader("Content-Disposition", "attachment; filename=\"EmployeeReport.pdf\"");

        // Get the PDF file from the server
        String filePath = reqFilePath;
        // Get file name
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);

        // Set the header to trigger a file download
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        File pdfFile = new File(filePath);

        // Check if file exists
        if (!pdfFile.exists()) {
            // Log the error instead of sending it in the response
            System.err.println("File not found: " + filePath);
            // Optionally redirect to an error page
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }

        // Set content length for the response
        response.setContentLength((int) pdfFile.length());

        // Write the PDF to the response output stream
        try (FileInputStream fileInputStream = new FileInputStream(pdfFile);
                OutputStream outputStream = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();  // Ensure all data is sent
        } catch (Exception e) {
            // Log the error instead of sending it in the response
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the PDF");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
