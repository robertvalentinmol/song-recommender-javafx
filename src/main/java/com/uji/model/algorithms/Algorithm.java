package com.uji.model.algorithms;

import com.uji.model.dataStructure.Table;
import com.uji.model.dataStructure.TableWithLabels;

/**
 * Interfaz genérica para algoritmos como KNN o Kmeans.
 *
 * @param <T> Será una tabla, ya sea Table o TableWithLabels
 * @param <P> Será el tipo de parámetro que se le pase a "estimate"
 * @param <R> Será el valor de retorno de la estimación como Double,Integer...
 *
 * @see Table
 * @see TableWithLabels
 */
public interface Algorithm<T extends Table,P,R> {

    /**
     * Entrena un algoritmo con los datos de entrada proporcionados.
     *
     * @param datos Conjunto de datos, en formato tabla, con los que se entrenará el modelo.
     */
    void train(T datos);


    /**
     * Realiza una estimación en base a los datos proporcionados.
     *
     * @param dato Datos que se quieren analizar para obtener una prediccion.
     *
     * @return Resultado de la estimación, tanto en KNN como Kmeans devuelve un Integer.
     *
     */
    R estimate(P dato);

}
