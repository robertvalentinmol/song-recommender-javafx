package com.uji.controller;

import com.uji.view.ConfigView;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

public class ConfigController {

    private final ConfigView view;

    public ConfigController(ConfigView view) {
        this.view = view;
    }

    public String getSelectedAlgorithm() {
        ComboBox<String> comboBox = view.getAlgoritmoComboBox();
        return (comboBox != null) ? comboBox.getValue() : null;
    }

    public String getSelectedDistance() {
        ComboBox<String> comboBox = view.getDistanciaComboBox();
        return (comboBox != null) ? comboBox.getValue() : null;
    }

    public Integer getNumberOfRecommendations() { 
        Spinner<Integer> spinner = view.getNumRecomendacionesSpinner();
        return (spinner != null) ? spinner.getValue() : null;
    }

}
