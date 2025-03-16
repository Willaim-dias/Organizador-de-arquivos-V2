package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class DescriptionController implements Initializable {

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
