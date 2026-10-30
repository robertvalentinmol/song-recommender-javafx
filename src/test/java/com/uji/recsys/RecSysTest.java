
package com.uji.recsys;



import com.uji.model.algorithms.Algorithm;
import com.uji.model.algorithms.KMeans;
import com.uji.model.algorithms.KNN;
import com.uji.model.dataStructure.Table;
import com.uji.model.distances.Distance;
import com.uji.model.distances.EuclideanDistance;
import com.uji.model.filesManagement.CSVLabeledFileReader;
import com.uji.model.recSys.LikedItemNotFoundException;
import com.uji.model.recSys.RecSys;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RecSysTest {

    private RecSys recSys;
    private Algorithm algorithm;

    private Table trainTable;
    private Table testTable;
    private List<String> testItemNames;

    private int numRecommendations = 5;

    private Distance distance = new EuclideanDistance();


    @AfterEach
    void tearDown() {
        algorithm = null;
        recSys = null;
        trainTable = null;
        testTable = null;
        testItemNames = null;
    }

    @Nested
    class KNNRecSys {

        @BeforeEach
        // TODO: añadir o eliminar excepciones según tu implementación
        void setUp() throws IOException {
            trainTable = new CSVLabeledFileReader("songs_train.csv").readTableFromSource();
            testTable = new CSVLabeledFileReader("songs_test.csv").readTableFromSource();
            testItemNames = readNames("songs_test_names.csv");

            algorithm = new KNN(distance);
            recSys = new RecSys(algorithm);
            recSys.train(trainTable);
            recSys.initialise(testTable, testItemNames);
        }

        @Test
        @DisplayName("RecSys[KNN] - estimate")
        void estimate() throws LikedItemNotFoundException {
            List<String> recommendations = recSys.recommend("The Weekend", numRecommendations);

            assertEquals(numRecommendations, recommendations.size());
            assertFalse(recommendations.contains("The Weekend"));
        }

        @Test
        @DisplayName("RecSys[KNN] - estimate: liked item not returned")
        void estimate_likedItemNotInRecommendation() throws LikedItemNotFoundException {
            List<String> recommendations = recSys.recommend("Inside Out", numRecommendations);

            assertEquals(numRecommendations, recommendations.size());
            assertFalse(recommendations.contains("Inside Out"));
        }

        @Test
        @DisplayName("RecSys[KNN] - estimate: liked item not found")
        void estimate_likedItemNotFound() {
            assertThrows(LikedItemNotFoundException.class, () -> recSys.recommend("Inside Ouuuut", numRecommendations));
        }
    }

    @Nested
    class KMeansRecSys {

        private int numClusters = 15;
        private int numIterations = 10;
        private long seed = 53;

        @BeforeEach
        // TODO: añadir o eliminar excepciones según tu implementación
        void setUp() throws IOException {
            trainTable = new CSVLabeledFileReader("recommender/songs_train_withoutnames.csv").readTableFromSource();
            testTable = new CSVLabeledFileReader("recommender/songs_test_withoutnames.csv").readTableFromSource();

            testItemNames = readNames("songs_test_names.csv");

            algorithm = new KMeans(numClusters, numIterations, seed,distance);
            recSys = new RecSys(algorithm);
            recSys.train(trainTable);
            recSys.initialise(testTable, testItemNames);
        }

        @Test
        @DisplayName("RecSys[KMeans] - estimate")
        void estimate() throws LikedItemNotFoundException {
            List<String> recommendations = recSys.recommend("The Weekend", numRecommendations);

            assertEquals(numRecommendations, recommendations.size());
            assertFalse(recommendations.contains("The Weekend"));
        }

        @Test
        @DisplayName("RecSys[KMeans] - estimate: liked item not returned")
        void estimate_likedItemNotInRecommendation() throws LikedItemNotFoundException {
            List<String> recommendations = recSys.recommend("Inside Out", numRecommendations);

            assertEquals(numRecommendations, recommendations.size());
            assertFalse(recommendations.contains("Inside Out"));
        }

        @Test
        @DisplayName("RecSys[KMeans] - estimate: liked item not found")
        void estimate_likedItemNotFound() {
            assertThrows(LikedItemNotFoundException.class, () -> recSys.recommend("Inside Ouuuut", numRecommendations));
        }
    }

    private List<String> readNames(String fileOfItemNames) {
        List<String> names = new ArrayList<>();
        try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(fileOfItemNames), StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine());
            }
        }
        return names;
    }
}
