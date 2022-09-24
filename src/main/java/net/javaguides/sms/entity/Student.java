package net.javaguides.sms.entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name = "first_name", nullable = false)
	public String firstName;
	
	@Column(name = "last_name")
	public String lastName;
	@Column(name= "username")
	public String username;
	@Column(name= "phone")
	public String phone;
	@Column(name = "email")
	public String email;


}
