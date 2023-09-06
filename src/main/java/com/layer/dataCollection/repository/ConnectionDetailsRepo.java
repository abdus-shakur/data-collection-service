package com.layer.dataCollection.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.layer.dataCollection.model.ConnectionDetails;

@Repository
public interface ConnectionDetailsRepo extends MongoRepository<ConnectionDetails,String>{
	
	

}
