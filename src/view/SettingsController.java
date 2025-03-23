package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import view.util.Tools;

public class SettingsController implements Initializable {

    @FXML
    private ComboBox<String> cbCategory;

    @FXML
    private TextField txtAddCategory;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addCategoryComboBox();
    }  
    
    //Category
    public void onBtAddCategory() {
        if (txtAddCategory.getText().equals("")) {
          txtAddCategory.setStyle("-fx-border-color: red;");
        } else {
            txtAddCategory.setStyle("-fx-border-color: #000;");
            Tools.addCategory(txtAddCategory.getText().toLowerCase());
            addCategoryComboBox();
        }
    }
     
    private void addCategoryComboBox() {
        cbCategory.getItems().clear();
        for (String category : Tools.readListCategory()) {
            cbCategory.getItems().add(category);
        }
    }
    
    
}
