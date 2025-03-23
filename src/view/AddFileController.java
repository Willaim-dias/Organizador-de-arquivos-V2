package view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.entities.Document;
import model.service.DocumentService;
import view.util.PdfTools;
import view.util.Tools;

public class AddFileController implements Initializable{

    private File file = null;
    private DocumentService service;
    private Stage stage;
    private byte[] fileByte;
    
    @FXML
    private ComboBox<String> cbFileCategories;

    @FXML
    private Label labelAlert;

    @FXML
    private TextField txtFileTitle;

    @FXML
    private TextArea txtaFileDescription;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setDocumentService(DocumentService service) {
        this.service = service;
    }
    
    public void onBtLoadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar arquivo");
        
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Arquivos PDF", "*.pdf");
        fileChooser.getExtensionFilters().add(filter);
        
        file = fileChooser.showOpenDialog(stage);
        fileByte = PdfTools.convertToByte(file);
        labelAlert.setText("Arquivo carregado");
    }
    
    public void onBtSave() { 
        if (txtFileTitle.getText().equals("") || file == null || cbFileCategories.getValue() == null) {
            labelAlert.setText("Erro ao Salvar.");
        } else {        
            String title = txtFileTitle.getText().toLowerCase();
            String categories = cbFileCategories.getValue();
            String description = txtaFileDescription.getText().toLowerCase();
            Document obj = new Document(null, title, categories, description, fileByte);
            service.saveOrUpdate(obj);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addCategoryComboBox();
    }  
    
    private void addCategoryComboBox() {
        cbFileCategories.getItems().clear();
        for (String category : Tools.readListCategory()) {
            cbFileCategories.getItems().add(category);
        }
    }
}
