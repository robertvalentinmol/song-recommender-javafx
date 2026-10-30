package com.uji.model.filesManagement;

import com.uji.model.dataStructure.RowWithLabel;
import com.uji.model.dataStructure.TableWithLabels;

import java.util.ArrayList;
import java.util.List;

public class CSVLabeledFileReader extends FileReader<TableWithLabels> {

    private final List<String> headers = new ArrayList<>();
    private final List<RowWithLabel> rows = new ArrayList<>();

    public CSVLabeledFileReader(String source) {
        this.source = source;
    }

    @Override
    protected void processHeaders(String headerLine) {
        String[] arrayHeaders = headerLine.split(",");
        for (int i = 0; i < arrayHeaders.length - 1; i++) {
            headers.add(arrayHeaders[i].strip());
        }
    }

    @Override
    protected void processData(String dataLine) {
        String[] values = dataLine.split(",");
        List<Double> rowValues = new ArrayList<>();
        for (int i = 0; i < values.length - 1; i++) {
            rowValues.add(Double.parseDouble(values[i]));
        }
        String label = values[values.length - 1];
        rows.add(new RowWithLabel(rowValues, label));
    }

    @Override
    public TableWithLabels readTableFromSource() {
        table = new TableWithLabels();
        super.readTableFromSource();
        return createTable();
    }



    protected TableWithLabels createTable() {
        table.setHeaders(headers);
        table.setRowsWithLabels(rows);
        return table;
    }
}