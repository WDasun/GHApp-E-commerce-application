/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Employee;

import com.google.gson.Gson;
import dao.EmployeeDAO;
import dto.EncodeImageToBase64;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import model.Employee;

/**
 *
 * @author Dasun Wimukthi
 */
public class generateEmployeeData extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String empId = request.getParameter("empId");
            
            System.out.println(empId);
            
            if(!empId.equals("") && empId != null){
                Employee employee = new EmployeeDAO().searchById(empId);
                
                HashMap<String, String> data = new HashMap<>();
                data.put("empId", employee.getEmployeeId());
                data.put("fname", employee.getFname());
                data.put("lname", employee.getLname());                      
                data.put("dob", employee.getDob().toString());
                data.put("nic", employee.getNic());
                data.put("email", employee.getEmail());
                data.put("address", employee.getAddress());
                data.put("cnt1", employee.getCntOne());
                data.put("cnt2", employee.getCntTwo());
                data.put("gender", employee.getGender());
                data.put("image", new EncodeImageToBase64().encodeImage(employee.getProfilePicturePath()));                       
                
                out.print(new Gson().toJson(data));
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
