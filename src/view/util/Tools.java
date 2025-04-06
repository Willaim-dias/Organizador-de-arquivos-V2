package view.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Tools {

    private static File file = new File("category.txt");

    public static void crietListCategory() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    public static void addCategory(String category) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));

            pw.println(category);
            pw.close();
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    public static List<String> readListCategory() {
        try {
            Scanner sc = new Scanner(file);
            List listCategory = new ArrayList();

            while (sc.hasNextLine()) {
                listCategory.add(sc.nextLine());
            }
            return listCategory;
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
        return null;
    }

    public static Stage currentStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
}
