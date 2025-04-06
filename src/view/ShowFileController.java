package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.entities.Document;

public class ShowFileController implements Initializable {

    private Document document;
     
    public void setDocument(Document document) {
        this.document = document;
        addData();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    private void addData() {
        
    }
}
