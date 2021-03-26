package com.bisoft.postgre.model;

import javax.persistence.*;

@MappedSuperclass
public class SimpleEntity extends CSVItem {
	private int id;
	private String name;
//	static private String file = "simple";
	
	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		SimpleEntity that = (SimpleEntity) o;
		
		if (id != that.id) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
	
	static public String readTitle() {
		return "uid,name";
	}
	
	static public String readFileNamePrefix() {
		return "";
	}
	
	@Override
	public String readDataRow() {
		return String.format("%d,%s", getId(),getName());
	}
	
}
