package com.holidu.interview.assignment.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tree")
public class Tree {

    @Id
    private String treeId;
    private String speciesName;
    private double[] location;
}
