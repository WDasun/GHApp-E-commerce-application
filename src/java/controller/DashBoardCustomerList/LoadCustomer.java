package controller.DashBoardCustomerList;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "LoadCustomer", urlPatterns = {"/LoadCustomer"})
public class LoadCustomer extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String customerId = request.getParameter("customerId");
            try {
                Customer c = new CustomerDAO().searchById(Integer.valueOf(customerId));
                String html = "<div class=\"modal-header\">\n"
                        + "                    <h5 class=\"modal-title\" id=\"exampleModalLabel\">Customer ID : "+c.getCustomerId()+"</h5>\n"
                        + "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n"
                        + "                </div>\n"
                        + "                <div class=\"modal-body\">\n"
                        + "                    <div class=\"mt-2\">\n"
                        + "                        <label for=\"name\" class=\"form-label fw-bold\">Full Name</label>\n"
                        + "                        <input id=\"name\" type=\"text\" class=\"form-control\" value=\""+c.getFname()+" "+c.getLname()+"\" disabled>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"mt-2\">\n"
                        + "                        <label for=\"email\" class=\"form-label fw-bold\">Email</label>\n"
                        + "                        <input id=\"email\" type=\"text\" class=\"form-control\" value=\""+c.getEmail()+"\" disabled>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"mt-2\">\n"
                        + "                        <label for=\"cnt\" class=\"form-label fw-bold\">Contact Number</label>\n"
                        + "                        <input id=\"cnt\" type=\"text\" class=\"form-control\" value=\""+c.getCntNumber()+"\" disabled>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"mt-2\">\n"
                        + "                        <label for=\"cDate\" class=\"form-label fw-bold\">AC crated date</label>\n"
                        + "                        <input id=\"cDate\" type=\"text\" class=\"form-control\" value=\""+c.getCreatedAt()+"\" disabled>\n"
                        + "                    </div>\n"
                        + "                    <div class=\"mt-2\">\n"
                        + "                        <label for=\"lUpdate\" class=\"form-label fw-bold\">AC last update</label>\n"
                        + "                        <input id=\"lUpdate\" type=\"text\" class=\"form-control\" value=\""+c.getUpdatedAt()+"\" disabled>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "                <div class=\"modal-footer d-flex justify-content-center\">\n"
                        + "                    <button type=\"button\" class=\"btn btn-secondary w-25\" data-bs-dismiss=\"modal\">Close</button>\n"
                        + "                </div>";
                out.print(html);
            } catch (Exception e) {
                out.print("<div class=\"modal-header\">\n"
                        + "                    <h5 class=\"modal-title\" id=\"exampleModalLabel\">Response</h5>\n"
                        + "                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n"
                        + "                </div>\n"
                        + "                <div class=\"modal-body fs-5\">\n"
                        + "                    <p class=\"text-center m-0\">Customer not found !</p>\n"
                        + "                </div>\n"
                        + "                <div class=\"modal-footer d-flex justify-content-center\">\n"
                        + "                    <button type=\"button\" class=\"btn btn-secondary w-25\" data-bs-dismiss=\"modal\">Close</button>\n"
                        + "                </div>");
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
