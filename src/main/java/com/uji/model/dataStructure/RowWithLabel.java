package com.uji.model.dataStructure;

import java.util.List;

public class RowWithLabel extends Row {
    private final String label; // Label es final, ya que no se debe modificar en ningún caso.

    public RowWithLabel(List<Double> values, String label){
        super(values);
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}