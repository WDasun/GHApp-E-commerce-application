/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Employee;

import connection.ConnectionBuilder;
import dao.EmployeeDAO;
import dto.CurrentEmployee;
import dto.ExctractFileExtension;
import dto.HTMLContents;
import common.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Employee;
import org.hibernate.Session;
import security.Commen.EmployeeAuthorization;
import security.Log.CreateLog;

/**
 *
 * @author Dasun Wimukthi
 */
@MultipartConfig
public class UpdateEmployee extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            CurrentEmployee currentEmployee = (CurrentEmployee) session.getAttribute("CurrentEmployee");
            EmployeeAuthorization eA = new EmployeeAuthorization();

            if (currentEmployee != null && eA.CheckFirstLevelAuthorization(currentEmployee.getUserName(), "f2")) {

                String empId = request.getParameter("empId");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String gender = request.getParameter("gender");
                String dob = request.getParameter("dob");
                String nic = request.getParameter("nic");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                String cnt1 = request.getParameter("cnt1");
                String cnt2 = request.getParameter("cnt2");
                Part image = request.getPart("image");

                if (!empId.equals("") && empId != null
                        && !fname.equals("") && fname != null
                        && !lname.equals("") && lname != null
                        && !gender.equals("") && gender != null
                        && !dob.equals("") && dob != null
                        && !nic.equals("") && nic != null
                        && !email.equals("") && email != null
                        && !address.equals("") && address != null
                        && !cnt1.equals("") && cnt1 != null
                        && !cnt2.equals("") && cnt2 != null) {

                    Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

                    LocalDate ld = LocalDate.parse(dob);
                    Date dateOfBirth = Date.valueOf(ld);

                    Employee emp = new EmployeeDAO().searchById(empId);
                    emp.setFname(fname);
                    emp.setLname(lname);
                    emp.setNic(nic);
                    emp.setDob(dateOfBirth);
                    emp.setGender(gender);
                    emp.setEmail(email);
                    emp.setAddress(address);
                    emp.setCntOne(cnt1);
                    emp.setCntTwo(cnt2);
                    emp.setLastUpdate(lastUpdate);

                    String imagePath = "";
                    if (image.getSize() > 0) {
                        imagePath = new Paths().getEmployeeProfilePicPath() + empId + "ProfilePic" + "." + new ExctractFileExtension().getFileExtension(image);
                        emp.setProfilePicturePath(imagePath);
                    }

                    new EmployeeDAO().update(emp);

                    if (image.getSize() > 0) {
                        FileInputStream fin1 = (FileInputStream) image.getInputStream();
                        FileOutputStream fout1 = new FileOutputStream(imagePath);
                        byte[] buffer1 = new byte[1024];
                        int byteRead1 = 0;

                        while ((byteRead1 = fin1.read(buffer1)) != -1) {
                            fout1.write(buffer1, 0, byteRead1);
                        }
                        fin1.close();
                        fout1.close();
                    }

                    out.print(new HTMLContents().responseContent("Employee Updated !"));

                    // Log Employee Activity
                    new CreateLog("info", "Update a Employee.", currentEmployee.getEmployeeId(), UpdateEmployee.class.getName()).employeeActivey();

                } else {
                    out.print(new HTMLContents().responseContent("Employee data not valid !"));
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
