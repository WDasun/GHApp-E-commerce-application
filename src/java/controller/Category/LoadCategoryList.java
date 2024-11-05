/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Category;

import dao.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductCategory;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoadCategoryList", urlPatterns = {"/LoadCategoryList"})
public class LoadCategoryList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            CategoryDAO cDAO = new CategoryDAO();
            List<ProductCategory> productCategoryList = cDAO.getCategoryList();
            String html = "";
            for (ProductCategory productCategory : productCategoryList) {
                String parentCategory = "";
                if(productCategory.getProductCategory() == null){
                    parentCategory = "Parent Category";
                }else{
                    parentCategory = productCategory.getProductCategory().getId()+" : "+productCategory.getProductCategory().getName();
                }
                if (productCategory.isStatus()) {
                    html += "<tr>\n"
                            + "                        <td>" + productCategory.getId() + "</td>\n"
                            + "                        <td>" + productCategory.getName() + "</td>\n"
                            + "                        <td>" + parentCategory + "</td>\n"
                            + "                        <td>Active</td>\n"
                            + "                        <td class=\"\">\n"
                            + "                            <button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateCategory('" + productCategory.getId() + "')\">Update</button>\n"
                            + "                            <button class=\"btn btn-danger btn-categoryList-tableTow\" onclick=\"clickOnInactivateCategory('" + productCategory.getId() + "')\">Inactivate</button>\n"
                            + "                        </td>\n"
                            + "                    </tr>";
                } else {
                    html += "<tr class=\"table-secondary\">\n"
                            + "                        <td>" + productCategory.getId() + "</td>\n"
                            + "                        <td>" + productCategory.getName() + "</td>\n"
                            + "                        <td>" + parentCategory + "</td>\n"
                            + "                        <td class=\"fw-bold\">Inactive</td>\n"
                            + "                        <td class=\"\">\n"
                            + "                            <button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateCategory('" + productCategory.getId() + "')\">Update</button>\n"
                            + "                            <button class=\"btn btn-warning btn-categoryList-tableTow\" onclick=\"clickOnActivateCategory('" + productCategory.getId() + "')\">Activate</button>\n"
                            + "                        </td>\n"
                            + "                    </tr>";
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
