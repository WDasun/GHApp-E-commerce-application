/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.CategoryPromotion;

import dao.PromotionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductCategory;
import model.Promotion;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "LoadPromotionUpdateModal", urlPatterns = {"/LoadPromotionUpdateModal"})
public class LoadPromotionUpdateModal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String promotionId = request.getParameter("promotionId");
            try {
                Promotion promotion = new PromotionDAO().getRelatedPromotion(Integer.parseInt(promotionId));
                if (promotion != null) {
                    // Get category
                    Set<ProductCategory> categorySet = promotion.getProductCategories();
                    ProductCategory category = categorySet.iterator().next();
                    // Get Date and time
                    Date startDate = promotion.getStartDate();
                    String startDay = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
                    String startTime = new SimpleDateFormat("HH:mm").format(startDate);

                    Date endDate = promotion.getEndDate();
                    String endDay = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
                    String endTime = new SimpleDateFormat("HH:mm").format(endDate);

                    String promotionUpdateModalContentHtml = "<div class=\"modal-header\">\n"
                            + "                    <h5 class=\"modal-title\" id=\"exampleModalLabel\">Update Promotion</h5>\n"
                            + "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n"
                            + "                </div>\n"
                            + "                <div class=\"modal-body fs-6\">\n"
                            + "                    <label for=\"updatePromotionId\" class=\"form-label\">Promotion ID</label>\n"
                            + "                    <input type=\"text\" class=\"form-control mb-1\" id=\"updatePromotionId\" value=\"" + promotion.getId() + "\">\n"
                            + "                    <label for=\"updatePromotionName\" class=\"form-label\">Name</label>\n"
                            + "                    <input type=\"text\" class=\"form-control mb-1\" id=\"updatePromotionName\" value=\"" + promotion.getName() + "\">\n"
                            + "                    <label for=\"updatePromotionDescription\" class=\"form-label\">Description</label>\n"
                            + "                    <input type=\"text\" class=\"form-control mb-1\" id=\"updatePromotionDescription\" value=\"" + promotion.getDescription() + "\">\n"
                            + "                    <label for=\"updatePromotionDiscountRate\" class=\"form-label\">Discount Rate</label>\n"
                            + "                    <input type=\"text\" class=\"form-control mb-1\" id=\"updatePromotionDiscountRate\" value=\"" + promotion.getDiscountRate() + "\">\n"
                            + "                    <label for=\"updatePromotionCategory\" class=\"form-label\">Related Category</label>\n"
                            + "                    <input type=\"text\" class=\"form-control mb-1\" id=\"updatePromotionCategory\" value=\"" + category.getName() + "\" disabled>\n"
                            + "                    <label class=\"form-label\">Start Date and Time</label>\n"
                            + "                    <input type=\"date\" class=\"form-control mb-1\" id=\"updatePromotionStartDate\" value=\"" + startDay + "\">\n"
                            + "                    <input type=\"time\" class=\"form-control mb-1\" id=\"updatePromotionStartTime\" value=\"" + startTime + "\">\n"
                            + "                    <label class=\"form-label\">End Date and Time</label>\n"
                            + "                    <input type=\"date\" class=\"form-control mb-1\" id=\"updatePromotionEndDate\" value=\"" + endDay + "\">\n"
                            + "                    <input type=\"time\" class=\"form-control mb-1\" id=\"updatePromotionEndTime\" value=\"" + endTime + "\">\n"
                            + "                </div>\n"
                            + "                <div class=\"modal-footer\">\n"
                            + "                    <button type=\"button\" class=\"btn btn-primary w-25\" id=\"updateBtn-newPromotion\">Update</button>\n"
                            + "                    <button type=\"button\" class=\"btn btn-secondary w-25\" data-bs-dismiss=\"modal\">Close</button>\n"
                            + "                </div>";
                    out.print(promotionUpdateModalContentHtml);
                } else {
                    out.print("Data not valid !");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.print("Data not valid !");
            }
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
