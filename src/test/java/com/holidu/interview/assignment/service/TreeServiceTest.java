package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.dao.model.Tree;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class TreeServiceTest {


    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private TreeServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldReturnAggregatedSearchOfPlants() {

        when(mongoTemplate.find(Mockito.any(Query.class), Mockito.any(Class.class))).thenReturn(sampleTrees());

        final Map<String, Integer> treesWithinCircle = service.
                aggregatedSearchOfPlants(-73.88863006, 40.81419255, 1000);

        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("pin oak", 2);
        expectedMap.put("littleleaf linden", 1);
        expectedMap.put("honeylocust", 2);


        expectedMap.forEach((k, v) -> {
            assertThat(treesWithinCircle, is(IsMapContaining.hasEntry(k, v)));
        });

    }


    private List<Tree> sampleTrees() {
        List<Tree> trees = new ArrayList<>();
        trees.add(new Tree("1", "pin oak", new double[]{-73.88917503, 40.81646683}));
        trees.add(new Tree("2", "pin oak", new double[]{-73.88917503, 40.81646683}));
        trees.add(new Tree("3", "littleleaf linden", new double[]{-73.88896542, 40.81411567}));
        trees.add(new Tree("4", "honeylocust", new double[]{-73.88863006, 40.81419255}));
        trees.add(new Tree("5", "honeylocust", new double[]{-73.88863006, 40.81419255}));

        return trees;
    }
}
