package view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Document;
import model.service.DocumentService;

public class HomeController implements Initializable {

    private final DocumentService service = new DocumentService();
    
    @FXML
    private TableView<Document> tableFiles;
    
    @FXML
    private TableColumn<Document, Integer> columnId;
    
    @FXML
    private TableColumn<Document, String> columnTitle;
    
    @FXML
    private TableColumn<Document, String> columnCategory;

    @FXML
    private TableColumn<Document, Document> columnBtView;

    @FXML
    private Label labelError;

    @FXML
    private Label labelTotalFiles;

    @FXML
    private TextField txtSearch;
    
    private ObservableList<Document> obsList;
    
    public void showWindowSave() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFile.fxml"));
            AnchorPane anchorPane = loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Adicionar novo arquivo");
            dialogStage.setScene(new Scene(anchorPane));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        } catch (IOException e) {
            System.err.println("Error: "+e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializerNodes();
    }    
    
    private void initializerNodes() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
      
    }
    
    private void addDataTable() {
        if (service == null) {
     
        }
        
        List<Document> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableFiles.setItems(obsList);
        
    }
}
