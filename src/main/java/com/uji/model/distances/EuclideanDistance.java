package com.uji.model.distances;

import java.util.List;

public class EuclideanDistance implements Distance {
    @Override
    public double calculateDistance(List<Double> p, List<Double> q) {
        double suma = 0;
        for (int i = 0; i < p.size(); i++){
            double resta = p.get(i) - q.get(i);
            suma += resta * resta;
        }
        return Math.sqrt(suma);
    }
}