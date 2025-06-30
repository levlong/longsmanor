package com.thelong.longsmanor.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thelong.longsmanor.model.Ping;

@Repository
public interface PingRepository extends MongoRepository<Ping, ObjectId>{
    Ping findBy_id(ObjectId _id);
}
