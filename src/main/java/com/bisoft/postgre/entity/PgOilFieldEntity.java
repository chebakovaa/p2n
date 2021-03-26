package com.bisoft.postgre.entity;

import com.bisoft.postgre.model.SimpleEntity;

import javax.persistence.*;

@Entity
@Table(name = "oil_field", schema = "public", catalog = "pitc")
public class PgOilFieldEntity extends SimpleEntity {
	static public String file = "oilfield";
}

