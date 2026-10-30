package com.uji.model.dataStructure;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<String> headers;
    private List<Row> rows;

    // Constructor vacío
    public Table() {
        this.headers = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    public Table(List<String> headers) {
        this.headers = headers;
        this.rows = new ArrayList<>();
    }

    // Setters y Getters
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public List<Row> getRows() {
        return this.rows;
    }

    public List<String> getHeaders() {
        return this.headers;
    }

    public int getRowCount() {
        return this.rows.size();
    }

    // Métodos
    public void addRow(Row row) {
        this.rows.add(row);
    }

    public Row getRowAt(int rowNumber) {
        comprobarIndiceFila(rowNumber);
        return this.rows.get(rowNumber);
    }

    public List<Double> getColumnAt(int columnNumber) {
        comprobarIndiceColumna(columnNumber);

        List<Double> columnValues = new ArrayList<>();
        for (Row row : rows) {
            columnValues.add(row.getValueAt(columnNumber));
        }

        return columnValues;
    }

    private void comprobarIndice(int index, int size, String tipo) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("El índice " + index + " de la " + tipo + " está fuera de rango");
        }
    }

    private void comprobarIndiceFila(int index) {
        comprobarIndice(index, rows.size(), "fila");
    }

    private void comprobarIndiceColumna(int index) {
        comprobarIndice(index, headers.size(), "columna");
    }
}