package com.uji.view;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.List;

public class RecommendationsView {
    private final VBox rootNode;
    private final ListView<String> recommendationsListView;

    public RecommendationsView() {
        this.rootNode = new VBox();
        this.recommendationsListView = new ListView<>();
        recommendationsListView.setPlaceholder(new Label("Presiona 'Recomendar' para ver resultados."));
        VBox.setVgrow(recommendationsListView, Priority.ALWAYS);

        rootNode.getChildren().add(recommendationsListView);
    }

    public void mostrarRecomendaciones(List<String> recommendedSongs) {
        if (recommendedSongs != null && !recommendedSongs.isEmpty()) {
            recommendationsListView.setItems(FXCollections.observableArrayList(recommendedSongs));
            recommendationsListView.setPlaceholder(null);
        } else {
            recommendationsListView.getItems().clear();
            recommendationsListView.setPlaceholder(new Label("No se encontraron canciones"));
        }
    }

    public Node getRootNode() {
        return rootNode;
    }

}