package com.jakobbeber.tam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Objects;

public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Test_fx.fxml"));


        // Create a scene with the loaded FXML file
        Scene scene = new Scene(root);

        primaryStage.setTitle("Test");
        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

