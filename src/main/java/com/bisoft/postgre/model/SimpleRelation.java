package com.bisoft.postgre.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SimpleRelation extends CSVItem{

//	static protected String fromEntity = "";
//	static protected String toEntity = "";
	
	@Id
	@Column
	public int rn;

	public int getRn() {
		return rn;
	}
	
	public Integer from_id;
	public Integer to_id;
	
	static public String readTitle() {
		return "rn,from_id,to_id";
	}
//	static public String readFileName() {
//		return String.format("relation_%s_%s", fromEntity, toEntity);
//	}
	static public String readFileNamePrefix() {
		return "relation";
	}
	
	@Override
	public String readDataRow() {
		return String.format("%d,%d,%d", rn, from_id, to_id);
	}
}
