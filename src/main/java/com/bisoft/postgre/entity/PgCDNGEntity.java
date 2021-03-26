package com.bisoft.postgre.entity;

import com.bisoft.postgre.model.SimpleEntity;

import javax.persistence.*;

@Entity
@Table(name = "dim_cdng", schema = "public", catalog = "pitc")
public class PgCDNGEntity extends SimpleEntity {
	static public String file = "cdng";
}
