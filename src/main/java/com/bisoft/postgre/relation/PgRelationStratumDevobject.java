package com.bisoft.postgre.relation;

import com.bisoft.postgre.model.SimpleRelation;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;

@Entity
@Immutable
@Subselect("select * from neo.relation_stratum_devobject")
public class PgRelationStratumDevobject extends SimpleRelation {
	
	static public String fromEntity = "stratum";
	static public String toEntity = "devobject";
	static public String file = String.format("relation_%s_%s",fromEntity,toEntity);
}
