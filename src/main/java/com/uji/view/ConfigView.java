package com.uji.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ConfigView {
    private final TitledPane rootNode;
    private final ScrollPane scrollPane;
    private final VBox ConfigContentBox;

    //Parte de configuración
    private final ComboBox<String> algoritmoComboBox;
    private final ComboBox<String> distanciaComboBox;
    private final Spinner<Integer> numeroRecomendacionesSpinner;

    public ConfigView() {
        this.rootNode = new TitledPane();
        rootNode.setText("Configuración");
        rootNode.setCollapsible(true);
        rootNode.setExpanded(false);
        rootNode.setAnimated(true);
        rootNode.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(rootNode, Priority.ALWAYS);

        this.scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        //Parte de configuración principal
        this.ConfigContentBox = new VBox();
        this.ConfigContentBox.setAlignment(Pos.CENTER);
        this.ConfigContentBox.setSpacing(15);
        this.ConfigContentBox.setPadding(new Insets(10));

        Label configLabel = new Label("Configuración Principal");
        this.ConfigContentBox.getChildren().add(configLabel);

        GridPane configGrid = new GridPane();
        configGrid.setAlignment(Pos.CENTER);
        configGrid.setVgap(15);
        
        this.algoritmoComboBox = new ComboBox<>();
        algoritmoComboBox.getItems().addAll("KMeans", "KNN");
        algoritmoComboBox.setValue("KMeans");

        this.distanciaComboBox = new ComboBox<>();
        distanciaComboBox.getItems().addAll("Euclidean", "Manhattan");
        distanciaComboBox.setValue("Euclidean");

        int defaultNumRecomendaciones = 5;
        this.numeroRecomendacionesSpinner = new Spinner<>(1, 100, defaultNumRecomendaciones);
        numeroRecomendacionesSpinner.setEditable(true);

        configGrid.add(new Label("Algoritmo: "), 0, 0);
        configGrid.add(this.algoritmoComboBox, 1, 0);
        configGrid.add(new Label("Distancia: "), 0, 1);
        configGrid.add(this.distanciaComboBox, 1, 1);
        configGrid.add(new Label("Cantidad: "), 0, 2);
        configGrid.add(this.numeroRecomendacionesSpinner, 1, 2);

        this.ConfigContentBox.getChildren().add(configGrid);
        
        scrollPane.setContent(ConfigContentBox);
        rootNode.setContent(scrollPane);
    }

    //Getters
    public TitledPane getRootNode() {
        return rootNode;
    }
    
    public ComboBox<String> getAlgoritmoComboBox() {
        return algoritmoComboBox;
    }

    public ComboBox<String> getDistanciaComboBox() {
        return distanciaComboBox;
    }

    public Spinner<Integer> getNumRecomendacionesSpinner() {
        return numeroRecomendacionesSpinner;
    }
}
