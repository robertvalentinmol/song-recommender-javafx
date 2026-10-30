package com.uji.controller;

import com.uji.model.models.SongModel;
import com.uji.view.*;
import javafx.scene.control.*;

public class MainController {
    private final MainView view;
    private final SongModel songModel;
    private final SongSelectionController songSelectionController;
    private final ConfigController configController;
    private final RecommendationController recommendationController;
    private final MenuActionController menuActionController;
    private final HistoryController historyController;

    public MainController(MainView view, SongModel songModel) {
        this.view = view;
        this.songModel = songModel;


        // Controlador para la selección de canciones
        SongSelectionView songSelectionV = view.getSongSelectionView();
        this.songSelectionController = new SongSelectionController(songSelectionV, this.songModel);


        // Controlador para config
        ConfigView ConfigV = view.getFiltersAndConfigView();
        this.configController = new ConfigController(ConfigV);

        this.historyController = new HistoryController(view, songModel); 

        this.recommendationController = new RecommendationController(view, songModel, songSelectionController, configController);
        this.recommendationController.setHistoryController(this.historyController);
        
        this.menuActionController = new MenuActionController(view, songModel);

        initializeButtonActions(); 
        initializeMenuActions();
        
    }

    private void initializeButtonActions() {
        Button botonRecomendar = view.getRecomendarButton();

        botonRecomendar.setOnAction(event -> {
            try {
                recommendationController.manejoBotonRecomendar();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        recommendButtonOnOff(botonRecomendar);
       
    }
    
   private void recommendButtonOnOff(Button botonRecomendar) {

        SongSelectionView songSelectionV = view.getSongSelectionView();
        ListView<String> listView = songSelectionV.getCancionListView();

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> botonRecomendar.setDisable(newVal == null));
        botonRecomendar.setDisable(listView.getSelectionModel().getSelectedItem() == null);
    }

    private void initializeMenuActions() {
        menuActionController.setupMenuListeners();
    }

}

