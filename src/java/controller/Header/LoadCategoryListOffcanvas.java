/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Header;

import dao.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductCategory;

/**
 *
 * @author ASUS
 */
public class LoadCategoryListOffcanvas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String html = "";

            List<ProductCategory> ParentCategoryList = new CategoryDAO().getParentCategoryList();
            for (ProductCategory parentCategory : ParentCategoryList) {
                html += "<div class=\"pt-4 pb-3 border-bottom border-2\">\n"
                        + "<p class=\"ms-5 txt-patentCategory\">"+parentCategory.getName()+"</p>";
                List<ProductCategory> subCategory = new CategoryDAO().getSubCategoryListOfParent(parentCategory);

                for (ProductCategory sc : subCategory) {
                    html += "<button class=\"btn p-0 ps-5 d-flex align-items-center btn-subCategory\" onclick=\"clickOnSubCategory_header('"+sc.getId()+"')\">"+sc.getName()+"</button>";
                }
                html += "</div>";
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
