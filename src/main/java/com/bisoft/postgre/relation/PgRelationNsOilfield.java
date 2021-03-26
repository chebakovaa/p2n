package com.bisoft.postgre.relation;

import com.bisoft.postgre.model.SimpleRelation;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;

@Entity
@Immutable
@Subselect("select * from neo.relation_ns_oilfield")
public class PgRelationNsOilfield extends SimpleRelation {
	
	static public String fromEntity = "ns";
	static public String toEntity = "oilfield";
	static public String file = String.format("relation_%s_%s",fromEntity,toEntity);
	
}
