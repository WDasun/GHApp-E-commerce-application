/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Customer;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.CustomerDAO;
import dto.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.ShoppingCart;
import model.ShoppingCartItem;
import security.Validation.CurrentDatecompare;
import security.Validation.PasswordValidation;
import security.Validation.EmailValidation;
import security.Validation.MobileNumberValidation;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "CustomerRegistration", urlPatterns = {"/CustomerRegistration"})
@MultipartConfig
public class CustomerRegistration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String cnt = request.getParameter("cnt");
            String dob = request.getParameter("dob");

            Customer c = new Customer();
            CustomerDAO cDAO = new CustomerDAO();

            try {
                if (!fname.equals("") || fname != null
                        && !lname.equals("") || lname != null
                        && !email.equals("") || email != null
                        && !cnt.equals("") || cnt != null
                        && !password.equals("") || password != null
                        && !dob.equals("") || dob != null) {

                    Date bDate = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                    String year = new SimpleDateFormat("yyyy").format(bDate);
                    String month = new SimpleDateFormat("MM").format(bDate);
                    String date = new SimpleDateFormat("dd").format(bDate);
                    if (new EmailValidation().patternMatch(email)
                            && new PasswordValidation().patternMatch(password)
                            && new MobileNumberValidation().patternMatch(cnt)
                            && new CurrentDatecompare().beforCurrentDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date))) {
                        if (new CustomerDAO().checkEmailAvailability(email)) {
                            c.setFname(fname);
                            c.setLname(lname);
                            c.setEmail(email);
                            c.setPassword(password);
                            c.setCntNumber(cnt);

                            c.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(dob));

                            Date currentDate = new Date();

                            c.setCreatedAt(currentDate);
                            c.setUpdatedAt(currentDate);

                            c.setStatus(true);

                            ShoppingCart shoppingCart = new ShoppingCart();
                            shoppingCart.setCustomer(c);

                            try {
                                cDAO.save(c);
                                new CartDAO().save(shoppingCart);

                                //Set Cart items to db if customer has select items to the cart befor the registration
                                HttpSession session = request.getSession();
                                ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) session.getAttribute("sessionCartItemList");
                                if (cartItemList != null) {
                                    for (CartItem cartItem : cartItemList) {
                                        ShoppingCartItem sci = new ShoppingCartItem();
                                        Customer customer = new CustomerDAO().searchByEmail(email);
                                        ShoppingCart sc = new CartDAO().searchByCustomer(customer);

                                        sci.setShoppingCart(sc);
                                        sci.setProductItem(cartItem.getProductItem());
                                        sci.setQty(cartItem.getQty());

                                        new CartItemDAO().save(sci);
                                    }
                                }

                                out.print("{\"registrationStatus\": true, \"message\": \"Registration completed !\", \"redirectUrl\": \"CustomerLogin.jsp\"}");
                            } catch (Exception e) {
                                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred !");
                                e.printStackTrace();
                            }
                        } else {
                            out.print("{\"registrationStatus\": false, \"message\": \"Email not valid !\"}");
                        }
                    } else {
                        out.print("{\"registrationStatus\": false, \"message\": \"Data not valid !\"}");
                    }

                } else {
                    out.print("{\"registrationStatus\": false, \"message\": \"Data not valid !\"}");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.print("{\"registrationStatus\": false, \"message\": \"Data not valid !\"}");
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
