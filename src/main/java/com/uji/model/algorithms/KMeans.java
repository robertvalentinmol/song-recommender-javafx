package com.uji.model.algorithms;

import com.uji.model.dataStructure.Row;
import com.uji.model.dataStructure.Table;
import com.uji.model.distances.Distance;

import java.util.*;

/**
 * Algoritmo de clustering K-Means.
 * Agrupa las filas de una tabla en {@code numClusters} grupos y estima a qué grupo pertenece un nuevo punto.
 *
 * @see <a href="https://es.wikipedia.org/wiki/K-medias">Información sobre KMeans</a>
 */
public class KMeans implements Algorithm<Table, List<Double>, Integer> {

    private final int numClusters;
    private final int numIterations;
    private final long seed;
    private final Distance distance;
    private final List<Row> centroides = new ArrayList<>();
    private final Map<Row, List<Row>> clusters = new HashMap<>(); // Centroide -> filas asignadas a él.

    public KMeans(int numClusters, int numIterations, long seed, Distance distance) {
        this.numClusters = numClusters;
        this.numIterations = numIterations;
        this.seed = seed;
        this.distance = distance;
    }

    public Map<Row, List<Row>> getClusters() {
        return clusters;
    }

    /**
     * Entrena el modelo KMeans con los datos proporcionados. No modifica la tabla de entrada.
     *
     * @param datos La tabla de datos.
     * @throws InvalidClusterNumberException si numClusters > número de filas en datos.
     */
    @Override
    public void train(Table datos) {
        if (this.numClusters > datos.getRowCount())
            throw new InvalidClusterNumberException(this.numClusters, datos.getRowCount());

        centroides.clear();
        clusters.clear();

        // Paso 1: elegir k filas distintas al azar como centroides iniciales.
        // Se copian sus valores para no modificar los datos de entrenamiento al recalcular los centroides.
        Random random = new Random(seed);
        Set<Integer> indicesElegidos = new HashSet<>();
        while (centroides.size() < this.numClusters) {
            int indice = random.nextInt(datos.getRowCount());
            if (indicesElegidos.add(indice))
                centroides.add(new Row(new ArrayList<>(datos.getRowAt(indice).getData())));
        }

        // Pasos 2-4: asignar puntos, recalcular centroides y repetir N iteraciones.
        for (int i = 0; i < this.numIterations; i++)
            asignarPuntosYRecalcularCentroides(datos);
    }

    @Override
    public Integer estimate(List<Double> datos) {
        return indiceCentroideMasCercano(datos);
    }

    private void asignarPuntosYRecalcularCentroides(Table datos) {
        // Paso 2: asignar cada punto al centroide más cercano.
        clusters.clear();
        for (Row centroide : centroides)
            clusters.put(centroide, new ArrayList<>());

        for (Row row : datos.getRows()) {
            Row centroideMasCercano = centroides.get(indiceCentroideMasCercano(row.getData()));
            clusters.get(centroideMasCercano).add(row);
        }

        // Paso 3: recalcular cada centroide como la media de su grupo.
        // Si un grupo queda vacío, su centroide se mantiene donde estaba.
        for (Row centroide : centroides) {
            List<Row> grupo = clusters.get(centroide);
            if (!grupo.isEmpty())
                centroide.setData(calcularMedia(grupo));
        }
    }

    private int indiceCentroideMasCercano(List<Double> punto) {
        int indiceMinimo = 0;
        double minimo = Double.MAX_VALUE;

        for (int i = 0; i < centroides.size(); i++) {
            double d = distance.calculateDistance(punto, centroides.get(i).getData());
            if (d < minimo) {
                minimo = d;
                indiceMinimo = i;
            }
        }
        return indiceMinimo;
    }

    private List<Double> calcularMedia(List<Row> rows) {
        int numCaracteristicas = rows.getFirst().getData().size();
        List<Double> media = new ArrayList<>(numCaracteristicas);

        for (int i = 0; i < numCaracteristicas; i++) {
            double suma = 0;
            for (Row r : rows)
                suma += r.getValueAt(i);
            media.add(suma / rows.size());
        }
        return media;
    }
}
