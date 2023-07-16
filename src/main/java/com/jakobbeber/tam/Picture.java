package com.jakobbeber.tam;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Picture {

    @FXML
    private MenuBar menu;



    @FXML
    private GridPane mtx;

    public void matrika() {
        mtx = new GridPane();
        mtx.add(new Button("0"), 0, 0);

    }



    @FXML
    private Button start;

    @FXML
    private Button step;

    @FXML
    private Button stop;

    @FXML
    private TextField time;

}


