package com.automation.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.automation.entity.Knowledge;

@Repository
public interface KnowledgeRepository extends
		PagingAndSortingRepository<Knowledge, Long> {

	Knowledge findByName(String name);

	@Query(value = "MATCH (k1),(k2) WHERE k1.name ={name1} AND k2.name = {name2} CREATE (k1)-[:CONNECTED]->(k2)")
	void addRelations(@Param("name1") String name1, @Param("name2") String name2);
}
