package com.bisoft.postgre;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Subselect("select * from neo.relation_well_oilfield")
public class PgRelationWellOilfield  extends SimpleRelation {
	
	static public String fromEntity = "well";
	static public String toEntity = "oilfield";
	
}
