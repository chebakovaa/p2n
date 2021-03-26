package com.bisoft.postgre;

import javax.persistence.*;

@Entity
@Table(name = "well", schema = "public", catalog = "pitc")
public class PgWellEntity  extends SimpleEntity {
	static private String file = "well";
}
