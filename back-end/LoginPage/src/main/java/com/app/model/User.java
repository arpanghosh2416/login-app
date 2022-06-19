package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "login_user")
public class User {

	@Id
	@Column(name = "name", length = 30)
	private String username;

	@Column(name = "pass", nullable = false, length = 30)
	private String password;

}
