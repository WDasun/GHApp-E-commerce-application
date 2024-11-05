/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import connection.ConnectionBuilder;
import dao.ItemDAO;
import dao.VariationOptionDAO;
import dto.CartItem;
import dto.EncodeImageToBase64;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProductItem;
import model.VariationOption;
import org.hibernate.Session;

/**
 *
 * @author Dasun
 */
public class loadOptionContent extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String optionIdArray = request.getParameter("optionIdArray");
            Session session = ConnectionBuilder.hibSession();
            if (!optionIdArray.equals("") && optionIdArray != null) {

                JsonArray voIdjsArray = new JsonParser().parse(optionIdArray).getAsJsonArray();
                ArrayList<Integer> voIdArray = new ArrayList<>();
                for (int i = 0; i < voIdjsArray.size(); i++) {
                    voIdArray.add(voIdjsArray.get(i).getAsInt());
                }

                ArrayList<ProductItem> itemList = new ArrayList<>();

                LinkedList<String> selectedProductItemList = newSelectItem(voIdArray, session);
                for (String itemId : selectedProductItemList) {
                    try {
                        itemList.add(new ItemDAO().searchById(itemId));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) request.getSession().getAttribute("sessionCartItemList");

                EncodeImageToBase64 e64 = new EncodeImageToBase64();
                String html = "";

                int itemAmount = itemList.size();
                int itemAmountMin = itemAmount - 1;

                int itemAmountForARow = 5;

                int loopAmount = itemAmountMin + (itemAmountForARow - (itemAmountMin % itemAmountForARow));

                for (int i = 0; i < loopAmount; i++) {
                    if ((i % itemAmountForARow) == 0) {
                        html += "<!-- New row -->"
                                + "<div class=\"row m-0 mb-2\">";
                    }

                    if (itemAmountMin >= i) {

                        String addToCartBtnHTML = "";
                        if (cartItemList != null) {
                            for (CartItem cartItem : cartItemList) {
                                if (cartItem.getProductItem().getId().equals(itemList.get(i).getId())) {
                                    addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickRemoveItemCart('" + itemList.get(i).getId() + "','addtoCartBtnDiv" + itemList.get(i).getId() + "')\"><i class=\"fa-solid fa-trash fa-2xl\" style=\"color: #74C0FC;\"></i></button>";
                                    break;
                                } else {
                                    addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickAddToCartBtn('" + itemList.get(i).getId() + "','addtoCartBtnDiv" + itemList.get(i).getId() + "')\"><i class=\"fa-solid fa-cart-shopping fa-2xl me-2\" style=\"color: #B197FC;\"></i></button>\n";
                                }
                            }
                        } else {
                            addToCartBtnHTML = "<button class=\"btn-itemActions\" onclick=\"onclickAddToCartBtn('" + itemList.get(i).getId() + "','addtoCartBtnDiv" + itemList.get(i).getId() + "')\"><i class=\"fa-solid fa-cart-shopping fa-2xl me-2\" style=\"color: #B197FC;\"></i></button>\n";
                        }

                        html += "<div class=\"col p-0\">\n"
                                + "<!-- Card -->\n"
                                + "<div class=\"card card-item\">\n"
                                + "<div class=\"div-img-item\">\n"
                                + "<img src=\"" + e64.encodeImage(itemList.get(i).getItemImgOnePath()) + "\" class=\"card-img-top\" alt=\"...\">\n"
                                + " </div>\n"
                                + "\n"
                                + "<div class=\"card-body p-0 p-2\">\n"
                                + "<h5 class=\"card-title m-0\">" + itemList.get(i).getName() + "</h5>\n"
                                + "<div class=\"d-flex py-1 mt-1\">\n"
                                + "<div>\n"
                                + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                + "<i class=\"fa-solid fa-star me-1\" style=\"color: #ffa41c;\"></i>\n"
                                + "</div>\n"
                                + "<p class=\"m-0 ms-2\">(256)</p>\n"
                                + "</div>\n"
                                + "<div class=\"d-flex\">\n"
                                + "<p class=\"m-0 fs-6 me-1\">Price (LKR) : </p>\n"
                                + "<p class=\"m-0 fs-5\">" + itemList.get(i).getPrice() + "</p>\n"
                                + "</div>\n"
                                + "<div class=\"d-flex mt-3\">\n"
                                + "<div style=\"width: 100%;\">"
                                + "<button class=\"btn-itemActions border-end\"><i class=\"fa-solid fa-heart fa-2xl me-2\" style=\"color: #ff8787;\"></i> </button>\n"
                                + "</div>"
                                + "<div style=\"width: 100%;\" id=\"addtoCartBtnDiv" + itemList.get(i).getId() + "\">"
                                + addToCartBtnHTML
                                + "</div>"
                                + "</div>\n"
                                + "<div class=\"mt-3\">\n"
                                + "<button class=\"w-100 btn-item-view\" onclick=\"onclickView_collection('" + itemList.get(i).getId() + "')\">View</button>\n"
                                + "</div>\n"
                                + "</div>\n"
                                + "</div>\n"
                                + "</div>";
                    } else {
                        html += "<div class=\"col p-0\">\n"
                                + "<!-- Card -->\n"
                                + "<div class=\"card card-item\">\n"
                                + "\n"
                                + "</div>\n"
                                + "</div>";
                    }

                    if ((i % itemAmountForARow) == 4) {
                        html += "</div>"
                                + "<!-- Row End -->";
                    }

                }
                out.print(html);

            }
//            session.close();
        }
    }

    private LinkedList<String> newSelectItem(ArrayList<Integer> idArray, Session session) {

        HashMap<Integer, ArrayList<VariationOption>> v = new HashMap<Integer, ArrayList<VariationOption>>();

        for (Integer vId : idArray) {
            VariationOption vo = new VariationOptionDAO().searchById(vId, session);
            if (v.containsKey(vo.getVariation().getId())) {
                v.get(vo.getVariation().getId()).add(vo);
            } else {
                v.put(vo.getVariation().getId(), new ArrayList<VariationOption>());
                v.get(vo.getVariation().getId()).add(vo);
            }
        }

        LinkedList<String> piIdListMain = new LinkedList<String>();
        LinkedList<String> piIdListFirst = new LinkedList<String>();

        for (int key : v.keySet()) {

            LinkedList<String> piIdListSecond = new LinkedList<String>();

            ArrayList<VariationOption> voList = v.get(key);

            for (VariationOption vo : voList) {

                Object[] piArray = vo.getProductItems().toArray();
                ProductItem[] productItems = Arrays.copyOf(piArray, piArray.length, ProductItem[].class);

                for (ProductItem productItem : productItems) {
                    piIdListSecond.add(productItem.getId());
                }
            }

            if (piIdListFirst.size() == 0) {
                piIdListFirst = piIdListSecond;
            } else {
                for (String list2ItemId : piIdListSecond) {
                    if (piIdListFirst.contains(list2ItemId) && !piIdListMain.contains(list2ItemId)) {
                        piIdListMain.add(list2ItemId);
                    }
                }
                piIdListFirst = piIdListMain;
            }

            if (v.size() == 1) {
                piIdListMain = piIdListFirst;
            }

        }

        return piIdListMain;

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
