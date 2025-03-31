package view;

import application.Main;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Document;
import model.service.DocumentService;
import view.listeners.DataChangeListener;
import view.util.Alert;
import view.util.Tools;

public class HomeController extends DataChangeListener implements Initializable {

    private final DocumentService service = new DocumentService();
    private Stage stage;

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
    private TableColumn<Document, Document> columnBtRemover;

    @FXML
    private Label labelError;

    @FXML
    private Label labelTotalFiles;

    @FXML
    private TextField txtSearch;

    private ObservableList<Document> obsList;

    //Show Windows
    public void onBtWindowSave(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFile.fxml"));
            AnchorPane anchorPane = loader.load();

            AddFileController controller = loader.getController();
            controller.setDocumentService(service);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Adicionar novo arquivo");
            dialogStage.setScene(new Scene(anchorPane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(stage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBtWindowSettings(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Settings.fxml"));
            AnchorPane anchorPane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Configurações");
            dialogStage.setScene(new Scene(anchorPane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(Tools.currentStage(event));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBtupdateTable() {
        addDataTable();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializerNodes();
        stage = Main.getStage();
    }

    private void initializerNodes() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        addDataTable();
    }

    private void addDataTable() {
        List<Document> list = service.findAll();
        labelTotalFiles.setText("Total de Arquivos: " + list.size());
        obsList = FXCollections.observableArrayList(list);
        tableFiles.setItems(obsList);
        initBtViewDescription();
        initBtViewDelete();

    }

    private void initBtViewDescription() {
        columnBtView.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        columnBtView.setCellFactory(param -> new TableCell<Document, Document>() {
            private final Button button = new Button();
            private final ImageView icon = new ImageView(new Image("/view/imgs/icons/view.png"));

            {
                icon.setFitWidth(20);
                icon.setFitHeight(20);

                button.setGraphic(icon);
                button.setPrefWidth(30);
                button.setPrefHeight(30);
            }

            @Override
            protected void updateItem(Document obj, boolean empty) {
                super.updateItem(obj, empty);

                if (obj == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(button);
                button.setOnAction(event -> createDialogForm(obj, "/view/Description.fxml", Tools.currentStage(event)));
            }
        });
    }

    private void initBtViewDelete() {
        columnBtRemover.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        columnBtRemover.setCellFactory(param -> new TableCell<Document, Document>() {
            private final Button button = new Button();
            private final ImageView icon = new ImageView(new Image("/view/imgs/icons/delete.png"));

            {
                icon.setFitWidth(20);
                icon.setFitHeight(20);

                button.setGraphic(icon);
                button.setPrefWidth(30);
                button.setPrefHeight(30);
            }

            @Override
            protected void updateItem(Document obj, boolean empty) {
                super.updateItem(obj, empty);

                if (obj == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(button);
                button.setOnAction(event -> removeEntity(obj));
            }
        });
    }

    private void createDialogForm(Document obj, String absoluteName, Stage parentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            AnchorPane anchorPane = loader.load();

            DescriptionController controller = loader.getController();
            controller.setDocument(obj);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Descrição");
            dialogStage.setScene(new Scene(anchorPane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeEntity(Document obj) {
        Optional<ButtonType> result = Alert.showConfirmation("Confirmação", "Tem certeza de que deseja excluir?");
        if (result.get() == ButtonType.OK) {
            if (service == null) {
                throw new IllegalStateException("Service was null");
            }

            service.deleteById(obj.getId());
            addDataTable();
        }
    }
    
    @Override
    public void onDataChanged() {
        addDataTable();
    }
}
