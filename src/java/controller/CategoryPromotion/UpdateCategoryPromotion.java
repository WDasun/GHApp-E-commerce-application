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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Promotion;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "UpdateCategoryPromotion", urlPatterns = {"/UpdateCategoryPromotion"})
public class UpdateCategoryPromotion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                String updatePromotionId = request.getParameter("updatePromotionId");
                String updatePromotionName = request.getParameter("updatePromotionName").trim();
                String updatePromotionDescription = request.getParameter("updatePromotionDescription").trim();
                String updatePromotionDiscountRate = request.getParameter("updatePromotionDiscountRate");
                String updatePromotionStartDate = request.getParameter("updatePromotionStartDate");
                String updatePromotionStartTime = request.getParameter("updatePromotionStartTime");
                String updatePromotionEndDate = request.getParameter("updatePromotionEndDate");
                String updatePromotionEndTime = request.getParameter("updatePromotionEndTime");

                String startDateString = updatePromotionStartDate + " " + updatePromotionStartTime;
                String endDateString = updatePromotionEndDate + " " + updatePromotionEndTime;

                Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDateString);
                Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateString);

                if (startDate.before(endDate)) {

                    if (updatePromotionId != null && !updatePromotionId.equals("")
                            && updatePromotionName != null && !updatePromotionName.equals("")
                            && updatePromotionDescription != null && !updatePromotionDescription.equals("")
                            && updatePromotionDiscountRate != null && !updatePromotionDiscountRate.equals("")
                            && updatePromotionStartDate != null && !updatePromotionStartDate.equals("")
                            && updatePromotionStartTime != null && !updatePromotionStartTime.equals("")
                            && updatePromotionEndDate != null && !updatePromotionEndDate.equals("")
                            && updatePromotionEndTime != null && !updatePromotionEndTime.equals("")) {

                        Promotion promotion = new PromotionDAO().getRelatedPromotion(Integer.parseInt(updatePromotionId));
                        promotion.setName(updatePromotionName);
                        promotion.setDescription(updatePromotionDescription);
                        promotion.setDiscountRate(Double.parseDouble(updatePromotionDiscountRate));

                        promotion.setStartDate(startDate);

                        promotion.setEndDate(endDate);

                        new PromotionDAO().update(promotion);
                        out.print("Promotion update successful !");

                    } else {
                        out.print("Data not valid !");
                    }

                } else {
                    out.print("Date not compatible !");
                }

            } catch (Exception e) {
                e.printStackTrace();
                out.print("Promotion update failed !");
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
