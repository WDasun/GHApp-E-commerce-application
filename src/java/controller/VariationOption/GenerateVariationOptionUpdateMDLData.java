package controller.VariationOption;

import com.google.gson.Gson;
import connection.ConnectionBuilder;
import dao.VariationDAO;
import dao.VariationOptionDAO;
import dto.HTMLContents;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Variation;
import model.VariationOption;
import org.hibernate.Session;

public class GenerateVariationOptionUpdateMDLData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Session session = ConnectionBuilder.hibSession();
            String variationOptionId = request.getParameter("variationOptionId");

            if (!variationOptionId.equals("") && (variationOptionId != null)) {
                
                VariationOption variationOption = new VariationOptionDAO().searchById(Integer.parseInt(variationOptionId));

                HashMap<String, String> data = new HashMap<>();
                data.put("variationOptionId", String.valueOf(variationOption.getId()));
                data.put("variationOptionValue", variationOption.getValue());
                
                String variationListHTML = "";
                List<Variation> variationList = new VariationDAO().getVariationList(session);
                for (Variation variation : variationList) {
                    if(variation.getId().equals(variationOption.getVariation().getId())){
                        variationListHTML += "<option value=\""+variation.getId()+"\" selected>"+variation.getId()+" : " + variation.getName() + " (Category : "+variation.getProductCategory().getName()+")</option>";
                    }else{
                        variationListHTML += "<option value=\""+variation.getId()+"\">"+variation.getId()+" : " + variation.getName() + " (Category : "+variation.getProductCategory().getName()+")</option>";
                    }
                }
                
                data.put("variationListHTML", variationListHTML);

                out.print(new Gson().toJson(data));
                
            }else{
                out.print("Variation option ID not valid !");
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
