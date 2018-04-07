package io.techmeal.rest.V1.dto;

import java.util.Set;

public class UserDto {

	private Long id;
	private String username;
	private String password;
	private String name;
	private String email;
	private Set<RoleDto> roles;

	public UserDto() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	public static class Builder {
		private Long id;
		private String username;
		private String password;
		private String name;
		private String email;
		private Set<RoleDto> roles;
		private Set<MutualFundDto> mutualFunds;

		public Builder id(Long id) {
			this.id = id;
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

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder roles(Set<RoleDto> roles) {
			this.roles = roles;
			return this;
		}

		public UserDto build() {
			return new UserDto(this);
		}
	}

	private UserDto(Builder builder) {
		this.id = builder.id;
		this.username = builder.username;
		this.password = builder.password;
		this.name = builder.name;
		this.email = builder.email;
		this.roles = builder.roles;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
				+ email + ", roles=" + roles + "]";
	}

}
