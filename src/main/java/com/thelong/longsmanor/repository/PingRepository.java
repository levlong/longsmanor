package com.thelong.longsmanor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.thelong.longsmanor.model.Ping;

public interface PingRepository extends MongoRepository<Ping, String>{
}
