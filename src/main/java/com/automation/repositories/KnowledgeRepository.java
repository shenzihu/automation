package com.automation.repositories;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.automation.entity.Knowledge;

@RepositoryRestResource(collectionResourceRel = "knowledge", path = "knowledge")
public interface KnowledgeRepository extends
		PagingAndSortingRepository<Knowledge, Long> {

	Knowledge findByName(@Param("name") String name);
	
	Collection<Knowledge> findByNameLike(@Param("name") String name);

	@Query(value = "MATCH (k1),(k2) WHERE k1.name ={name1} AND k2.name = {name2} CREATE (k1)-[:CONNECTED]->(k2)")
	void addRelations(@Param("name1") String name1, @Param("name2") String name2);
	
	@Query(value = "MATCH (k1:Knowledge)-[c:CONNECTED]-(k2:Knowledge)  return k1,c,k2")
	Collection<Knowledge> findAll();
}
