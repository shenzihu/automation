package com.automation.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automation.entity.Knowledge;
import com.automation.entity.KnowledgeRelation;
import com.automation.repositories.KnowledgeRepository;
import com.google.common.base.Strings;

@Service
public class KnowledgeService {

	@Autowired
	KnowledgeRepository repository;

	public Map<String, Object> graph(String name) {
		Collection<Knowledge> knowledges;
		if(StringUtils.isEmpty(name)){
			knowledges = (Collection<Knowledge>) repository.findAll();
		}else {
			knowledges = repository.findByNameLike(name);
		}
		return toD3Format(knowledges);
	}
	

private Map<String, Object> toD3Format(Collection<Knowledge> knowledges) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Knowledge> result = knowledges.iterator();
		while (result.hasNext()) {
			Knowledge knowledge = result.next();
			nodes.add(map("name", knowledge.getName(),"label", "knowledge"));
			int target = i;
			i++;
			for (KnowledgeRelation relation : knowledge.getRelations()) {
				Map<String, Object> sub = map("name", relation.getSubKnowledge().getName(), "label", "knowledge");
				int source = nodes.indexOf(sub);
				if (source == -1) {
					nodes.add(sub);
					source = i++;
				}
				rels.add(map("source", source, "target", target));
			}
		}
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}
	
	/*private Map<String, Object> toD3Format(Collection<Knowledge> knowledges) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		Iterator<Knowledge> result = knowledges.iterator();
		while (result.hasNext()) {
			Knowledge knowledge = result.next();
			nodes.add(map("name", knowledge.getName(),"label", "knowledge"));
			for (KnowledgeRelation relation : knowledge.getRelations()) {
				rels.add(map("source", relation.getMainKnowlege().getId(), "target", relation.getSubKnowledge().getId()));
			}
		}
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		result.put("relation", "CONNECTED");
		return result;
	}*/
	
}
