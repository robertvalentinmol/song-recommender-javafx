package com.uji.model.filesManagement;


import com.uji.model.dataStructure.Row;
import com.uji.model.dataStructure.Table;

import java.util.ArrayList;
import java.util.List;

public class CSVUnlabeledFileReader extends FileReader<Table> {

    private final List<String> headers = new ArrayList<>();
    private final List<Row> rows = new ArrayList<>();

    public CSVUnlabeledFileReader(String source) {
        this.source = source;
    }

    @Override
    protected void processHeaders(String headerLine) {
        String[] arrayHeaders = headerLine.split(",");
        for (String header : arrayHeaders) {
            headers.add(header.strip());
        }
    }

    @Override
    protected void processData(String dataLine) {
        String[] values = dataLine.split(",");
        List<Double> rowValues = new ArrayList<>();
        for (String value : values) {
            rowValues.add(Double.parseDouble(value));
        }
        rows.add(new Row(rowValues));
    }

    @Override
    public Table readTableFromSource() {
        table = new Table();
        super.readTableFromSource();
        return createTable();
    }

    protected Table createTable() {
        table.setHeaders(headers);
        table.setRows(rows);
        return table;
    }
}
