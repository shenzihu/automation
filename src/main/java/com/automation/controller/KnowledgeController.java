package com.automation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.automation.Result;
import com.automation.entity.Knowledge;
import com.automation.entity.KnowledgeRelation;
import com.automation.repositories.KnowledgeRepository;
import com.automation.services.KnowledgeService;

@RestController()
public class KnowledgeController {

	@Autowired
	KnowledgeService service;

	@Autowired
	KnowledgeRepository repository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result add(@RequestBody KnowledgeRelation relation) {
		if (relation == null)
			return Result.error(10001l, "数据为空！");
		Knowledge main = relation.getMainKnowlege();
		if (main == null)
			return Result.error(10001l, "主知识点数据不能为空！");
		Knowledge findMain = repository.findByName(main.getName());
		if (findMain == null) {
			try {
				repository.save(main);
			} catch (Exception e) {
				return Result.error(10001l, "存储错误！");
			}
		}
		Knowledge sub = relation.getSubKnowledge();
		if (sub == null) {
			return Result.success("插入知识点成功");
		} else {
			Knowledge findSub = repository.findByName(sub.getName());
			if (findSub == null) {
				try {
					repository.save(sub);
				} catch (Exception e) {
					return Result.error(10001l, "存储错误！");
				}
			}
			if (findMain != null && findMain.getRelations().contains(relation)) {
				return Result.error(10002l, "关系已存在，请勿重复存储！");
			}
			repository.addRelations(main.getName(), sub.getName());
		}
		return Result.success("插入成功！");
	}

	@RequestMapping(value = "/search")
	public Result search(String name) {
		if (null == name) {
			return Result.success(repository.findAll());
		}
		return Result.success();
	}
}
