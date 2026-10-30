package com.uji.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class HistoryView {
    private final VBox rootNode;
    private final ListView<String> historyListView;

    public HistoryView(){
        this.rootNode = new VBox();

        this.historyListView = new ListView<>();
        historyListView.setPlaceholder(new Label("El historial de recomendaciones aparecerá aquí."));
        VBox.setVgrow(historyListView, Priority.ALWAYS);

        rootNode.getChildren().add(historyListView);
    }
    
    public VBox getRootNode() {
        return rootNode;
    }

    public ListView<String> getHistoryListView() {
        return historyListView;
    }
    
}
