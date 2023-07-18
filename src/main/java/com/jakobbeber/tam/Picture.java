package com.jakobbeber.tam;

import com.google.common.collect.Table;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class Picture {


    private Assembly assembly;

    public Picture(Assembly assembly) {
        this.assembly = assembly;
    }



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
        stepx = 0;
        stepy = 0;
    }

    @FXML
    void startTable(ActionEvent event) {
        builderTable();
    }

    private int stepx = 0;
    private int stepy = 0;

    @FXML
    void stepBystep(ActionEvent event) {
        for (int i = 0; i < stepx+1; i++) {
            addKocka(stepx, stepy-i);
        }
        for (int i = 0; i < stepy+1; i++) {
            addKocka(stepx-i, stepy);
        }
        stepx++;
        stepy++;
    }

    void builderTable() {


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                addKocka(i, j);

            }
        }
        stepx=10;
        stepy=10;

    }

    void addKocka(int x, int y){

        Table<Integer, Integer, Tile> table = assembly.getTable();
        String string = Objects.requireNonNull(table.get(x, y)).getName();
        TextField tf = new TextField(string);
        tf.setPrefHeight(30);
        tf.setPrefWidth(30);
        tf.setAlignment(Pos.CENTER);
        tf.setEditable(false);
        mtx.add(tf, x, y);


    }

}
