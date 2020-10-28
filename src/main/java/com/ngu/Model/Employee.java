package com.ngu.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "this field should not be empty")
	@Column(name = "username")
	private String username;
	
	@NotEmpty(message = "this field should not be empty")
	@Column(name = "password")
	private String password;
	
	@NotEmpty(message = "this field should not be empty")
	@Email(message = "please enter valid email")
	@Column(name = "email")
	private String email;
	
	
//	@Column(name="active")
//	private boolean active;
	
//	@NotNull
//	@Past
//	@DateTimeFormat(pattern="dd-mm-yyyy")
//	private Date dateOfBirth;
	
	@Column(name = "phone")
	private Long phone;
//	@Column(name="role")
//	private String role;
	
	
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
	
	
	
	
}