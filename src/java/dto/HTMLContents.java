/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author ASUS
 */
public class HTMLContents {

    public String responseContent(String message) {
        return "                <div class=\"modal-header\">\n"
                + "                    <h5>Response</h5>\n"
                + "                </div>\n"
                + "                <div class=\"modal-body\">\n"
                + "                    <div class=\"container-fluid\">\n"
                + "                        <div class=\"row\">\n"
                + "                            <p class=\"fw-normal fs-5\">" + message + "</p>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                </div>\n"
                + "                <div class=\"modal-footer \">\n"
                + "                    <button class=\"btn btn-primary \" style=\"width: 100px; \" data-bs-dismiss=\"modal\">Ok</button>\n"
                + "                </div>";
    }
}
