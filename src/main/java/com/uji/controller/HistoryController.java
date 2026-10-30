package com.uji.controller;

import com.uji.model.models.SongModel;
import com.uji.view.HistoryView;
import com.uji.view.MainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.util.List;


public class HistoryController {

    private final HistoryView historyView;
    private final SongModel songModel;
    private ObservableList<String> observableHistory;

    public HistoryController(MainView mainView, SongModel songModel) {
        this.historyView = mainView.getHistoryView(); 
        this.songModel = songModel;
        this.observableHistory = FXCollections.observableArrayList();
        unirHistoryViewAlModel();
    }


    private void unirHistoryViewAlModel() {
        ListView<String> historyListView = historyView.getHistoryListView();
        if (historyListView == null) return;
        
        updateHistory();
        
        historyListView.setItems(observableHistory);
    }
    

    public void updateHistory() {
        if (observableHistory == null) {
            observableHistory = FXCollections.observableArrayList();
        }
        
        List<String> currentHistory = songModel.getHistorial();
        
        observableHistory.clear();
        observableHistory.addAll(currentHistory);
        
    }

} 