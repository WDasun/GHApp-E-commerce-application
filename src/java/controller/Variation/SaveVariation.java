/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Variation;

import connection.ConnectionBuilder;
import dao.CategoryDAO;
import dao.VariationDAO;
import dto.HTMLContents;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductCategory;
import model.Variation;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SaveVariation", urlPatterns = {"/SaveVariation"})
@MultipartConfig
public class SaveVariation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String name = request.getParameter("name").trim();
            String catId = request.getParameter("categoryId").trim();
            Session session = ConnectionBuilder.hibSession();
            if (!name.equals("") && name != null && !catId.equals("") && catId != null) {
                Variation v = new Variation();
                v.setName(name);
                ProductCategory pc = new CategoryDAO().searchById(catId,session);
                if (pc != null) {
                    if (!(new VariationDAO().checkAvailability(pc, name))) {
                        v.setProductCategory(pc);
                        new VariationDAO().save(v);
                        out.print(new HTMLContents().responseContent("Completed !"));
                    }else{
                        out.print(new HTMLContents().responseContent("Variation already exists !"));
                    }
                } else {
                    out.print(new HTMLContents().responseContent("Category id not valid !"));
                }
            } else {
                out.print(new HTMLContents().responseContent("Data not valid !"));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
