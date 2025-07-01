package com.thelong.longsmanor.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thelong.longsmanor.model.Ping;

@Repository
public interface PingRepository extends MongoRepository<Ping, ObjectId>{
    Ping findBy_id(ObjectId _id);
    List<Ping> findTop10ByOrderByTimePingedDesc();
    List<Ping> findAllByOrderByTimePingedDesc(Pageable pageable);
    List<Ping> findTop10ByOrderByTimePinged();
}
