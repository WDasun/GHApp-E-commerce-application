/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.CardDetails;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.ConnectionBuilder;
import dao.CardDetailsDAO;
import dao.CustomerDAO;
import dto.CardDetailCustomerProfile;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CardDetails;
import model.Customer;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoadCardList", urlPatterns = {"/LoadCardList"})
public class LoadCardList extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            Session hSession = ConnectionBuilder.hibSession();
            Customer currentCustomer = (Customer) session.getAttribute("currentCustomerAccount");
            try {
                if(currentCustomer != null){
                Customer customer = new CustomerDAO().searchById(currentCustomer.getCustomerId(), hSession);
                List<CardDetails> cardDetailsSet = new CardDetailsDAO().searchByCustomer(customer);
                System.out.println("Card details set size : "+cardDetailsSet.size());
                ArrayList<CardDetailCustomerProfile> ccList = new ArrayList<>();
                for (CardDetails c : cardDetailsSet) {
                    
                    CardDetailCustomerProfile cc = new CardDetailCustomerProfile(
                            c.getId(),
                            c.getCreditOrDebitCardType().getValue(),
                            c.getCardNumber(),
                            c.getCardName(),
                            String.valueOf(c.getExpYear()),
                            String.valueOf(c.getExpMonth()));
                    
                    ccList.add(cc);
                    
                }
                
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                
                Gson gson = builder.create();
                out.print(gson.toJson(ccList));
            }
            } catch (Exception e) {
                e.printStackTrace();
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
