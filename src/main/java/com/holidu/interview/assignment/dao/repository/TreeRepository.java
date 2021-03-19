package com.holidu.interview.assignment.dao.repository;

import com.holidu.interview.assignment.dao.model.Tree;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TreeRepository extends MongoRepository<Tree, String> {
}
