/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Category;

import connection.ConnectionBuilder;
import dao.CategoryDAO;
import dto.HTMLContents;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductCategory;
import org.hibernate.Session;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "SaveCategory", urlPatterns = {"/SaveCategory"})
@MultipartConfig
public class SaveCategory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String categoryId = request.getParameter("categoryId").trim();
            String parentCategoryId = request.getParameter("select-parentCategory").trim();
            String name = request.getParameter("categoryName").trim();
            boolean status = true;
            Session session = ConnectionBuilder.hibSession();
            if (!categoryId.equals("") && (categoryId != null)
                    && !parentCategoryId.equals("") && (parentCategoryId != null)
                    && !name.equals("") && (name != null)) {
                if (parentCategoryId.equals("PARENT")) {
                    ProductCategory parentCategory = new ProductCategory();
                    parentCategory.setId(categoryId);
                    parentCategory.setName(name);
                    parentCategory.setStatus(status);
                    new CategoryDAO().save(parentCategory);
                    out.print(new HTMLContents().responseContent("Parent category saved !"));
                } else {
                    ProductCategory pc = new ProductCategory();
                    pc.setId(categoryId);
                    pc.setName(name);
                    pc.setProductCategory(new CategoryDAO().searchById(parentCategoryId, session));
                    pc.setStatus(status);
                    new CategoryDAO().save(pc);
                    out.print(new HTMLContents().responseContent("Sub category saved !"));
                }
            } else {
                out.print(new HTMLContents().responseContent("Data not valid !"));
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
