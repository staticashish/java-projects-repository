package com.example.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name="USER_DETAILS")
public class UserDetails {

	@Id
	private UserKey userKey;
	
	
/*	@Column(name ="USER_NAME")
	private String userName;
*/	
	@Column(name ="COMPANY_NAME")
	private String companyName;
	
	@ElementCollection
	@JoinTable(name="USER_ADDRESS",joinColumns= {@JoinColumn(name="USER_ID_FK"),@JoinColumn(name="USER_NAME_FK")})
	@AttributeOverrides({@AttributeOverride(column=@Column(name="MY_CITY"),name="city"),
			@AttributeOverride(column=@Column(name="MY_DISTRICT"),name="district"),
			@AttributeOverride(column=@Column(name="MY_STATE"),name="state")
	})
	private List<Address> address;

	@ElementCollection
	@JoinTable(name="HOME_ADDRESS",joinColumns= {@JoinColumn(name="USER_ID_FK"),@JoinColumn(name="USER_NAME_FK")})
	@AttributeOverrides({@AttributeOverride(column=@Column(name="HOME_CITY"),name="city"),
		@AttributeOverride(column=@Column(name="HOME_DISTRICT"),name="district"),
		@AttributeOverride(column=@Column(name="HOME_STATE"),name="state")
	})
	private List<Address> homeaddress;
	
	public UserKey getUserKey() {
		return userKey;
	}

	public void setUserKey(UserKey userKey) {
		this.userKey = userKey;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "UserDetails [userKey=" + userKey + ", companyName="
				+ companyName + "]";
	}
	
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Address> getHomeaddress() {
		return homeaddress;
	}

	public void setHomeaddress(List<Address> homeaddress) {
		this.homeaddress = homeaddress;
	}

}
