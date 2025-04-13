package view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Document;
import model.service.DocumentService;
import view.listeners.DataChangeListener;
import view.util.PdfTools;
import view.util.Tools;

public class AddFileController implements Initializable{

    private final List<DataChangeListener> DataChangeListeners = new ArrayList<>();
    
    private File file = null;
    private DocumentService service;
    private Stage stage;
    private byte[] fileByte;
    private double fileSize;
    private int pageCounter;
    
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
        file = PdfTools.LoadFile(stage);
        fileSize = file.length();
        fileByte = PdfTools.convertToByte(file);
        pageCounter = PdfTools.getPageCounter(file);
        labelAlert.setText("Arquivo carregado");
    }
    
    public void onBtSave(ActionEvent event) { 
        if (txtFileTitle.getText().equals("") || file == null || cbFileCategories.getValue() == null) {
            labelAlert.setText("Erro ao Salvar.");
        } else {        
            
            String title = txtFileTitle.getText().toLowerCase();
            String categories = cbFileCategories.getValue();
            String description = txtaFileDescription.getText().toLowerCase();
            Document obj = new Document(null, title, categories, description, fileByte,pageCounter,fileSize);
            service.saveOrUpdate(obj);
            notifyDataChangeListeners();
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
    
    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : DataChangeListeners) {
            listener.onDataChanged();
        }
    }
}
