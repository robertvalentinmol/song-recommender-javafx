package com.uji.model.filesManagement;

import com.uji.model.dataStructure.Table;

public abstract class ReaderTemplate<T extends Table> {

    protected T table;
    protected String source;

    public T readTableFromSource() {
        openSource(source);
        if (hasMoreData()) {
            String headerLine = getNextData();
            processHeaders(headerLine);
        }

        while (hasMoreData()) {
            String dataLine = getNextData();
            processData(dataLine);
        }

        closeSource();
        return table;
    }


    protected abstract void openSource(String source);
    protected abstract void processHeaders(String headers);
    protected abstract void processData(String data);
    protected abstract void closeSource();

    protected abstract boolean hasMoreData();
    protected abstract String getNextData();
}
