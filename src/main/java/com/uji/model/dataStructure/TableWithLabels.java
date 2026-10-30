package com.uji.model.dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table {
    private final Map<String, Integer> clases = new HashMap<>();

    // Constructor vacío
    public TableWithLabels() {
        super();
    }

    // Métodos
    @Override
    public RowWithLabel getRowAt(int rowNumber) {
        return (RowWithLabel) super.getRowAt(rowNumber);
    }

    public void addRow(RowWithLabel row) {
        super.addRow(row);
        List<RowWithLabel> rows = new ArrayList<>();
        rows.add(row);
        labelsToIndex(rows); // Se indexarán directamente en el mapa "clases".
    }
    public List<RowWithLabel> getRowsWithLabels() {
        List<RowWithLabel> rowsWithLabels = new ArrayList<>();
        for (Row row : super.getRows()) {
            rowsWithLabels.add((RowWithLabel) row);
        }
        return rowsWithLabels;
    }

    public void setRowsWithLabels(List<RowWithLabel> rows) {
        super.setRows(new ArrayList<>(rows));
        labelsToIndex(rows);
    }

    public Integer getLabelAsInteger(String label){
        return clases.get(label);
    }

    //Convierte una etiqueta a un número entero, que representa una de las clases.
    public void labelsToIndex(List<RowWithLabel> rows){
        for (RowWithLabel row: rows){
            String label = row.getLabel();
            if (!clases.containsKey(label))
                clases.put(label, clases.size());
        }
    }
}
