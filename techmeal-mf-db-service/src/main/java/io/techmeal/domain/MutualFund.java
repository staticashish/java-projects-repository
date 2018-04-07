package io.techmeal.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mutual_fund")
public class MutualFund {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "scheme_code")
	private String schemeCode;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "purchase_date")
	private Date purchaseDate;

	@Column(name = "purchase_amount")
	private Double purchaseAmount;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public MutualFund() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static class Builder {
		private Long id;
		private String schemeCode;
		private String name;
		private String description;
		private Date purchaseDate;
		private Double purchaseAmount;
		private User user;

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

		public Builder user(User user) {
			this.user = user;
			return this;
		}

		public MutualFund build() {
			return new MutualFund(this);
		}
	}

	private MutualFund(Builder builder) {
		this.id = builder.id;
		this.schemeCode = builder.schemeCode;
		this.name = builder.name;
		this.description = builder.description;
		this.purchaseDate = builder.purchaseDate;
		this.purchaseAmount = builder.purchaseAmount;
		this.user = builder.user;
	}

	@Override
	public String toString() {
		return "MutualFund [id=" + id + ", schemeCode=" + schemeCode + ", name=" + name + ", description=" + description
				+ ", purchaseDate=" + purchaseDate + ", purchaseAmount=" + purchaseAmount + ", user=" + user + "]";
	}

}
