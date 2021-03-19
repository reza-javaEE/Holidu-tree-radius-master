package com.holidu.interview.assignment.service;

import java.util.Map;

public interface TreeService {

    int loadDataFromApiAndSaveAll();

    Map<String, Integer> aggregatedSearchOfPlants(double latitude, double longitude, double radius);

}
