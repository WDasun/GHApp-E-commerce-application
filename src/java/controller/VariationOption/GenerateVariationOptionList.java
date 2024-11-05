package controller.VariationOption;

import connection.ConnectionBuilder;
import dao.VariationOptionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VariationOption;
import org.hibernate.Session;

@WebServlet(name = "GenerateVariationOptionList", urlPatterns = {"/GenerateVariationOptionList"})
public class GenerateVariationOptionList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Session session = ConnectionBuilder.hibSession();
            String html = "";

            List<VariationOption> optionList = new VariationOptionDAO().getVariationOptionList(session);
            for (VariationOption variationOption : optionList) {
                html += " <tr>\n"
                        + "<td>"+variationOption.getId()+"</td>\n"
                        + "<td>"+variationOption.getValue()+"</td>\n"
                        + "<td>"+variationOption.getVariation().getId()+" : "+variationOption.getVariation().getName()+" / [CAT] "+variationOption.getVariation().getProductCategory().getId()+" : "+variationOption.getVariation().getProductCategory().getName()+"</td>\n"
                        + "<td class=\"\">\n"
                        + "<button class=\"btn btn-primary btn-categoryList-tableTow\" onclick=\"clickOnUpdateVariationOption('"+variationOption.getId()+"')\">Update</button>\n"
                        + "</td>\n"
                        + "</tr>";
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
