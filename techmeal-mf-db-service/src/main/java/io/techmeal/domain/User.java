package io.techmeal.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<MutualFund> mutualFund;

	public User() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<MutualFund> getMutualFund() {
		return mutualFund;
	}

	public void setMutualFund(Set<MutualFund> mutualFund) {
		this.mutualFund = mutualFund;
	}

	public static class Builder {
		private Long id;
		private String name;
		private String email;
		private String username;
		private String password;
		private Set<Role> roles;
		private Set<MutualFund> mutualFund;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder roles(Set<Role> roles) {
			this.roles = roles;
			return this;
		}

		public Builder mutualFund(Set<MutualFund> mutualFund) {
			this.mutualFund = mutualFund;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

	private User(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.email = builder.email;
		this.username = builder.username;
		this.password = builder.password;
		this.roles = builder.roles;
		this.mutualFund = builder.mutualFund;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username + ", password="
				+ password + ", roles=" + roles + ", mutualFund=" + mutualFund + "]";
	}

}
