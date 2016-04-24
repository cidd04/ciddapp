package com.cidd.sentiment.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cidd.sentiment.model.SocialData;

@Repository
public interface SocialDataDao extends MongoRepository<SocialData, String> {
	

}
