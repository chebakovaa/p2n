package com.bisoft.postgre;


import javax.persistence.*;

@Entity
@Table(name = "dim_bush", schema = "public", catalog = "pitc")
public class PgBushEntity extends SimpleEntity {
	static private String file = "bush";
}
