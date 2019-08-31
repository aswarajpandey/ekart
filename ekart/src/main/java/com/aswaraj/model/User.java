package com.aswaraj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_firstname")
	@NotEmpty(message = "*Please provide a First Name")
	private String userFirstname;

	@Column(name = "user_lastname")
	@NotEmpty(message =  "*Please provide a Last Name")
	private String userLastname;
	
	@Column(name = "user_username", nullable = false, unique = true)
	@Length(min = 5, message =  "*Your username must have atleast 5 characters")
	@NotEmpty(message = "*Please provide a Username")
	private String userUsername;

	@Column(name = "user_email", nullable = false, unique = true)
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message =  "*Please provide an Email")
	private String userEmail;

	@Column(name = "user_password", nullable = false)
	@Length(min= 5, message = "*Your password must have atleast 5 characters")
	@NotEmpty(message = "*Please provide a Password")
	private String userPassword;

	@Column(name = "user_active")
	private int userActive;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserActive() {
		return userActive;
	}

	public void setUserActive(int userActive) {
		this.userActive = userActive;
	}

}
