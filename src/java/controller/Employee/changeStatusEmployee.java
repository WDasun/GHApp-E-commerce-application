/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Employee;

import dao.EmployeeDAO;
import dao.SystemUserDAO;
import dto.CurrentEmployee;
import dto.HTMLContents;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Employee;
import model.SystemUser;
import security.Commen.EmployeeAuthorization;
import security.Log.CreateLog;

/**
 *
 * @author Dasun Wimukthi
 */
public class changeStatusEmployee extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            CurrentEmployee currentEmployee = (CurrentEmployee) session.getAttribute("CurrentEmployee");
            EmployeeAuthorization eA = new EmployeeAuthorization();

            if (currentEmployee != null && eA.CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f2")) {

                String empId = request.getParameter("id").trim();
                String newStatus = request.getParameter("status").trim();

                if (!empId.equals("") && empId != null
                        && !newStatus.equals("") && newStatus != null) {

                    boolean status = Boolean.parseBoolean(newStatus);
                    Employee emp = new EmployeeDAO().searchById(empId);
                    if (!emp.getEmployeeId().equals(currentEmployee.getEmployeeId())) {
                        new EmployeeDAO().updateStatus(status, emp);

                        if (status) {
                            out.print(new HTMLContents().responseContent("Employee acount activated !"));
                        } else {
                            out.print(new HTMLContents().responseContent("Employee acount inactivated !"));
                        }

                        // Log Employee Activity
                        new CreateLog("info", "Change status of a Employee.", currentEmployee.getEmployeeId(), changeStatusEmployee.class.getName()).employeeActivey();
                    }else{
                        out.print(new HTMLContents().responseContent("This is a logged account !"));
                    }

                } else {
                    out.print(new HTMLContents().responseContent("Data not valid !"));
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
