/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Checkout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.CardDetailsDAO;
import dto.CardDetailDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CardDetails;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "GetCardInfo", urlPatterns = {"/GetCardInfo"})
public class GetCardInfo extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String idString = request.getParameter("id");
            if (idString != null) {
                int id = Integer.parseInt(idString);
                try {
                    CardDetails cd = new CardDetailsDAO().searchById(id);
                    CardDetailDTO cDTO = new CardDetailDTO();
                    cDTO.setId(cd.getId());
                    cDTO.setCustomerId(cd.getCustomer().getCustomerId());
                    cDTO.setCardTypeId(cd.getCreditOrDebitCardType().getId());
                    cDTO.setCardNumber(cd.getCardNumber());
                    cDTO.setCardName(cd.getCardName());
                    cDTO.setExYear(cd.getExpYear());
                    cDTO.setExMonth(cd.getExpMonth());
                    cDTO.setIsDefault(cd.isIsDefault());
                    
                    GsonBuilder gBuilder = new GsonBuilder();
                    Gson gson = gBuilder.create();
                    
                    String gCardDetails = gson.toJson(cDTO);
                    
                    out.print(gCardDetails);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
