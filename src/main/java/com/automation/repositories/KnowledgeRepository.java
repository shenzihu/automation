package com.automation.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.automation.entity.Knowledge;

@Repository
public interface KnowledgeRepository extends PagingAndSortingRepository<Knowledge, Long> {
	
	Knowledge findByName(String name);

}
