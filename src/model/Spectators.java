package model;

import java.util.Random;

public class Spectators{
	
	//------------------------------
	// Associations
	//------------------------------
	private Spectators next;
	private Spectators prev;
	private Spectators left;
	private Spectators right;
	private Spectators parent; 
	
	//------------------------------
	// Attributes
	//------------------------------
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String country;
	private String birthdate;
	private String image;
	//------------------------------
	// Constructor 
    //------------------------------
	public Spectators(String id, String firstName, String lastName, String email, String gender, String country, String image,
			String birthdate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.country = country;
		this.image=image;
		this.birthdate = birthdate;
		next=null;
		prev=null;
	}
	
	//------------------------------
	// Getters and Setters 
	//------------------------------
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Spectators getRight() {
		return right;
	}

	public void setRight(Spectators right) {
		this.right = right;
	}

	public Spectators getParent() {
		return parent;
	}

	public void setParent(Spectators parent) {
		this.parent = parent;
	}

	public Spectators getLeft() {
		return left;
	}

	public void setLeft(Spectators left) {
		this.left = left;
	}

	public int compare(Spectators sx) {
		return id.compareTo(sx.getId());
	}

	public Spectators getNext() {
		return next;
	}

	public void setNext(Spectators next) {
		this.next = next;
	}

	public Spectators getPrev() {
		return prev;
	}

	public void setPrev(Spectators prev) {
		this.prev = prev;
	}
	
	//------------------------------
	// Methods 
	//------------------------------
	public int compareTo(String idx) {
		int comparator;
		if(id.compareTo(idx)>0) {
			comparator=1;
		}else if(id.compareTo(idx)<0) {
			comparator = -1;
		}else {
			comparator = 0;
		}
		return comparator;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
