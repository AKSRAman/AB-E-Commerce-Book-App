package com.AB.bookServer.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "users")
public class User {

	@Id
	private String id;

	private String role = "user";

	private String fullName;

	private String email;

	private String password;

	private String address;

	private double mobNumber;

	private String profilePic;

	private List<Book> booksCart;

	private Date addedOn;

	private Date updatedOn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getfullName() {
		return fullName;
	}

	public void setFullName(String userName) {
		this.fullName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(double mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public List<Book> getBooksCart() {
		return booksCart;
	}

	public void setBooksCart(List<Book> booksCart) {
		this.booksCart = booksCart;
	}
}
