package com.jakobbeber.tam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Picture picture = new Picture();
        picture.matrika();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Picture.fxml"));






        // Create a scene with the loaded FXML file
        Scene scene = new Scene(root);

        primaryStage.setTitle("Main Menu");
        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

