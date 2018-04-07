package io.techmeal.rest.V1.dto;

public class RoleDto {

	private Long id;
	private String name;

	public RoleDto() {
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

	public static class Builder {
		private Long id;
		private String name;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public RoleDto build() {
			return new RoleDto(this);
		}
	}

	private RoleDto(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
	}

	@Override
	public String toString() {
		return "RoleDto [id=" + id + ", name=" + name + "]";
	}

}
