package com.fsb.networked;

import com.fsb.networked.utils.JSONParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private boolean windowForceClosed = false;
    private static Scene scene;
    private static String currentRootFilePath;
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginPage"));
        stage.setScene(scene);
        stage.setResizable(true);
        scene.getStylesheets().clear();
        stage.show();
        stage.setOnCloseRequest(event -> {
            windowForceClosed = true;
            resetJSONIfForceblyClosed(); // Perform any necessary cleanup tasks
        });
    }
    private void resetJSONIfForceblyClosed() {
        if (windowForceClosed) {
            JSONParser.resetIndividualJSONFile();
            JSONParser.resetEntrepriseJSONFile();
        }
    }
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        currentRootFilePath = fxml;
    }
    public static String getFxmlPath() throws IOException {
        return currentRootFilePath;
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}