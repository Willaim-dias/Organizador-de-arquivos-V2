package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.util.Tools;

public class Main extends Application {

    private static Scene mainScene;
    private static Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Home.fxml"));
            AnchorPane anchorPane = loader.load();
            
            mainScene = new Scene(anchorPane);
            primaryStage.setScene(mainScene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Home");
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/view/imgs/icons/researchBooks.png")));
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Error main:");
        }
    }
    
    public static Scene getMainScene() {
        return mainScene;
    }
    
    public static Stage getStage() {
        return stage;
    }
    
    public static void main(String[] args) {
        Tools.crietListCategory();
        launch(args);
    }

}
