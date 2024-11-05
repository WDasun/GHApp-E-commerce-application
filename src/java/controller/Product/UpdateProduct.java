/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Product;

import connection.ConnectionBuilder;
import dao.CategoryDAO;
import dao.ProductDAO;
import dto.ExctractFileExtension;
import common.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@MultipartConfig
public class UpdateProduct extends HttpServlet {

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

            System.out.println("Servlet called !");
            
            String id = request.getParameter("productId").trim();
            String name = request.getParameter("productName").trim();
            String catId = request.getParameter("categoryId").trim();
            String descripion = request.getParameter("productDescription".trim());
            Part imageOne = request.getPart("imageOne");

            Part imageTwo = request.getPart("imageTwo");
            Session session = ConnectionBuilder.hibSession();
            //Image name,path setting process
            String newPath1 = null;
            String newPath2 = null;
            String prodcutImgSavePath = new Paths().getProdcutImgPath();
            if (imageOne.getSize() > 0) {
                System.out.println("In 1111111111111111111");
                String imgOneName = id + "ImgOne";
                String imgOneExtension = new ExctractFileExtension().getFileExtension(imageOne);
                newPath1 = prodcutImgSavePath + imgOneName + "." + imgOneExtension;
            }

            if (imageTwo.getSize() > 0) {
                String imgTwoExtension = new ExctractFileExtension().getFileExtension(imageTwo);
                String imgTwoName = id + "ImgTwo";
                newPath2 = prodcutImgSavePath + imgTwoName + "." + imgTwoExtension;
            }

            // Updating the product
            if (!id.equals("") && id != null
                    && !name.equals("") && name != null
                    && !catId.equals("") && catId != null
                    && !descripion.equals("") && descripion != null) {

                new ProductDAO().Update(id, new CategoryDAO().searchById(catId, session), name, descripion, newPath1, newPath2);

                out.print("Product Updated !");
            } else {
                out.print("Data not valid !");
            }

            //Saving and replacing Product images
            try {
                if (newPath1 != null) {
                    System.out.println("File copping !");
                    FileInputStream fin1 = (FileInputStream) imageOne.getInputStream();
                    FileOutputStream fout1 = new FileOutputStream(newPath1);
                    byte[] buffer1 = new byte[1024];
                    int byteRead1 = 0;

                    while ((byteRead1 = fin1.read(buffer1)) != -1) {
                        fout1.write(buffer1, 0, byteRead1);
                    }
                    fin1.close();
                    fout1.close();

                    System.out.println("Image One Saved !");
                }

                if (newPath2 != null) {
                    FileInputStream fin2 = (FileInputStream) imageTwo.getInputStream();
                    FileOutputStream fout2 = new FileOutputStream(newPath2);
                    byte[] buffer2 = new byte[1024];
                    int byteRead2 = 0;

                    while ((byteRead2 = fin2.read(buffer2)) != -1) {
                        fout2.write(buffer2, 0, byteRead2);
                    }
                    fin2.close();
                    fout2.close();

                    System.out.println("Image Two Saved !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
