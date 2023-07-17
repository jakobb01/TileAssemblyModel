package com.jakobbeber.tam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Picture {

    @FXML
    private MenuBar menu;

    @FXML
    private GridPane mtx;

    @FXML
    private Button reset;

    @FXML
    private Button start;

    @FXML
    private Button step;

    @FXML
    private TextField time;

    @FXML
    void resetTable(ActionEvent event) {
        Node node = mtx.getChildren().get(0);
        mtx.getChildren().clear();
        mtx.getChildren().add(0,node);

    }

    @FXML
    void startTable(ActionEvent event) {
        builderTable();

    }

    @FXML
    void stepBystep(ActionEvent event) {

    }

    void builderTable() {


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                TextField tf = new TextField("SEED");
                tf.setPrefHeight(50);
                tf.setPrefWidth(50);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);
                mtx.add(tf, i, j);

            }
        }

    }

}
