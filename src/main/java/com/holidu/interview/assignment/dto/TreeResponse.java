package com.holidu.interview.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeResponse {

    /*
     * Unique identification number for each tree point.
     */
    private String tree_id;
    /*
     * Common name for species, e.g. "red maple"
     */
    private String spc_common;
    /*
     * Latitude of point, in decimal degrees
     */
    private double latitude;
    /*
     * Longitude of point, in decimal degrees
     */
    private double longitude;

}
