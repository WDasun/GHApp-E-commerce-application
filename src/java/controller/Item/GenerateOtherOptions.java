package controller.Item;

import connection.ConnectionBuilder;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.VariationDAO;
import dao.VariationOptionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.Variation;
import model.VariationOption;
import org.hibernate.Session;

public class GenerateOtherOptions extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String productId = request.getParameter("productId");

            String html = "<div class=\"row py-1\">\n"
                    + "    <label class=\"form-label lbl-leftMenu-body\">Other options</label>\n"
                    + "</div>";

            Session session = ConnectionBuilder.hibSession();

            Product product = new ProductDAO().searchById(productId);

            List<Variation> pc = new VariationDAO().getVariationListByCategory(new CategoryDAO().searchById(product.getProductCategory().getId(), session));

            for (Variation variation : pc) {

                html += "<div class=\"row pb-3\">\n"
                        + "    <div class=\"col-2 d-flex align-items-center justify-content-end\">\n"
                        + "        <label for=\"selectProductAddItem\" class=\"form-label m-0 lbl-leftMenu-body\">" + variation.getName() + " : </label>\n"
                        + "    </div>\n"
                        + "    <div class=\"col-10\">\n"
                        + "        <select class=\"form-select option-select\" aria-label=\"Default select example\" name=\"productId\" id=\"selectProductAddItem\" style=\"width: 50%;\">\n"
                        + "            <option value=\"\" selected disabled>Select " + variation.getName() + "</option>";

                List<VariationOption> vo = new VariationOptionDAO().getVariationOptionListByVariation(variation);

                for (VariationOption variationOption : vo) {
                    html += "<option value=\"" + variationOption.getId() + "\"> " + variationOption.getValue() + "</option>";
                }
                html += "</select>\n"
                        + "    </div>\n"
                        + "</div>";
            }
            out.print(html);
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
