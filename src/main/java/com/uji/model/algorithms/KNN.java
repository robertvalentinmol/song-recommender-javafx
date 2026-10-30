package com.uji.model.algorithms;

import com.uji.model.dataStructure.RowWithLabel;
import com.uji.model.dataStructure.TableWithLabels;
import com.uji.model.distances.Distance;

import java.util.*;

/**
 * Clasificador k vecinos más próximos (k-NN).
 * Estima la clase de un punto por votación entre las {@code k} filas de entrenamiento más cercanas.
 * Con {@code k = 1} (valor por defecto) devuelve la clase del vecino más cercano.
 *
 * @see <a href="https://es.wikipedia.org/wiki/K_vecinos_m%C3%A1s_pr%C3%B3ximos">Información sobre KNN</a>
 */
public class KNN implements Algorithm<TableWithLabels, List<Double>, Integer> {

    private final int k;
    private final Distance distance;
    private TableWithLabels tabla;

    public KNN(Distance distance) {
        this(1, distance);
    }

    public KNN(int k, Distance distance) {
        if (k < 1)
            throw new IllegalArgumentException("k debe ser al menos 1");
        this.k = k;
        this.distance = distance;
    }

    @Override
    public void train(TableWithLabels data) {
        this.tabla = data;
    }

    /**
     * Estima la clase de un punto.
     *
     * @param data Valores del punto a clasificar.
     * @return Índice de la clase más votada entre los k vecinos más cercanos.
     *         En caso de empate gana la clase con el vecino más cercano.
     */
    @Override
    public Integer estimate(List<Double> data) {
        List<RowWithLabel> filas = tabla.getRowsWithLabels();

        List<Integer> indices = new ArrayList<>(filas.size());
        double[] distancias = new double[filas.size()];
        for (int i = 0; i < filas.size(); i++) {
            distancias[i] = distance.calculateDistance(data, filas.get(i).getData());
            indices.add(i);
        }
        indices.sort(Comparator.comparingDouble(i -> distancias[i]));

        // Votación entre los k más cercanos (LinkedHashMap conserva el orden de cercanía para desempatar).
        Map<String, Integer> votos = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(k, indices.size()); i++)
            votos.merge(filas.get(indices.get(i)).getLabel(), 1, Integer::sum);

        String ganadora = null;
        int maxVotos = 0;
        for (Map.Entry<String, Integer> e : votos.entrySet()) {
            if (e.getValue() > maxVotos) {
                maxVotos = e.getValue();
                ganadora = e.getKey();
            }
        }
        return tabla.getLabelAsInteger(ganadora);
    }
}
