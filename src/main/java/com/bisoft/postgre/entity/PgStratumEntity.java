package com.bisoft.postgre.entity;

import com.bisoft.postgre.model.SimpleEntity;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "stratum", schema = "neo", catalog = "pitc")
public class PgStratumEntity extends SimpleEntity {
	static public String file = "stratum";
}
