package com.bisoft.postgre;

import javax.persistence.*;

@Entity
@Table(name = "dim_ns", schema = "public", catalog = "pitc")
public class PgNsEntity extends SimpleEntity {
	static private String file = "ns";
}

