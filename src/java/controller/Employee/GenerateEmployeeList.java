/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Employee;

import connection.ConnectionBuilder;
import controller.systemUser.loadEmployeeListSystemUser;
import dao.EmployeeDAO;
import dto.CurrentEmployee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;
import org.hibernate.Session;
import security.Commen.EmployeeAuthorization;
import security.Log.CreateLog;

/**
 *
 * @author Dasun Wimukthi
 */
public class GenerateEmployeeList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

                    String html = "";

                    List<Employee> employeeList = new EmployeeDAO().getEmployeeList();
                    for (Employee employee : employeeList) {
                        if (employee.isStatus()) {
                            html += "<tr>\n"
                                    + "<td>" + employee.getEmployeeId() + "</td>\n"
                                    + "<td>" + employee.getFname() + " " + employee.getLname() + "</td>\n"
                                    + "<td>" + employee.getNic() + "</td>\n"
                                    + "<td>" + employee.getEmail() + "</td>\n"
                                    + "<td>" + employee.getLastUpdate() + "</td>\n"
                                    + "<td>Active</td>\n"
                                    + "<td class=\"\">\n"
                                    + "<button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateEmployee('" + employee.getEmployeeId() + "')\">Update</button>\n"
                                    + "<button class=\"btn btn-danger btn-categoryList-tableTow\" onclick=\"clickOnInactivateEmployee('" + employee.getEmployeeId() + "')\">Inactivate</button>\n"
                                    + "</td>\n"
                                    + "</tr>";
                        } else {
                            html += "<tr>\n"
                                    + "<td>" + employee.getEmployeeId() + "</td>\n"
                                    + "<td>" + employee.getFname() + " " + employee.getLname() + "</td>\n"
                                    + "<td>" + employee.getNic() + "</td>\n"
                                    + "<td>" + employee.getEmail() + "</td>\n"
                                    + "<td>" + employee.getLastUpdate() + "</td>\n"
                                    + "<td>Inactive</td>\n"
                                    + "<td class=\"\">\n"
                                    + "<button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateEmployee('" + employee.getEmployeeId() + "')\">Update</button>\n"
                                    + "<button class=\"btn btn-warning btn-categoryList-tableTow\" onclick=\"clickOnActivateEmployee('" + employee.getEmployeeId() + "')\">Activate</button>\n"
                                    + "</td>\n"
                                    + "</tr>";
                        }
                    }
                    out.print(html);
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
