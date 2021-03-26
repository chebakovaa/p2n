package com.bisoft.postgre.entity;

import com.bisoft.postgre.model.SimpleEntity;

import javax.persistence.*;

@Entity
@Table(name = "dim_ns", schema = "public", catalog = "pitc")
public class PgNsEntity extends SimpleEntity {
	static public String file = "ns";
}

