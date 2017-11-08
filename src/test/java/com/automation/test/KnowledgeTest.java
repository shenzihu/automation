package com.automation.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.automation.entity.Knowledge;
import com.automation.entity.KnowledgeRelation;
import com.automation.repositories.KnowledgeRepository;
import com.automation.services.KnowledgeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KnowledgeTest {

	@Autowired
	KnowledgeRepository repository;

	@Autowired
	KnowledgeService service;

	@Test
	public void testInsert() {
		Knowledge mainKnowledge = new Knowledge("主知识点", "主内容");
		repository.save(mainKnowledge);
		Set<KnowledgeRelation> relations = new HashSet<KnowledgeRelation>();

		// mainKnowledge.addRelations(relations);
		Knowledge main2 = new Knowledge("主知识点2", "主内容2");
		repository.save(main2);
		relations.add(new KnowledgeRelation(mainKnowledge, main2));

		repository.save(mainKnowledge);

		Knowledge main3 = new Knowledge("主知识点3", "主内容3");
		repository.save(main3);

		relations.add(new KnowledgeRelation(mainKnowledge, main3));
		Knowledge main4 = new Knowledge("主知识点4", "主内容4");
		repository.save(main4);
		repository.save(mainKnowledge);

	}

	@Test
	public void test2() {
		System.err.println(repository.findAll());
		Object k2 = repository.findByName("主知识点2");
		Object k4 = repository.findByName("主知识点3");
		System.err.println(k2);
		System.err.println(k4);
		repository.addRelations("主知识点2", "主知识点3");

	}
}
