package com.jakobbeber.tam;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Menu {

    @FXML
    private RadioButton binary;

    @FXML
    private Button dist;

    @FXML
    private Button par;

    @FXML
    private Button seq;

    @FXML
    private Button start;

    @FXML
    private RadioButton triangle;

    @FXML
    private TextField xsize;

    @FXML
    private TextField ysize;



    @FXML
    void binary(ActionEvent event) {
        tileSetUp();

    }

    @FXML
    void mode(ActionEvent event) {
        setComputeMode(event);
    }

    @FXML
    void runSim(ActionEvent event) {
        // TO DO: return important values back & close this screen

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
            start.setDisable(false);
            i=0;
            j=0;
        }

    }





}
