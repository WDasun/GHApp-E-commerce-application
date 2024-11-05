/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Product;

import com.google.gson.Gson;
import connection.ConnectionBuilder;
import dao.CategoryDAO;
import dao.ProductDAO;
import dto.EncodeImageToBase64;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductCategory;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
public class GenerateUpdateMDL extends HttpServlet {

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
            

            String productId = request.getParameter("productId");
            Session session = ConnectionBuilder.hibSession();
            if (!productId.equals("") && productId != null) {
                
                Product product = new ProductDAO().searchById(productId);
                
                HashMap<String, String> data = new HashMap<>();
                data.put("productId", product.getId());
                data.put("productName", product.getName());
                data.put("productDescription", product.getDescription());
                
                System.out.println(product.getDescription());
                
                String categoryListHtml = "";
                List<ProductCategory> catagoryList = new CategoryDAO().getSubCategoryList(session);
                for (ProductCategory productCategory : catagoryList) {
                    if(productCategory.getId().equals(product.getProductCategory().getId())){
                        categoryListHtml += "<option value=\""+productCategory.getId()+"\" selected>"+productCategory.getId()+" : "+productCategory.getName()+"</option>";
                    }else{
                        categoryListHtml += "<option value=\""+productCategory.getId()+"\">"+productCategory.getId()+" : "+productCategory.getName()+"</option>";
                    }
                }
                
                data.put("categoryListHTML", categoryListHtml);
                data.put("imageOne", new EncodeImageToBase64().encodeImage(product.getImagePathOne()));
                data.put("imageTwo", new EncodeImageToBase64().encodeImage(product.getImagePathTwo()));
                
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
