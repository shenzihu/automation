package com.automation.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NodeEntity
public class Knowledge {

	@GraphId
	private Long id;
	
	
	private String name;

	private String content;

	public Knowledge() {
	}

	public Knowledge(String name, String content) {
		this.name = name;
		this.content = content;
	}

	@Relationship(type = "CONNECTED")
	Set<KnowledgeRelation> relations = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<KnowledgeRelation> getRelations() {
		return relations;
	}

	public void addRelations(Collection<KnowledgeRelation> relations) {
		this.relations.addAll(relations);
	}

}
