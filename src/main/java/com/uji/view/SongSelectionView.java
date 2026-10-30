package com.uji.view;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SongSelectionView {
    private final TitledPane rootNode;
    private final TextField searchField;
    private final ListView<String> cancionListView;

    public SongSelectionView() {
        this.rootNode = new TitledPane();
        rootNode.setText("Selecciona una canción");
        rootNode.setCollapsible(false);
        rootNode.setExpanded(true);

        VBox contentBox = new VBox();
        rootNode.setContent(contentBox);

        this.searchField = new TextField();
        searchField.setPromptText("Buscar canción...");
        contentBox.getChildren().add(searchField);

        this.cancionListView = new ListView<>();
        cancionListView.setPlaceholder(new Label("Cargando canciones..."));
        VBox.setVgrow(cancionListView, Priority.ALWAYS);
        contentBox.getChildren().add(cancionListView);


        rootNode.setContent(contentBox);

    }

    //Getters
    public TitledPane getRootNode() {
        return rootNode;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public ListView<String> getCancionListView() {
        return cancionListView;
    }
    
}
