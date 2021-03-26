package com.bisoft.postgre;

import javax.persistence.*;

@Entity
@Table(name = "dim_cdng", schema = "public", catalog = "pitc")
public class PgCDNGEntity extends SimpleEntity {
	static private String file = "cdng";
}
