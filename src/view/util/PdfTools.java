package view.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PdfTools {

    public static byte[] convertToByte(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            System.err.println("Error pdfTools: " + e);
            return null;
        }
    }

    
    public static int getPageCounter(File file) {
        try {
            PDDocument document = PDDocument.load(file);
            int counter = document.getNumberOfPages();
            document.close();
            return counter;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } 
    }
}
