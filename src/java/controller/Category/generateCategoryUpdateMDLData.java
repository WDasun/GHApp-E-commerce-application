package controller.Category;

import com.google.gson.Gson;
import connection.ConnectionBuilder;
import dao.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductCategory;
import org.hibernate.Session;

/**
 *
 * @author ASUS
 */
public class generateCategoryUpdateMDLData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String categoryId = request.getParameter("categoryId").trim();
            Session session = ConnectionBuilder.hibSession();
            if (!categoryId.equals("") && categoryId != null) {

                ProductCategory category = new CategoryDAO().searchById(categoryId, session);

                HashMap<String, String> data = new HashMap<>();
                data.put("categoryId", category.getId());
                data.put("categoryName", category.getName());

                String parentCategoryListHTML = "";

                List<ProductCategory> parentCategoryList = new CategoryDAO().getParentCategoryList();
                if (category.getProductCategory() == null) {
                    parentCategoryListHTML += "<option value=\"PARENT\" selected>Parent</option>";

                } else {

                    for (ProductCategory parentCategory : parentCategoryList) {
                        if (category.getProductCategory().getId().equals(parentCategory.getId())) {
                            parentCategoryListHTML += "<option value=\"" + parentCategory.getId() + "\" selected>" + parentCategory.getId() + " : " + parentCategory.getName() + "</option>\n";
                        } else {
                            parentCategoryListHTML += "<option value=\"" + parentCategory.getId() + "\">" + parentCategory.getId() + " : " + parentCategory.getName() + "</option>\n";
                        }
                    }
                }

                data.put("parentCategoryListHTML", parentCategoryListHTML);

                out.print(new Gson().toJson(data));
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
