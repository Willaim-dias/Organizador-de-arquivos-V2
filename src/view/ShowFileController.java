package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.Document;
import org.apache.pdfbox.rendering.PDFRenderer;
import view.util.PdfTools;

public class ShowFileController implements Initializable {

    private int page = 0;
    private Document document;
    private PDFRenderer renderer;

    @FXML
    private Label labelPageNumber;

    @FXML
    private ImageView scrollPane;
    
    @FXML
    private ImageView showPage;

    public void setDocument(Document document, byte[] file) {
        this.document = document;
        renderer = PdfTools.rendererPDF(file);
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

    private void initializerNodes() {
        showPage.setPreserveRatio(true);
        showPage.setSmooth(true);
        showPage.setCache(true);
        scrollPane.setPreserveRatio(true);
        scrollPane.setSmooth(true);
        scrollPane.setCache(true);
    }
    
    private void showFile() {
        try {
            if (page >= 0 && page <= document.getNumberPages()) {
                BufferedImage bi = renderer.renderImageWithDPI(page, 250);
                Image image = SwingFXUtils.toFXImage(bi, null);
                labelPageNumber.setText(""+page+"/"+document.getNumberPages());
                showPage.setImage(image);
            }
        } catch (IOException e) {
            System.out.println("showfile Error: " + e);
        }
    }

}
