package com.uji.model.dataStructure;

import java.util.List;

public class Row { //
    private List<Double> values;

    public Row(List<Double> values){
        comprobarLista(values);
        this.values = values;
    }

    public List<Double> getData(){
        return values;
    }

    public Double getValueAt(int index){
        comprobarIndice(index);
        return values.get(index);
    }

    public void setData(List<Double> values){
        comprobarLista(values);
        this.values = values;
    }

    private void comprobarLista(List<Double> lista){ // Se podria poner protected si en RowWithLabel se necesitase. También en comprobarIndice().
        if (lista == null)
            throw new NullPointerException("La lista no puede ser null");
        if (lista.isEmpty())
            throw new IllegalArgumentException("La lista no puede estar vacia");
    }

    private void comprobarIndice(int index){
        if (index<0 || index >= values.size())
            throw  new IndexOutOfBoundsException("El indice" + index + " está fuera de rango");
    }
}
