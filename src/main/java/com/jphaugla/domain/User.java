package com.jphaugla.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("user")
public class User {
	private @Id String id;
	private @Indexed String firstName;
	private String middleName;
	private @Indexed String lastName;
	private String roleName;


	public String getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public User( String id, String firstName, String middleName, String lastName, String roleName) {
		this.id=id;
		this.firstName=firstName;
		this.middleName=middleName;
		this.lastName=lastName;
		this.roleName=roleName;
	}
}
