package org.danwatt.hibernate.embedded.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.MonthDay;

@Data
@Entity
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "anniversary")
	@Type(type = "org.danwatt.hibernate.embedded.types.MonthDayType")
	private MonthDay anniversary;

}
