package com.example.user_api.repository.user;

import com.example.common.common.MongoTemplateCommon;
import com.example.common.utils.BenchMarkUtils;
import com.example.user_api.model.User;
import com.example.user_api.model.UserWithDepartment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import java.util.List;

@Repository
@Slf4j
public class UserRepositoryImpl implements IUserRepository {
    @Autowired
    MongoTemplateCommon mongoTemplateCommon;

    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User findById(String id) {
        return mongoTemplate.findById(id, User.class);
    }

    @Override
    public List<User> findUserByAge(int age) {
        Criteria criteria = Criteria.where("age").gte(age);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, User.class);
    }
    @Override
    public List<UserWithDepartment> findUsersWithDepartments() {
        StopWatch start = BenchMarkUtils.start();
        AggregationOperation convertDepartmentIdToObjectId = mongoTemplateCommon.buildConvertToObjectId("departmentId");
        Aggregation aggregation = Aggregation.newAggregation(
                convertDepartmentIdToObjectId,
                LookupOperation.newLookup()
                        .from("department")
                        .localField("departmentId")
                        .foreignField("_id")
                        .as("departments")
        );
        AggregationResults<UserWithDepartment> results = mongoTemplate.aggregate(aggregation, "user", UserWithDepartment.class);
        BenchMarkUtils.end(start, "find user with department");
        return results.getMappedResults();
    }

    @Override
    public List<UserWithDepartment> findUsersByNameAndAge(String name, int age) {
        AggregationOperation convertDepartmentIdToObjectId = mongoTemplateCommon.buildConvertToObjectId("departmentId");
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("name").is(name).and("age").gt(age)),
                convertDepartmentIdToObjectId,
                LookupOperation.newLookup()
                        .from("department")
                        .localField("departmentId")
                        .foreignField("_id")
                        .as("departments")

        );

        AggregationResults<UserWithDepartment> results = mongoTemplate.aggregate(aggregation, "user", UserWithDepartment.class);

        return results.getMappedResults();
    }
    @Override
    public List<UserWithDepartment> findUsersInAgeRangeWithField(int minAge, int maxAge) {
        AggregationOperation convertDepartmentIdToObjectId = mongoTemplateCommon.buildConvertToObjectId("departmentId");
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("age").gte(minAge).lte(maxAge)),
                convertDepartmentIdToObjectId,
                LookupOperation.newLookup()
                        .from("department")
                        .localField("departmentId")
                        .foreignField("_id")
                        .as("departments")
        );

        AggregationResults<UserWithDepartment> results = mongoTemplate.aggregate(aggregation, "user", UserWithDepartment.class);
        return results.getMappedResults();
    }
}
