package com.bisoft.postgre;

import javax.persistence.*;

@Entity
@Table(name = "oil_field", schema = "public", catalog = "pitc")
public class PgOilFieldEntity extends SimpleEntity {
	static private String file = "oilfield";
}

