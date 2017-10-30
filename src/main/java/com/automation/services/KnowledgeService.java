package com.automation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.repositories.KnowledgeRepository;

@Service
public class KnowledgeService {
	
	@Autowired
	KnowledgeRepository repository;
}
