package com.automation.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@RelationshipEntity(type = "CONNECTED")
public class KnowledgeRelation {

	@GraphId
	private Long id;

	@StartNode
	private Knowledge mainKnowlege;

	@EndNode
	private Knowledge subKnowledge;

	public KnowledgeRelation() {
	}

	public KnowledgeRelation(Knowledge mainKnowlege, Knowledge subKnowledge) {
		this.mainKnowlege = mainKnowlege;
		this.subKnowledge = subKnowledge;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Knowledge getMainKnowlege() {
		return mainKnowlege;
	}

	public void setMainKnowlege(Knowledge mainKnowlege) {
		this.mainKnowlege = mainKnowlege;
	}

	public Knowledge getSubKnowledge() {
		return subKnowledge;
	}

	public void setSubKnowledge(Knowledge subKnowledge) {
		this.subKnowledge = subKnowledge;
	}

	@Override
	public int hashCode() {
		int result = 1;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KnowledgeRelation other = (KnowledgeRelation) obj;
		if (mainKnowlege == null) {
			if (other.mainKnowlege != null)
				return false;
		} else if (!mainKnowlege.equals(other.mainKnowlege))
			return false;
		if (subKnowledge == null) {
			if (other.subKnowledge != null)
				return false;
		} else if (!subKnowledge.equals(other.subKnowledge))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KnowledgeRelation [id=" + id + ", mainKnowlege=" + mainKnowlege.getName()
				+ ", subKnowledge=" + subKnowledge.getName() + "]";
	}

}
