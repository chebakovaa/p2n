package com.bisoft.postgre.entity;

import com.bisoft.postgre.model.SimpleEntity;

import javax.persistence.*;

@Entity
@Table(name = "well", schema = "public", catalog = "pitc")
public class PgWellEntity  extends SimpleEntity {
	static public String file = "well";
}
