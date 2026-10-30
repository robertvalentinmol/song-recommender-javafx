package com.uji.view;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ClustersView {
    private final VBox rootNode;
    private final ScatterChart<Number, Number> scatterChart;
    private final NumberAxis EjeX;
    private final NumberAxis EjeY;

    public ClustersView() {
        this.rootNode = new VBox();
        
        this.EjeX = new NumberAxis();
        this.EjeY = new NumberAxis();

        this.scatterChart = new ScatterChart<>(EjeX, EjeY);
        scatterChart.setTitle("Clusters de las canciones");
        scatterChart.setAnimated(true);
        
        VBox.setVgrow(scatterChart, Priority.ALWAYS);


        rootNode.getChildren().add(scatterChart);
    }

    //Getters
    public VBox getRootNode() {
        return rootNode;
    }

    
}
