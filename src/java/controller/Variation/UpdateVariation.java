/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Variation;

import connection.ConnectionBuilder;
import dao.CategoryDAO;
import dao.VariationDAO;
import dto.HTMLContents;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

@MultipartConfig
public class UpdateVariation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String variationId = request.getParameter("txt-variationId-update");
            String variationName = request.getParameter("txt-variationName-update");
            String catId = request.getParameter("select-categoryVariation-update");
            Session session = ConnectionBuilder.hibSession();
            if (!variationId.equals("") && (variationId != null
                    && !variationName.equals("") && (variationName != null)
                    && !catId.equals("") && catId != null)) {
                if (!(new VariationDAO().checkAvailability(new CategoryDAO().searchById(catId, session), variationName))) {
                    new VariationDAO().update(Integer.parseInt(variationId), variationName, new CategoryDAO().searchById(catId, session));
                    out.print(new HTMLContents().responseContent("Variation update completed !"));
                } else {
                    out.print(new HTMLContents().responseContent("Variation already exists !"));
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("Data not valid !");
            }
//            session.close();
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
