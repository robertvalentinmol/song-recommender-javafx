package com.uji.model.distances;

import java.util.List;

public interface Distance {

    double calculateDistance(List<Double> p, List<Double> q);
}
