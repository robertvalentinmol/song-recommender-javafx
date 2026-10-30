package com.uji.controller;

import com.uji.model.models.SongModel;
import com.uji.view.SongSelectionView;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class SongSelectionController {
    private final SongSelectionView view;
    private final SongModel model;
    private final List<String> todasLasCanciones;
    private String cancionSeleccionadaActual;

    public SongSelectionController(SongSelectionView view, SongModel model) {
        this.view = view;
        this.model = model;
        this.cancionSeleccionadaActual = null;

        this.todasLasCanciones = this.model.getTodasLasCanciones();

        ListView<String> listView = this.view.getCancionListView();
        if (listView != null) {
            listView.getItems().clear();
            listView.getItems().addAll(todasLasCanciones);
        }

        initializeListeners();
    }

    private void initializeListeners() {
        TextField searchField = view.getSearchField();
      
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {aplicarFiltro(newValue);});

        ListView<String> listView = view.getCancionListView();

        if (listView != null) {
            listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                this.cancionSeleccionadaActual = newValue;});
        }
    }

    /**
     * Filtra las canciones según el texto de busqueda
     */
    private void aplicarFiltro(String textoBusqueda) {
        ListView<String> listView = view.getCancionListView();
        if (listView == null) return;
        
        listView.getItems().clear();
        
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            listView.getItems().addAll(todasLasCanciones);
        } else {
            List<String> cancionesFiltradas = new ArrayList<>();
            
            for (String cancion : todasLasCanciones) {
                if (cancion.toLowerCase().contains(textoBusqueda.toLowerCase())) {
                    cancionesFiltradas.add(cancion);
                }
            }
            
            listView.getItems().addAll(cancionesFiltradas);
        }
    }

    public String getSelectedSong() {
        return this.cancionSeleccionadaActual;
    }
}
