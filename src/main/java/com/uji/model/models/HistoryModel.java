package com.uji.model.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona el historial de canciones seleccionadas.
 */
public class HistoryModel {
    
    private final List<String> historial;
    
    public HistoryModel() {
        this.historial = new ArrayList<>();
    }
    
    /**
     * Añade una canción al principio del historial.
     * Si ya existe, la pone al principio.
     */
    public void addCancionAlHistorial(String cancion) {
        if (cancion == null || cancion.isEmpty() || historial.contains(cancion) ) return;

        historial.addFirst(cancion);
    }
    
    
    public List<String> getHistorial() {
        return historial;
    }
    
    

} 