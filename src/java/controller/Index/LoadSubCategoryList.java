/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Index;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.ConnectionBuilder;
import dao.CategoryDAO;
import dao.ProductDAO;
import dto.EncodeImageToBase64;
import dto.ProductInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductCategory;
import org.hibernate.Session;

/**
 *
 * @author Dasun Wimukthi
 */
@WebServlet(name = "LoadSubCategoryList", urlPatterns = {"/LoadSubCategoryList"})
public class LoadSubCategoryList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Session session = ConnectionBuilder.hibSession();
            List<Product> productList = new ProductDAO().getProductList(session);
            ArrayList<ProductInfo> productInfoList = new ArrayList<>();
            for (Product product : productList) {
                ProductInfo pi = new ProductInfo();
                pi.setProductName(product.getName());
                pi.setCategoryId(product.getProductCategory().getId());
                pi.setImage(new EncodeImageToBase64().encodeImage(product.getImagePathOne()));
                productInfoList.add(pi);
            }
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String json = gson.toJson(productInfoList);
            out.print(json);
//            session.close();
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
