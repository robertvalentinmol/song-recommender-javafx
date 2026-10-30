package com.uji.model.models;

import com.uji.model.algorithms.KMeans;
import com.uji.model.algorithms.KNN;
import com.uji.model.dataStructure.Table;
import com.uji.model.dataStructure.TableWithLabels;
import com.uji.model.distances.Distance;
import com.uji.model.distances.EuclideanDistance;
import com.uji.model.distances.ManhattanDistance;
import com.uji.model.filesManagement.CSVLabeledFileReader;
import com.uji.model.recSys.RecSys;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Modelo principal que gestiona canciones y recomendaciones.
 * Los datos se leen una sola vez y cada combinación algoritmo + distancia se entrena
 * la primera vez que se usa; después se reutiliza.
 */
public class SongModel {

    private static final String SONG_NAMES_PATH = "songs_test_names.csv";
    private static final String TRAIN_PATH = "songs_train.csv";
    private static final String TEST_PATH = "songs_test.csv";

    private static final int NUM_CLUSTERS = 15;
    private static final int NUM_ITERACIONES = 10;
    private static final long SEED = 123;

    private final List<String> todasLasCanciones;
    private final HistoryModel historial = new HistoryModel();
    private final Map<String, RecSys<?>> recSysCache = new HashMap<>();

    private TableWithLabels trainData;
    private TableWithLabels testData;

    public SongModel() {
        this.todasLasCanciones = readNames(SONG_NAMES_PATH);
    }

    /**
     * Lee los nombres de las canciones (uno por línea) de un fichero del classpath.
     */
    private List<String> readNames(String fileOfItemNames) {
        List<String> names = new ArrayList<>();
        InputStream in = getClass().getClassLoader().getResourceAsStream(fileOfItemNames);
        if (in == null)
            throw new IllegalStateException("No se encuentra el fichero: " + fileOfItemNames);

        try (Scanner scanner = new Scanner(in, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine())
                names.add(scanner.nextLine());
        }
        return names;
    }

    // Métodos del historial
    public void addCancionAlHistorial(String cancion) {
        historial.addCancionAlHistorial(cancion);
    }

    public List<String> getHistorial() {
        return historial.getHistorial();
    }

    public List<String> getTodasLasCanciones() {
        return this.todasLasCanciones;
    }

    /**
     * Genera recomendaciones con el algoritmo ("kmeans" o "knn") y la distancia ("euclidea" o "manhattan") indicados.
     */
    public List<String> getRecommendations(String cancion, String algoritmo, String distancia, int cantidad) {
        boolean usarKMeans = "kmeans".equalsIgnoreCase(algoritmo);
        boolean usarManhattan = "manhattan".equalsIgnoreCase(distancia);
        String clave = (usarKMeans ? "kmeans" : "knn") + "-" + (usarManhattan ? "manhattan" : "euclidea");

        RecSys<?> recSys = recSysCache.computeIfAbsent(clave, k -> crearRecSys(usarKMeans, usarManhattan));
        return recSys.recommend(cancion, cantidad);
    }

    private RecSys<?> crearRecSys(boolean usarKMeans, boolean usarManhattan) {
        cargarDatos();
        Distance distancia = usarManhattan ? new ManhattanDistance() : new EuclideanDistance();

        if (usarKMeans) {
            RecSys<Table> recSys =
                    new RecSys<>(new KMeans(NUM_CLUSTERS, NUM_ITERACIONES, SEED, distancia));
            recSys.train(trainData);
            recSys.initialise(testData, todasLasCanciones);
            return recSys;
        }

        RecSys<TableWithLabels> recSys = new RecSys<>(new KNN(distancia));
        recSys.train(trainData);
        recSys.initialise(testData, todasLasCanciones);
        return recSys;
    }

    private void cargarDatos() {
        if (trainData == null) {
            trainData = new CSVLabeledFileReader(TRAIN_PATH).readTableFromSource();
            testData = new CSVLabeledFileReader(TEST_PATH).readTableFromSource();
        }
    }
}
