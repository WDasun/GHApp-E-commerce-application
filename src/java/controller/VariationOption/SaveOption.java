package controller.VariationOption;

import dao.VariationDAO;
import dao.VariationOptionDAO;
import dto.HTMLContents;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Variation;
import model.VariationOption;

@MultipartConfig
public class SaveOption extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                String name = request.getParameter("option-name").trim();
                String variationId = request.getParameter("select-variation").trim();

                if (!name.equals("") && (name != null)
                        && !variationId.equals("") && (variationId != null)) {
                    VariationOption vo = new VariationOption();
                    vo.setValue(name);
                    Variation variation = new VariationDAO().searchById(Integer.parseInt(variationId));
                    vo.setVariation(variation);
                    if (!new VariationOptionDAO().checkAvailability(variation, name)) {
                        new VariationOptionDAO().save(vo);
                        out.print(new HTMLContents().responseContent("Variation option saved !"));
                    } else {
                        out.print(new HTMLContents().responseContent("Variation option exist !"));
                    }
                } else {
                    out.print(new HTMLContents().responseContent("Data not valid !"));
                }
            } catch (Exception e) {
                e.printStackTrace();
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

}
