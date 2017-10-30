package com.automation.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.automation.Result;
import com.automation.entity.Knowledge;
import com.automation.entity.KnowledgeRelation;
import com.automation.repositories.KnowledgeRepository;
import com.automation.services.KnowledgeService;
import com.google.common.collect.Lists;
import com.hazelcast.internal.serialization.impl.ArrayListStreamSerializer;

@RestController("/knowledge")
public class KnowledgeController {

	@Autowired
	KnowledgeService service;

	@Autowired
	KnowledgeRepository repository;

	public Result add(@RequestBody KnowledgeRelation relation) {
		if (relation == null)
			return Result.error(10001l, "数据为空！");
		Knowledge main = relation.getMainKnowlege();
		if (main == null)
			return Result.error(10001l, "主知识点数据不能为空！");
		Knowledge findMain = repository.findByName(main.getName());
		if (findMain == null) {
			repository.save(findMain);
		}
		Knowledge sub = relation.getSubKnowledge();
		if (sub == null) {
			return Result.success("插入知识点成功");
		} else {
			Knowledge findSub = repository.findByName(sub.getName());
			if (findSub == null) {
				repository.save(sub);
			} else {
				if (findMain == null) {
					main.addRelations(Lists.newArrayList(relation));
					repository.save(main);
				} else {
					if (findMain.getRelations().contains(relation)) {
						return Result.error(10002L, "数据库中已存在" + main.getName() + "和" + sub.getName() + "的关系");
					}else {
						findMain.addRelations(Lists.newArrayList(relation));
						repository.save(findMain);
					}
				}
			}
			return Result.success("插入成功！");
		}
	}

}
