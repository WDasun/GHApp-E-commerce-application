/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.CategoryPromotion;

import connection.ConnectionBuilder;
import dao.CategoryDAO;
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
import model.ProductCategory;
import model.Promotion;
import org.hibernate.Session;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "SaveCategoryPromotion", urlPatterns = {"/SaveCategoryPromotion"})
public class SaveCategoryPromotion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {

                String newPromotionName = request.getParameter("newPromotionName").trim();
                String newPromotionDescription = request.getParameter("newPromotionDescription").trim();
                String newPromotionDiscountRate = request.getParameter("newPromotionDiscountRate");
                String newPromotionCategory = request.getParameter("newPromotionCategory");
                String newPromotionStartDate = request.getParameter("newPromotionStartDate");
                String newPromotionStartTime = request.getParameter("newPromotionStartTime");
                String newPromotionEndDate = request.getParameter("newPromotionEndDate");
                String newPromotionEndTime = request.getParameter("newPromotionEndTime");

                String startDateString = newPromotionStartDate + " " + newPromotionStartTime;
                String endDateString = newPromotionEndDate + " " + newPromotionEndTime;

                Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDateString);
                Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateString);

                if (startDate.before(endDate)) {

                    if (newPromotionName != null && !newPromotionName.equals("")
                            && newPromotionDescription != null && !newPromotionDescription.equals("")
                            && newPromotionDiscountRate != null && !newPromotionDiscountRate.equals("")
                            && newPromotionCategory != null && !newPromotionCategory.equals("")
                            && newPromotionStartDate != null && !newPromotionStartDate.equals("")
                            && newPromotionStartTime != null && !newPromotionStartTime.equals("")
                            && newPromotionEndDate != null && !newPromotionEndDate.equals("")
                            && newPromotionEndTime != null && !newPromotionEndTime.equals("")) {

                        Session session = ConnectionBuilder.hibSession();
                        ProductCategory pc = new CategoryDAO().searchById(newPromotionCategory, session);
                        Promotion promotion = new Promotion();
                        promotion.setName(newPromotionName);
                        promotion.setDescription(newPromotionDescription);
                        promotion.setDiscountRate(Double.parseDouble(newPromotionDiscountRate));
                        promotion.setStartDate(startDate);
                        promotion.setEndDate(endDate);

                        promotion.setStatus(true);

                        new PromotionDAO().save(pc, promotion);
                        out.print("Promotion save successful !");

                    } else {
                        out.print("Data not valid !");
                    }

                } else {
                    out.print("Date not compatible !");
                }

            } catch (Exception e) {
                e.printStackTrace();
                out.print("Promotion Failed !");
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
