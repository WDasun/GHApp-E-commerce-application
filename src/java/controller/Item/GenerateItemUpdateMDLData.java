/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Item;

import connection.ConnectionBuilder;
import dao.ItemDAO;
import dao.VariationDAO;
import dao.VariationOptionDAO;
import dto.EncodeImageToBase64;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductItem;
import model.Variation;
import model.VariationOption;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
public class GenerateItemUpdateMDLData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Session session = ConnectionBuilder.hibSession();
            String itemId = request.getParameter("itemId");

            if (!itemId.equals("") && itemId != null) {

                try {
                    ProductItem productItem = new ItemDAO().searchById(itemId, session);

                    String variationsAndOptionHtml = "";

                    Set<VariationOption> productVoSet = productItem.getVariationOptions();
                    List<Variation> categoryV = new VariationDAO().getVariationListByCategory(productItem.getProduct().getProductCategory());
                    for (Variation variation : categoryV) {
                        variationsAndOptionHtml += "<div class=\"d-flex mt-2 ps-5\">\n"
                                + "<label for=\"color\" class=\"form-label lbl-leftMenu-body w-25 me-4 mt-3\">" + variation.getName() + " :</label>\n"
                                + "<select class=\"form-select w-50 mt-3 variationOption-update\" aria-label=\"Default select example\" id=\"" + variation.getName() + "\">";
                        boolean productHasThisVariation = false;
                        Set<VariationOption> vVoSet = variation.getVariationOptions();
                        for (VariationOption vVo : vVoSet) {
                            boolean vVoIsAPvo = false; 
                            for (VariationOption pVo : productVoSet) {
                                if(pVo.getId() == vVo.getId()){
                                    vVoIsAPvo = true;
                                    break;
                                }else{
                                    vVoIsAPvo = false;
                                }
                            }
                            if (vVoIsAPvo) {
                                    productHasThisVariation = true;
                                    variationsAndOptionHtml += "<option value=\"" + vVo.getId() + "\" selected>" + vVo.getValue() + "</option>";
                                } else {
                                    variationsAndOptionHtml += "<option value=\"" + vVo.getId() + "\">" + vVo.getValue() + "</option>";
                                }

                        }
                        if (!productHasThisVariation) {
                            variationsAndOptionHtml += "<option value=\"\" selected> None </option>";
                        } else {
                            variationsAndOptionHtml += "<option value=\"\"> None </option>";
                        }

                        variationsAndOptionHtml += "</select>\n</div>";
                    }

                    String modalHTML = " <div class=\"modal-header\">\n"
                            + "                    <h5>Update Item</h5>\n"
                            + "                </div>\n"
                            + "                <div class=\"modal-body\">\n"
                            + "                    <div class=\"container-fluid\">\n"
                            + "                        <div class=\"row\">\n"
                            + "                            <form id=\"form-updateItem\" class=\"p-0\">\n"
                            + "                                <div class=\"container-fluid p-0\">\n"
                            + "                                    <div class=\"row m-0 pt-2 pb-4 div-leftmenu-body\">\n"
                            + "                                        <div class=\"\">\n"
                            + "                                            <label for=\"itemId\" class=\"form-label lbl-leftMenu-body\">Item ID</label>\n"
                            + "                                            <input type=\"text\" class=\"form-control\" id=\"itemId\" name=\"itemId\" value=\"" + productItem.getId() + "\" readonly>\n"
                            + "                                        </div>\n"
                            + "                                        <div class=\"mt-2  was-validated\">\n"
                            + "                                            <label for=\"Product\" class=\"form-label lbl-leftMenu-body\">Product</label>\n"
                            + "                                            <input type=\"text\" class=\"form-control\" id=\"Product\" name=\"productId\" value=\"" + productItem.getProduct().getId() + " : " + productItem.getProduct().getName() + "\" readonly>\n"
                            + "                                        </div>\n"
                            + "                                        <div class=\"mt-2  was-validated\">\n"
                            + "                                            <label for=\"itemName\" class=\"form-label lbl-leftMenu-body\">Item name</label>\n"
                            + "                                            <input type=\"text\" class=\"form-control\" id=\"itemName\" name=\"itemName\" value=\"" + productItem.getName() + "\" required>\n"
                            + "                                        </div>\n"
                            + "                                        <div class=\"mt-2  was-validated\">\n"
                            + "                                            <label for=\"itemSKU\" class=\"form-label lbl-leftMenu-body\">SKU</label>\n"
                            + "                                            <input type=\"text\" class=\"form-control\" id=\"itemSKU\" name=\"itemSKU\" value=\"" + productItem.getSku() + "\" required>\n"
                            + "                                        </div>\n"
                            + "                                        <div class=\"mt-2  was-validated\">\n"
                            + "                                            <label for=\"itemQTY\" class=\"form-label lbl-leftMenu-body\">QTY</label>\n"
                            + "                                            <input type=\"text\" class=\"form-control\" id=\"itemQTY\" name=\"itemQTY\" value=\"" + productItem.getQty() + "\" required>\n"
                            + "                                        </div>\n"
                            + "                                        <div class=\"mt-2  was-validated\">\n"
                            + "                                            <label for=\"itemPrice\" class=\"form-label lbl-leftMenu-body\">Price (LKR)</label>\n"
                            + "                                            <input type=\"text\" class=\"form-control\" id=\"itemPrice\" name=\"itemPrice\" value=\"" + productItem.getPrice() + "\" required>\n"
                            + "                                        </div>\n"
                            + "                                    </div>\n"
                            + "                                    <div class=\"row m-0 mt-1 pt-2 pb-4 div-leftmenu-body\">\n"
                            + "                                        <div class=\"container-fluid\">\n"
                            + "                                            <div class=\"row py-1\">\n"
                            + "                                                <label class=\"form-label lbl-leftMenu-body\">Item images</label>\n"
                            + "                                            </div>\n"
                            + "                                            <div class=\"row d-flex align-items-center justify-content-between\">\n"
                            + "                                                <!-- Images One -->\n"
                            + "                                                <div class=\"col p-0 d-flex align-items-center justify-content-center\">\n"
                            + "                                                    <div class=\"form-group\">\n"
                            + "                                                        <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInputOne\" style=\"height: 300px; width: 200px;\">\n"
                            + "                  <i class=\"fa-solid fa-image\" id=\"icn-imageInputOne\"></i>\n"
                            + "                  <img id=\"img-imageInputOne\" src=\"" + new EncodeImageToBase64().encodeImage(productItem.getItemImgOnePath()) + "\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n"
                            + "                  </label>\n"
                            + "                                                        <input type=\"file\" class=\"form-control d-none\" id=\"imageInputOne\" name=\"imageOne\" accept=\"image/*\">\n"
                            + "                                                    </div>\n"
                            + "                                                    <div class=\"\" style=\"height: 300px;\">\n"
                            + "                                                        <div class=\"container px-3\">\n"
                            + "                                                            <div class=\"row mt-1\">\n"
                            + "                                                                <button class=\"btn btn-danger ms-1\" type=\"button\" onclick=\"removeItemImgInUpdateItem(1)\">\n"
                            + "                  <i class=\"fa-solid fa-trash\"></i>\n"
                            + "                  </button>\n"
                            + "                                                            </div>\n"
                            + "                                                        </div>\n"
                            + "                                                    </div>\n"
                            + "                                                </div>\n"
                            + "                                                <!-- Images Two -->\n"
                            + "                                                <div class=\"col p-0 d-flex align-items-center justify-content-center\">\n"
                            + "                                                    <div class=\"form-group\">\n"
                            + "                                                        <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInputTwo\" style=\"height: 300px; width: 200px;\">\n"
                            + "                  <i class=\"fa-solid fa-image\" id=\"icn-imageInputTwo\"></i>\n"
                            + "                  <img id=\"img-imageInputTwo\" src=\"" + new EncodeImageToBase64().encodeImage(productItem.getItemImgTwoPath()) + "\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n"
                            + "                  </label>\n"
                            + "                                                        <input type=\"file\" class=\"form-control d-none\" id=\"imageInputTwo\" name=\"imageTwo\" accept=\"image/*\">\n"
                            + "                                                    </div>\n"
                            + "                                                    <div class=\"\" style=\"height: 300px;\">\n"
                            + "                                                        <div class=\"container px-3\">\n"
                            + "                                                            <div class=\"row mt-1\">\n"
                            + "                                                                <button class=\"btn btn-danger ms-1\" type=\"button\" onclick=\"removeItemImgInUpdateItem(2)\">\n"
                            + "                      <i class=\"fa-solid fa-trash\"></i>\n"
                            + "                  </button>\n"
                            + "                                                            </div>\n"
                            + "                                                        </div>\n"
                            + "                                                    </div>\n"
                            + "                                                </div>\n"
                            + "                                                <!-- Images Three -->\n"
                            + "                                                <div class=\"col p-0 d-flex align-items-center justify-content-center\">\n"
                            + "                                                    <div class=\"form-group\">\n"
                            + "                                                        <label class=\"border border-2 d-flex align-items-center justify-content-center\" for=\"imageInputThree\" style=\"height: 300px; width: 200px;\">\n"
                            + "                  <i class=\"fa-solid fa-image\" id=\"icn-imageInputThree\"></i>\n"
                            + "                  <img id=\"img-imageInputThree\" src=\"" + new EncodeImageToBase64().encodeImage(productItem.getItemImgThreePath()) + "\" alt=\"Your Image\" style=\"display: none; width: 100%; height: 100%;\" />\n"
                            + "                  </label>\n"
                            + "                                                        <input type=\"file\" class=\"form-control d-none\" id=\"imageInputThree\" name=\"imageThree\" accept=\"image/*\">\n"
                            + "                                                    </div>\n"
                            + "                                                    <div class=\"\" style=\"height: 300px;\">\n"
                            + "                                                        <div class=\"container px-3\">\n"
                            + "                                                            <div class=\"row mt-1\">\n"
                            + "                                                                <button class=\"btn btn-danger ms-1\" type=\"button\" onclick=\"removeItemImgInUpdateItem(3)\">\n"
                            + "                      <i class=\"fa-solid fa-trash\"></i>\n"
                            + "                  </button>\n"
                            + "                                                            </div>\n"
                            + "                                                        </div>\n"
                            + "                                                    </div>\n"
                            + "                                                </div>\n"
                            + "                                            </div>\n"
                            + "                                        </div>\n"
                            + "                                    </div>\n"
                            + "                                    <div class=\"row m-0\">\n"
                            + "                                        <p class=\"m-0\">Variation Options</p>\n"
                            + "                                        <div id=\"div-variationOptions-updateItem\">\n"
                            + variationsAndOptionHtml
                            + "                                        </div>\n"
                            + "                                    </div>\n"
                            + "                                </div>\n"
                            + "                            </form>\n"
                            + "                        </div>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "                <div class=\"modal-footer \">\n"
                            + "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" id=\"btn-model-update\" onclick=\"ItemUpdateConfirm('xxxx')\">Update</button>\n"
                            + "                    <button class=\"btn btn-danger \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Cancel</button>\n"
                            + "                </div>";

                    out.print(modalHTML);
                } catch (Exception e) {
                    e.printStackTrace();
                    out.print(e.getMessage());
                }

            } else {
                out.print("Data not valid !");
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
