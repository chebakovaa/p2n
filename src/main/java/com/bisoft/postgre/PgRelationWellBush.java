package com.bisoft.postgre;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Immutable
@Subselect("select * from neo.relation_well_bush")
public class PgRelationWellBush extends SimpleRelation {
	
	static public String fromEntity = "well";
	static public String toEntity = "bush";
	
}
