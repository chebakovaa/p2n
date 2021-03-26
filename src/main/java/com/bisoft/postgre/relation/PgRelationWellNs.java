package com.bisoft.postgre.relation;

import com.bisoft.postgre.model.SimpleRelation;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;

@Entity
@Immutable
@Subselect("select * from neo.relation_well_ns")
public class PgRelationWellNs  extends SimpleRelation {
	
	static public String fromEntity = "well";
	static public String toEntity = "ns";
	static public String file = String.format("relation_%s_%s",fromEntity,toEntity);
	
}
