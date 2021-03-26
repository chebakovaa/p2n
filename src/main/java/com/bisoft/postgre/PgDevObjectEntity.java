package com.bisoft.postgre;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "dev_object", schema = "public", catalog = "pitc")
public class PgDevObjectEntity extends SimpleEntity {
	static private String file = "devobject";
}
