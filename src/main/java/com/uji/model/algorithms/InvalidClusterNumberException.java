package com.uji.model.algorithms;

public class InvalidClusterNumberException extends IllegalArgumentException  {
  private int invalidClustersCount;

  public InvalidClusterNumberException(String message) {
    super(message);
  }
  public InvalidClusterNumberException(int invalidClusters,int maxPossibleClusters){
    super("El número de clusters (" + invalidClusters + ") supera el máximo posible de clusters (" + invalidClusters + ").");
    this.invalidClustersCount = invalidClusters;
  }

  public int getNumberOfClusters(){
    return invalidClustersCount;
  }
}
