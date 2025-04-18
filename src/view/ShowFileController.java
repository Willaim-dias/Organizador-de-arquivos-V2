package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.Document;
import view.util.PdfTools;

public class ShowFileController implements Initializable {

    private int page = 0;
    private Document document;
    private byte[] pdfByte;
    
    @FXML
    private Label labelPageNumber;

    @FXML
    private ImageView showPage;

    public void setDocument(Document document, byte[] pdfByte) {
        this.document = document;
        this.pdfByte = pdfByte;
        
        try {
            showFile();
        } catch (Exception e) {
            System.err.println("Erro ao carregar documento: " + e.getMessage());
        }
    }

    public void onBtShowFile() {
        showFile();
    }

    public void onBtNextPage() {
        page += 1;
        showFile();
    }

    public void onBtPreviousPage() {
        if (page > -0) {
            page -= 1;
        }  
        showFile();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializerNodes();
    }

    private void showFile() {
        if (page >= 0 && page < document.getNumberPages()) {
            Image image = PdfTools.renderFirstPage(page, pdfByte);
            labelPageNumber.setText(""+(page+1)+"/"+document.getNumberPages());
            showPage.setImage(image);
            
            showPage.setFitWidth(800);
            showPage.setFitHeight(1200);
        }
    }

    private void initializerNodes() {
        
    }
}
