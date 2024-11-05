/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import javax.servlet.http.Part;

/**
 *
 * @author ASUS
 */
public class ExctractFileExtension {
    
        public String getFileExtension(Part part) {

        String[] chnk1 = part.getHeader("content-disposition").split("filename=");
        String[] chnk2 = chnk1[1].split("\\.");
        String[] chnk3 = chnk2[1].split("\"");

        return chnk3[0];
    }
    
}
