package com.jakobbeber.tam;

import com.google.common.collect.HashBasedTable;
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


    private static Assembly assembly;

    private String tileSetUp;
    private long xlengthTable = 100;
    private long ylengthTable = 100;
    private int computeType = -1;
    private long computeTime = -1;


    @FXML
    void runProcess(ActionEvent event) throws InterruptedException, IOException {
        // TO DO: run mainProcess & return important values back
        assembly = new Assembly();
        assembly.setTable(HashBasedTable.create());
        MainProcess mainProcess = new MainProcess(assembly);

        computeTime = mainProcess.main_process(tileSetUp, (xlengthTable*2), (ylengthTable*2), computeType);


        // Open Picture.fxml screen

        root = FXMLLoader.load(getClass().getResource("/fxml/Picture.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Picture");
        stage.setScene(scene);
        stage.show();
        //setTime();
    }


    @FXML
    void x_size(ActionEvent event) {
        text_numbers_x();
        get_x_size();
    }

    @FXML
    void y_size(ActionEvent event) {
        text_numbers_y();
        get_y_size();
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
        tileSetUp = toogleGroupValue;

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

    void get_y_size() {
        ylengthTable = Long.parseLong(ysize.getText());
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

    void get_x_size() {
        xlengthTable = Long.parseLong(xsize.getText());
    }

    void setComputeMode(ActionEvent event) {
        Button x = (Button) event.getSource();
        if (x.getId().equals(seq.getId()))
            computeType = 0;
        else if(x.getId().equals(par.getId()))
            computeType = 1;
        else if(x.getId().equals(dist.getId()))
            computeType = 2;

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


    void setTime () {
        time.setText(String.valueOf(computeTime));
    }

    @FXML
    void resetTable(ActionEvent event) {
        if (!(stepx == 0 && stepy == 0)) {
            Node node = mtx.getChildren().get(0);
            mtx.getChildren().clear();
            stepx = 0;
            stepy = 0;
        }
    }

    @FXML
    void startTable(ActionEvent event) {
        builderTable();
    }

    private int stepx = 0;
    private int stepy = 0;

    @FXML
    void stepBystep(ActionEvent event) {
        if (stepx < xlengthTable) {
            for (int i = 0; i < stepx+1; i++) {
                addKocka(stepx, stepy-i);
            }
        }
        if (stepy < ylengthTable) {
            for (int i = 0; i < stepy+1; i++) {
                addKocka(stepx-i, stepy);
            }
        }
        stepx++;
        stepy++;
    }

    void builderTable() {
        int x = 30;/*(Integer.parseInt(xsize.getText()))*/
        int y = 30;/*(Integer.parseInt(ysize.getText()))*/

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                addKocka(i, j);
            }
        }
        stepx=x;
        stepy=y;

    }

    void addKocka(int x, int y){

        //get string from table
        Table<Integer, Integer, Tile> table = assembly.getTable();
        String string = Objects.requireNonNull(table.get(x, y)).getName();
        String color = Objects.requireNonNull(table.get(x, y)).getColor();


        TextField tf = new TextField(string);
        tf.setStyle("-fx-background-color: " + color + ";");


        //TextField tf = new TextField("S");
        tf.setPrefHeight(30);
        tf.setPrefWidth(30);
        tf.setAlignment(Pos.CENTER);
        tf.setEditable(false);
        mtx.add(tf, x, y);


    }





}
