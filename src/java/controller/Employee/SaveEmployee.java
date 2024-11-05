package controller.Employee;


import dao.EmployeeDAO;
import dto.CurrentEmployee;
import dto.ExctractFileExtension;
import dto.HTMLContents;
import common.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Employee;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import security.Commen.EmployeeAuthorization;
import security.Log.CreateLog;
import security.Validation.CurrentDatecompare;
import security.Validation.EmailValidation;
import security.Validation.MobileNumberValidation;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "SaveEmployee", urlPatterns = {"/SaveEmployee"})
@MultipartConfig
public class SaveEmployee extends HttpServlet {

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

                if (empId != null && !empId.equals("")
                        && fname != null && !fname.equals("")
                        && lname != null && !lname.equals("")
                        && gender != null && !gender.equals("")
                        && dob != null && !dob.equals("")
                        && nic != null && !nic.equals("")
                        && email != null && !email.equals("")
                        && address != null && !address.equals("")
                        && cnt1 != null && !cnt1.equals("")
                        && cnt2 != null && !cnt2.equals("")
                        && image.getSize() > 0) {

                    String imagePath = new Paths().getEmployeeProfilePicPath() + empId + "ProfilePic" + "." + new ExctractFileExtension().getFileExtension(image);

                    Timestamp dateOfHire = Timestamp.valueOf(LocalDateTime.now());
                    Timestamp createdDate = dateOfHire;
                    Timestamp lastUpdate = createdDate;

                    LocalDate ld = LocalDate.parse(dob);
                    Date dateOfBirth = Date.valueOf(ld);

                    int bYeat = Integer.parseInt(new SimpleDateFormat("yyyy").format(dateOfBirth));
                    int bMonth = Integer.parseInt(new SimpleDateFormat("MM").format(dateOfBirth));
                    int bDate = Integer.parseInt(new SimpleDateFormat("dd").format(dateOfBirth));

                    // Validation
                    if (new EmailValidation().patternMatch(email)
                            && new MobileNumberValidation().patternMatch(cnt1)
                            && new MobileNumberValidation().patternMatch(cnt2)
                            && new CurrentDatecompare().beforCurrentDate(bYeat, bMonth, bDate)) {
                        Employee emp = new Employee();
                        emp.setEmployeeId(empId);
                        emp.setFname(fname);
                        emp.setLname(lname);
                        emp.setNic(nic);
                        emp.setDob(dateOfBirth);
                        emp.setGender(gender);
                        emp.setEmail(email);
                        emp.setAddress(address);
                        emp.setCntOne(cnt1);
                        emp.setCntTwo(cnt2);
                        emp.setDateOfHire(dateOfHire);
                        emp.setProfilePicturePath(imagePath);
                        emp.setCreatedDate(createdDate);
                        emp.setLastUpdate(lastUpdate);
                        emp.setStatus(true);

                        new EmployeeDAO().save(emp);

                        FileInputStream fin1 = (FileInputStream) image.getInputStream();
                        FileOutputStream fout1 = new FileOutputStream(imagePath);
                        byte[] buffer1 = new byte[1024];
                        int byteRead1 = 0;

                        while ((byteRead1 = fin1.read(buffer1)) != -1) {
                            fout1.write(buffer1, 0, byteRead1);
                        }
                        fin1.close();
                        fout1.close();

                        out.print(new HTMLContents().responseContent("Employee Saved !"));
                        
                        // Log Employee Activity
                        new CreateLog("info", "Saving a Employee.", currentEmployee.getEmployeeId(), SaveEmployee.class.getName()).employeeActivey();
                        
                    } else {
                        out.print(new HTMLContents().responseContent("Employee data not valid !"));
                    }

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
