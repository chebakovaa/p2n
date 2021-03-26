package com.bisoft.postgre.entity;

import com.bisoft.postgre.model.SimpleEntity;

import javax.persistence.*;

@Entity
@Table(name = "dev_object", schema = "public", catalog = "pitc")
public class PgDevObjectEntity extends SimpleEntity {
	static public String file = "devobject";
}
