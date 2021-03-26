package com.bisoft.postgre.relation;

import com.bisoft.postgre.model.SimpleRelation;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Immutable
@Subselect("select * from neo.relation_well_devobject")
public class PgRelationWellDevobject extends SimpleRelation {
	
	static public String fromEntity = "well";
	static public String toEntity = "devobject";
	static public String file = String.format("relation_%s_%s",fromEntity,toEntity);
	
}
