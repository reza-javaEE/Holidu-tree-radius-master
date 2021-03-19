package com.holidu.interview.assignment.service;

import com.holidu.interview.assignment.dao.repository.TreeRepository;
import com.holidu.interview.assignment.restclient.TreeRestClient;
import com.holidu.interview.assignment.dto.TreeResponse;
import com.holidu.interview.assignment.dao.model.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TreeServiceImpl implements TreeService {

    private TreeRepository treeRepository;

    private TreeRestClient treeRestClient;

    private MongoTemplate mongoTemplate;

    @Value("${tree.rest.client.rows}")
    private int rows;

    @Value("${earth.radius}")
    private double earthRadius;

    public TreeServiceImpl(TreeRepository treeRepository, TreeRestClient treeRestClient,
                           MongoTemplate mongoTemplate) {
        this.treeRepository = treeRepository;
        this.treeRestClient = treeRestClient;
        this.mongoTemplate = mongoTemplate;
    }

    Logger logger = LoggerFactory.getLogger(TreeServiceImpl.class);


    @Override
    public int loadDataFromApiAndSaveAll() {

        int offset = 0;
        boolean morePage = true;

        logger.info(" loadDataFromApi started ....");

        while (morePage) {
            List<TreeResponse> foundTrees = treeRestClient.findAllTreesPagination(rows, offset);

            List<Tree> mappedTrees = mapResponsToTree(foundTrees);

            treeRepository.saveAll(mappedTrees);

            offset += rows;
            logger.info(" --- current offset is : " + offset);

            morePage = foundTrees.size() >= rows;
        }

        logger.info(" loadDataFromApi Finished ....");

        return mongoTemplate.findAll(Tree.class).size();
    }

    @Override
    public Map<String, Integer> aggregatedSearchOfPlants(double latitude, double longitude, double radius) {

        List<Tree> trees = searchPlantsInCircle(latitude, longitude, radius);

        Map<String, List<Tree>> map = trees.stream()
                .filter(tree -> tree.getSpeciesName() != null)
                .collect(Collectors.groupingBy(Tree::getSpeciesName));

        Map<String, Integer> mapResult = new HashMap<>();
        map.forEach((k, value) -> {
            mapResult.put(k, value.size());
        });

        return mapResult;
    }

    private List<Tree> searchPlantsInCircle(double fieldX, double fieldY, double radius) {

        Circle circle = new Circle(fieldY, fieldX, (radius) / earthRadius);

        return mongoTemplate.find(new Query(Criteria.where("location").withinSphere(circle)), Tree.class);
    }

    public List<Tree> mapResponsToTree(List<TreeResponse> treeList) {
        return treeList.stream().map(this::convert).collect(Collectors.toList());
    }

    public Tree convert(TreeResponse treeResponse) {

        return new Tree(treeResponse.getTree_id(),
                treeResponse.getSpc_common(),
                new double[]{treeResponse.getLongitude(), treeResponse.getLatitude()});
    }

}
