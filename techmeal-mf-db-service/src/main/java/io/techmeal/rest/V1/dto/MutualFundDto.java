package io.techmeal.rest.V1.dto;

import java.util.Date;

public class MutualFundDto {

	private Long id;
	private String schemeCode;
	private String name;
	private String description;
	private Date purchaseDate;
	private Double purchaseAmount;
	private UserDto user;
	private Double nav;

	public MutualFundDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Double getNav() {
		return nav;
	}

	public void setNav(Double nav) {
		this.nav = nav;
	}

	public static class Builder {
		private Long id;
		private String schemeCode;
		private String name;
		private String description;
		private Date purchaseDate;
		private Double purchaseAmount;
		private UserDto user;
		private Double nav;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder schemeCode(String schemeCode) {
			this.schemeCode = schemeCode;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder purchaseDate(Date purchaseDate) {
			this.purchaseDate = purchaseDate;
			return this;
		}

		public Builder purchaseAmount(Double purchaseAmount) {
			this.purchaseAmount = purchaseAmount;
			return this;
		}

		public Builder user(UserDto user) {
			this.user = user;
			return this;
		}

		public Builder nav(Double nav) {
			this.nav = nav;
			return this;
		}

		public MutualFundDto build() {
			return new MutualFundDto(this);
		}
	}

	private MutualFundDto(Builder builder) {
		this.id = builder.id;
		this.schemeCode = builder.schemeCode;
		this.name = builder.name;
		this.description = builder.description;
		this.purchaseDate = builder.purchaseDate;
		this.purchaseAmount = builder.purchaseAmount;
		this.user = builder.user;
		this.nav = builder.nav;
	}

	@Override
	public String toString() {
		return "MutualFundDto [id=" + id + ", schemeCode=" + schemeCode + ", name=" + name + ", description="
				+ description + ", purchaseDate=" + purchaseDate + ", purchaseAmount=" + purchaseAmount + ", user="
				+ user + ", nav=" + nav + "]";
	}
	
}
