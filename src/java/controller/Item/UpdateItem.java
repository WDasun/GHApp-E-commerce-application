/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Item;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import dao.ItemDAO;
import dao.ProductConfigurationDAO;
import dao.ProductDAO;
import dao.VariationOptionDAO;
import dto.ExctractFileExtension;
import common.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.ProductConfiguration;

/**
 *
 * @author ASUS
 */
@MultipartConfig
public class UpdateItem extends HttpServlet {

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
            String optionsArray = request.getParameter("optionsArray");
            String itemId = request.getParameter("itemId");
            String productId = request.getParameter("productId");
            String name = request.getParameter("itemName");
            String sku = request.getParameter("itemSKU");
            int qty = Integer.parseInt(request.getParameter("itemQTY"));
            double price = Double.parseDouble(request.getParameter("itemPrice"));
            Part imageOne = request.getPart("imageOne");
            Part imageTwo = request.getPart("imageTwo");
            Part imageThree = request.getPart("imageThree");

            //Image name,path setting process
            String newPath1 = null;
            String newPath2 = null;
            String newPath3 = null;
            String prodcutImgSavePath = new Paths().getItemImgPath();
            if (imageOne.getSize() > 0) {
                String imgOneName = itemId + "ImgOne";
                String imgOneExtension = new ExctractFileExtension().getFileExtension(imageOne);
                newPath1 = prodcutImgSavePath + imgOneName + "." + imgOneExtension;
            }

            if (imageTwo.getSize() > 0) {
                String imgTwoExtension = new ExctractFileExtension().getFileExtension(imageTwo);
                String imgTwoName = itemId + "ImgTwo";
                newPath2 = prodcutImgSavePath + imgTwoName + "." + imgTwoExtension;
            }

            if (imageThree.getSize() > 0) {
                String imgThreeExtension = new ExctractFileExtension().getFileExtension(imageThree);
                String imgThreeName = itemId + "ImgThree";
                newPath3 = prodcutImgSavePath + imgThreeName + "." + imgThreeExtension;
            }

            // Updating the product
            if (itemId != null && !itemId.equals("")
                    && productId != null && !productId.equals("")
                    && name != null && !name.equals("")
                    && sku != null && !sku.equals("")) {

                new ItemDAO().Update(itemId, name, sku, qty, price, newPath1, newPath2, newPath3);

                // Option replacing
                try {
                    new ProductConfigurationDAO().deleteByProductItem(new ItemDAO().searchById(itemId));

                    JsonArray newOptionsArray = new JsonParser().parse(optionsArray).getAsJsonArray();
                    for (int i = 0; i < newOptionsArray.size(); i++) {
                        ProductConfiguration pc = new ProductConfiguration();
                        pc.setProductItem(new ItemDAO().searchById(itemId));
                        pc.setVariationOption(new VariationOptionDAO().searchById(newOptionsArray.get(i).getAsInt()));
                        new ProductConfigurationDAO().save(pc);
                    }

                    out.print("Product Updated !");
                } catch (Exception e) {
                    e.printStackTrace();
                    out.print(e.getMessage());
                }
            } else {
                out.print("Data not valid !");
            }

            //Saving and replacing Product images
            try {
                if (newPath1 != null) {
                    InputStream fin1 = imageOne.getInputStream();
                    FileOutputStream fout1 = new FileOutputStream(newPath1);
                    byte[] buffer1 = new byte[1024];
                    int byteRead1 = 0;

                    while ((byteRead1 = fin1.read(buffer1)) != -1) {
                        fout1.write(buffer1, 0, byteRead1);
                    }
                    fin1.close();
                    fout1.close();
                }

                if (newPath2 != null) {
                    InputStream fin2 = imageTwo.getInputStream();
                    FileOutputStream fout2 = new FileOutputStream(newPath2);
                    byte[] buffer2 = new byte[1024];
                    int byteRead2 = 0;

                    while ((byteRead2 = fin2.read(buffer2)) != -1) {
                        fout2.write(buffer2, 0, byteRead2);
                    }
                    fin2.close();
                    fout2.close();
                }
                if (newPath3 != null) {
                    InputStream fin3 = imageThree.getInputStream();
                    FileOutputStream fout3 = new FileOutputStream(newPath3);
                    byte[] buffer3 = new byte[1024];
                    int byteRead3 = 0;

                    while ((byteRead3 = fin3.read(buffer3)) != -1) {
                        fout3.write(buffer3, 0, byteRead3);
                    }
                    fin3.close();
                    fout3.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
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
