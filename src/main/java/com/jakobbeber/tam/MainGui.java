package com.jakobbeber.tam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainGui extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file
        //ControllerGui controllerGui = new ControllerGui(new Assembly());
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));

        // Create a scene with the loaded FXML file
        Scene scene = new Scene(root);

        stage.setTitle("Main Menu");
        // Set the scene on the primary stage
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}

