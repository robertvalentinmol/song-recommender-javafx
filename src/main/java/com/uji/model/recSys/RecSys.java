package com.uji.model.recSys;

import com.uji.model.algorithms.Algorithm;
import com.uji.model.algorithms.KMeans;
import com.uji.model.algorithms.KNN;
import com.uji.model.dataStructure.Table;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Sistema de recomendación de canciones. Delega en un {@link Algorithm} (patrón Strategy)
 * para asignar a cada canción un grupo o clase, y recomienda canciones del mismo grupo.
 *
 * @param <T> Tipo de tabla con la que se entrena el algoritmo.
 * @see KNN
 * @see KMeans
 */
public class RecSys<T extends Table> {
    private final Algorithm<T, List<Double>, Integer> algorithm;
    // LinkedHashMap: conserva el orden de los datos para que las recomendaciones sean deterministas.
    private final Map<String, Integer> estimations = new LinkedHashMap<>();

    public RecSys(Algorithm<T, List<Double>, Integer> algorithm) {
        this.algorithm = algorithm;
    }

    public void train(T trainData) {
        algorithm.train(trainData);
    }

    /**
     * Calcula la estimación (grupo o clase) de cada elemento de prueba.
     *
     * @param testData      Datos de prueba.
     * @param testItemNames Nombres de los elementos, en el mismo orden que las filas de {@code testData}.
     */
    public void initialise(Table testData, List<String> testItemNames) {
        estimations.clear();
        for (int i = 0; i < testItemNames.size(); i++) {
            List<Double> data = testData.getRowAt(i).getData();
            estimations.put(testItemNames.get(i), algorithm.estimate(data));
        }
    }

    /**
     * Recomienda elementos del mismo grupo que el elemento indicado, sin incluirlo a él.
     *
     * @throws LikedItemNotFoundException si el elemento no está en los datos.
     */
    public List<String> recommend(String nameLikedItem, int numRecommendations) {
        Integer likedItemEstimation = estimations.get(nameLikedItem);
        if (likedItemEstimation == null)
            throw new LikedItemNotFoundException(nameLikedItem);

        List<String> recommendations = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : estimations.entrySet()) {
            if (recommendations.size() >= numRecommendations)
                break;
            if (entry.getValue().equals(likedItemEstimation) && !entry.getKey().equals(nameLikedItem))
                recommendations.add(entry.getKey());
        }
        return recommendations;
    }
}
