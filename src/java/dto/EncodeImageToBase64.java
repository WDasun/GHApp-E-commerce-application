/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 *
 * @author ASUS
 */
public class EncodeImageToBase64 {

    public String encodeImage(String imagePath) {
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            // Converting Image byte array into Base64 String
            String imageDataString = Base64.getEncoder().encodeToString(imageData);

            return "data:image/jpeg;base64," + imageDataString;
        } catch (IOException e) {
            System.out.println("Exception while reading the Image " + e);
            return null;
        }
    }
}
