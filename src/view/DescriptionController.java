package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.entities.Document;

public class DescriptionController implements Initializable {

    private Document document;
    
    @FXML
    private Label labelCategory;

    @FXML
    private Label labelFileDescription;

    @FXML
    private Label labelFileName;

    @FXML
    private Label labelFileSize;

    @FXML
    private Label labelPageNumber;
    
    public void setDocument(Document document) {
        this.document = document;
        addData();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    private void addData() {
        labelFileName.setText(document.getTitle());
        labelCategory.setText(document.getCategory());
        labelPageNumber.setText(Integer.toString(document.getNumberPages()));
        labelFileSize.setText(Double.toString(document.getFileSize()));
        labelFileDescription.setText(document.getDescription());
    }
}
