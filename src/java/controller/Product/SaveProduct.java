/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Product;

import connection.ConnectionBuilder;
import dao.CategoryDAO;
import dao.ProductDAO;
import common.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Product;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SaveProduct", urlPatterns = {"/SaveProduct"})
@MultipartConfig
public class SaveProduct extends HttpServlet {

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
            String id = request.getParameter("productId").trim();
            String name = request.getParameter("productName").trim();
            String catId = request.getParameter("categoryId").trim();
            String descripion = request.getParameter("productDescription").trim();
            Part imageOne = request.getPart("imageOne");
            Part imageTwo = request.getPart("imageTwo");
            boolean status = true;
            Session session = ConnectionBuilder.hibSession();
            //Data Validation
            if (!id.equals("") && id != null
                    && !name.equals("") && name != null
                    && !catId.equals("") && catId != null
                    && !descripion.equals("") && descripion != null
                    && imageOne.getSize() > 0
                    && imageTwo.getSize() > 0) {

                //Image name,path setting process
                String imgOneName = id + "ImgOne";
                String imgOneExtension = getFileExtension(imageOne);
                String imgTwoExtension = getFileExtension(imageTwo);
                String imgTwoName = id + "ImgTwo";
                String prodcutImgSavePath = new Paths().getProdcutImgPath();
                
                String newPath1 = prodcutImgSavePath + imgOneName + "." + imgOneExtension;
                String newPath2 = prodcutImgSavePath + imgTwoName + "." + imgTwoExtension;

                //Saveing product details in the DB
                Product product = new Product();
                product.setId(id);
                product.setProductCategory(new CategoryDAO().searchById(catId,session));
                product.setName(name);
                product.setDescription(descripion);
                product.setImagePathOne(newPath1);
                product.setImagePathTwo(newPath2);
                product.setStatus(status);
                
                ProductDAO pDAO = new ProductDAO();
                pDAO.save(product);

                //Image file Saving process
                try {
                    
                    FileInputStream fin1 = (FileInputStream) imageOne.getInputStream();
                    FileOutputStream fout1 = new FileOutputStream(newPath1);
                    byte[] buffer1 = new byte[1024];
                    int byteRead1 = 0;
                    
                    while ((byteRead1 = fin1.read(buffer1)) != -1) {
                        fout1.write(buffer1, 0, byteRead1);
                    }
                    fin1.close();
                    fout1.close();
                    
                    FileInputStream fin2 = (FileInputStream) imageTwo.getInputStream();
                    FileOutputStream fout2 = new FileOutputStream(newPath2);
                    byte[] buffer2 = new byte[1024];
                    int byteRead2 = 0;
                    
                    while ((byteRead2 = fin2.read(buffer2)) != -1) {
                        fout2.write(buffer2, 0, byteRead2);
                    }
                    fin2.close();
                    fout2.close();
                    
                    out.print("Product saved !");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            } else {
                out.print("Data not valide !");
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

    // Method to extract the file extension from the file name
    private String getFileExtension(Part part) {
        
        String[] chnk1 = part.getHeader("content-disposition").split("filename=");
        String[] chnk2 = chnk1[1].split("\\.");
        String[] chnk3 = chnk2[1].split("\"");
        
        return chnk3[0];
    }
    
}
