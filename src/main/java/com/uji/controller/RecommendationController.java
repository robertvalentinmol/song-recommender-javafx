package com.uji.controller;

import com.uji.model.models.SongModel;
import com.uji.view.MainView;
import com.uji.view.RecommendationsView;
import java.util.List;

/**
 * Maneja la logica del botón recomendar.
 */
public class RecommendationController {

    private final MainView mainView;
    private final SongModel songModel;
    private final SongSelectionController songSelectionController;
    private final ConfigController filtersAndConfigController;
    private HistoryController historyController;

    public RecommendationController(MainView mainView, SongModel songModel, SongSelectionController songSelectionController,
                                        ConfigController filtersAndConfigController) {
        this.mainView = mainView;
        this.songModel = songModel;
        this.songSelectionController = songSelectionController;
        this.filtersAndConfigController = filtersAndConfigController;
    }
    
    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
    }
    
    public void manejoBotonRecomendar() {
        String cancionSeleccionada = songSelectionController.getSelectedSong();

        String algoritmo = null;
        String distancia = null;
        Integer numRecs = null;
        
        if (filtersAndConfigController != null) {
            algoritmo = filtersAndConfigController.getSelectedAlgorithm();
            distancia = filtersAndConfigController.getSelectedDistance();
            numRecs = filtersAndConfigController.getNumberOfRecommendations();
        }
        
        // Añadir historial y actualizar vista
        songModel.addCancionAlHistorial(cancionSeleccionada);
        if (historyController != null) {
            historyController.updateHistory();
        }

        long startTime = System.nanoTime();
        List<String> recomendaciones = songModel.getRecommendations(cancionSeleccionada, algoritmo, distancia, numRecs);
        long endTime = System.nanoTime();
        double timeTakenMillis = (endTime - startTime) / 1000000.0;

        // Mostrar recomendaciones
        RecommendationsView recView = mainView.getRecommendationsView();
        if (recView != null) {
            recView.mostrarRecomendaciones(recomendaciones);
        }

        // Actualizar info del footer
        updateFooterLabels(recomendaciones.size(), timeTakenMillis);
    }

    private void updateFooterLabels(int count, double timeMillis) {
        if (mainView.getCantidadLabel() != null) {
           mainView.getCantidadLabel().setText(" Canciones Recomendadas: " + count + " -- ");
        }

        String tiempoFormateado = String.format("Tiempo:  %.2f ms", timeMillis);
        if (mainView.getTiempoLabel() != null) {
            mainView.getTiempoLabel().setText(tiempoFormateado);
        }
    }
} 