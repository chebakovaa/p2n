package com.bisoft.postgre;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Subselect("select * from neo.relation_devobject_devobject")
public class PgRelationDevobjectDevobject  extends SimpleRelation {
	
	static public String fromEntity = "devobject";
	static public String toEntity = "devobject";
	
}
