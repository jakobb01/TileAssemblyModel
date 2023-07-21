package com.jakobbeber.tam;

import com.google.common.collect.Table;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerGui {

    @FXML
    private RadioButton binary;

    @FXML
    private Button dist;

    @FXML
    private Button par;

    @FXML
    private Button seq;

    @FXML
    private Button start_process;

    @FXML
    private RadioButton triangle;

    @FXML
    private TextField xsize;

    @FXML
    private TextField ysize;

    @FXML
    private GridPane mtx;


    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    void binary(ActionEvent event) {
        tileSetUp();
    }

    @FXML
    void mode(ActionEvent event) {
        setComputeMode(event);
    }


    private Assembly assembly;

    @FXML
    void runProcess(ActionEvent event) throws InterruptedException, IOException {
        // TO DO: run mainProcess & return important values back
        Assembly assembly = new Assembly();
        MainProcess mainProcess = new MainProcess(assembly);
        MainProcess.main_process();


        // Open Picture.fxml screen

        root = FXMLLoader.load(getClass().getResource("/fxml/Picture.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Picture");
        stage.setScene(scene);
        stage.show();


    }


    @FXML
    void x_size(ActionEvent event) {
        text_numbers_x();
    }

    @FXML
    void y_size(ActionEvent event) {
        text_numbers_y();
    }

    @FXML
    void trinagle(ActionEvent event) {
        tileSetUp();
    }

    void tileSetUp() {
        ToggleGroup toggleGroup = new ToggleGroup();
        binary.setToggleGroup(toggleGroup);
        triangle.setToggleGroup(toggleGroup);
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getId();
        System.out.println(toogleGroupValue);

        if (j == 0) { j = 1; } else {j = 1; }
        startButtonActive();
    }

    void text_numbers_y() {
        ysize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    ysize.setText(oldValue);
                }
            }
        });

    }

    void text_numbers_x() {
        xsize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    xsize.setText(oldValue);
                }
            }
        });

    }

    void setComputeMode(ActionEvent event) {
        Button x = (Button) event.getSource();
        if (x.getId().equals(seq.getId()))
            System.out.println("0");
        else if(x.getId().equals(par.getId()))
            System.out.println("1");
        else if(x.getId().equals(dist.getId()))
            System.out.println("2");

        if (i == 0) { i = 1; } else {i = 1; }
        startButtonActive();

    }

    private int i = 0;
    private int j = 0;

    void startButtonActive() {
        if (i == 1 && j == 1) {
            start_process.setDisable(false);
            i=0;
            j=0;
        }

    }

    //--------------------------------------------------------------------------------------

    //Picture functions

    //--------------------------------------------------------------------------------------

    @FXML
    private Button reset;

    @FXML
    private Button start_table;

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

        //get string from table
        /*
        Table<Integer, Integer, Tile> table = assembly.getTable();
        String string = Objects.requireNonNull(table.get(x, y)).getName();

        TextField tf = new TextField(string);

         */
        TextField tf = new TextField("S");
        tf.setPrefHeight(30);
        tf.setPrefWidth(30);
        tf.setAlignment(Pos.CENTER);
        tf.setEditable(false);
        mtx.add(tf, x, y);


    }





}
