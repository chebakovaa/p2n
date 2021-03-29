package com.bisoft.postgre.relation;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;

@Entity
@Immutable
@Subselect("select * from neo.relation_contour_oilfield")
public class PgRelationContourOilfield {
	static public String fromEntity = "contour";
	static public String toEntity = "oilfield";
	static public String file = String.format("relation_%s_%s",fromEntity,toEntity);
}
