package com.bisoft.postgre.entity;


import com.bisoft.postgre.model.SimpleEntity;

import javax.persistence.*;

@Entity
@Table(name = "dim_bush", schema = "public", catalog = "pitc")
public class PgBushEntity extends SimpleEntity {

	static public String file = "bush";
}
