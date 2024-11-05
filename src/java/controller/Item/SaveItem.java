/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Item;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import dao.CategoryDAO;
import dao.ItemDAO;
import dao.ProductConfigurationDAO;
import dao.ProductDAO;
import dao.VariationOptionDAO;
import dto.HTMLContents;
import common.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.ProductConfiguration;
import model.ProductItem;
import model.VariationOption;

/**
 *
 * @author Dasun
 */
@MultipartConfig
public class SaveItem extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String itemId = request.getParameter("itemId").trim();
            String productId = request.getParameter("productId").trim();
            String name = request.getParameter("itemName").trim();
            String sku = request.getParameter("itemSKU").trim();
            String qty = request.getParameter("itemQTY").trim();
            String price = request.getParameter("itemPrice").trim();
            String description = request.getParameter("itemDescription").trim();
            Part imgOne = request.getPart("imageOne");
            Part imgTwo = request.getPart("imageTwo");
            Part imgThree = request.getPart("imageThree");
            String optionValuesArray = request.getParameter("optionValuesArray");
            boolean status = true;

            //Data Validation
            if (itemId != null && !itemId.equals("")
                    && productId != null && !productId.equals("")
                    && name != null && !name.equals("")
                    && sku != null && !sku.equals("")
                    && qty != null && !qty.equals("")
                    && price != null && !price.equals("")
                    && optionValuesArray != null && !optionValuesArray.equals("")
                    && description != null && !description.equals("")
                    && imgOne.getSize() > 0
                    && imgTwo.getSize() > 0
                    && imgThree.getSize() > 0) {

                //Image name,path setting process
                String imgOneName = itemId + "ImgOne";
                String imgTwoName = itemId + "ImgTwo";
                String imgThreeName = itemId + "ImgThree";

                String imgOneExtension = getFileExtension(imgOne);
                String imgTwoExtension = getFileExtension(imgTwo);
                String imgThreeExtension = getFileExtension(imgThree);

                String prodcutImgSavePath = new Paths().getItemImgPath();

                String newPath1 = prodcutImgSavePath + imgOneName + "." + imgOneExtension;
                String newPath2 = prodcutImgSavePath + imgTwoName + "." + imgTwoExtension;
                String newPath3 = prodcutImgSavePath + imgThreeName + "." + imgThreeExtension;
                try {
                    //Saveing product details in the DB
                    ProductItem pi = new ProductItem();
                    pi.setId(itemId);
                    pi.setProduct(new ProductDAO().searchById(productId));
                    pi.setName(name);
                    pi.setSku(sku);
                    pi.setQty(Integer.parseInt(qty));
                    pi.setPrice(Double.parseDouble(price));
                    pi.setDescription(description);
                    pi.setItemImgOnePath(newPath1);
                    pi.setItemImgTwoPath(newPath2);
                    pi.setItemImgThreePath(newPath3);
                    pi.setStatus(status);

                    new ItemDAO().save(pi);

                    //Save Product configuration
                    JsonArray optionValuesArrayJs = new JsonParser().parse(optionValuesArray).getAsJsonArray();
                    System.out.println("JArray : "+optionValuesArrayJs);
                    for (int i = 0; i < optionValuesArrayJs.size(); i++) {
                        if (optionValuesArrayJs.get(i) != null && !optionValuesArrayJs.get(i).equals("")) {
                            ProductConfiguration pc = new ProductConfiguration();
                            pc.setProductItem(pi);
                            int variationOptionId = optionValuesArrayJs.get(i).getAsInt();
                            VariationOption vo = new VariationOptionDAO().searchById(variationOptionId);
                            pc.setVariationOption(vo);
                            System.out.println("Count : " + i);
                            System.out.println("VO id : " + vo.getId() + " value : " + vo.getValue());
                            new ProductConfigurationDAO().save(pc);
                        }
                    }

                    //Image file Saving process
                    FileInputStream fin1 = (FileInputStream) imgOne.getInputStream();
                    FileOutputStream fout1 = new FileOutputStream(newPath1);
                    byte[] buffer1 = new byte[1024];
                    int byteRead1 = 0;

                    while ((byteRead1 = fin1.read(buffer1)) != -1) {
                        fout1.write(buffer1, 0, byteRead1);
                    }
                    fin1.close();
                    fout1.close();

                    FileInputStream fin2 = (FileInputStream) imgTwo.getInputStream();
                    FileOutputStream fout2 = new FileOutputStream(newPath2);
                    byte[] buffer2 = new byte[1024];
                    int byteRead2 = 0;

                    while ((byteRead2 = fin2.read(buffer2)) != -1) {
                        fout2.write(buffer2, 0, byteRead2);
                    }
                    fin2.close();
                    fout2.close();

                    FileInputStream fin3 = (FileInputStream) imgThree.getInputStream();
                    FileOutputStream fout3 = new FileOutputStream(newPath3);
                    byte[] buffer3 = new byte[1024];
                    int byteRead3 = 0;

                    while ((byteRead3 = fin3.read(buffer3)) != -1) {
                        fout3.write(buffer3, 0, byteRead3);
                    }
                    fin3.close();
                    fout3.close();

                    out.print(new HTMLContents().responseContent("Completed !"));

                } catch (Exception e) {
                    e.printStackTrace();
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

    private String getFileExtension(Part part) {

        String[] chnk1 = part.getHeader("content-disposition").split("filename=");
        String[] chnk2 = chnk1[1].split("\\.");
        String[] chnk3 = chnk2[1].split("\"");

        return chnk3[0];
    }
}
