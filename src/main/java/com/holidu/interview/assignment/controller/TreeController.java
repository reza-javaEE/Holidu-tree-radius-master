package com.holidu.interview.assignment.controller;

import com.holidu.interview.assignment.service.TreeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/trees")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    /**
     * <p> loadDataFromApi method : load tree's information From the 3rd party API and saveAll in MongoDB.
     * </p>
     *
     * @return the information of all trees in the city of newyork. (tree species, diameter and perception of health)
     */

    @GetMapping("/loadDataFromApi")
    @ApiOperation(value = " load Data From the 3rd party API ", notes = " load Data From cityofnewyork.us ", nickname = "loadDataFromApi")
    public ResponseEntity<String> loadDataFromApiAndSaveAll() {
        treeService.loadDataFromApiAndSaveAll();

        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    /**
     * <p> findTree method : make an aggregated search of trees (plants, not binary trees) in the circle.
     * </p>
     *
     * @param latitude  : Latitude of the point in the coordinate system GCS_WGS_1984.
     * @param longitude : longitude of the point in the coordinate system GCS_WGS_1984.
     * @param radius    : A search radius in meters
     * @return the Aggregation by "common name"
     */

    @GetMapping
    @ApiOperation(value = " aggregated search ", notes = " Make an aggregated search of plants in the circle ", nickname = "findTree")
    public ResponseEntity<Map<String, Integer>> findTrees(@RequestParam double latitude,
                                                          @RequestParam double longitude,
                                                          @RequestParam double radius) {

        Map<String, Integer> treeList = treeService.aggregatedSearchOfPlants(latitude, longitude, radius);
        return new ResponseEntity<>(treeList, HttpStatus.OK);
    }

}
