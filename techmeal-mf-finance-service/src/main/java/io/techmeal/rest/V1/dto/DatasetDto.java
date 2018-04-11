package io.techmeal.rest.V1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatasetDto {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("dataset_code")
	private String datasetCode;

	@JsonProperty("database_code")
	private String databaseCode;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	public DatasetDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatasetCode() {
		return datasetCode;
	}

	public void setDatasetCode(String datasetCode) {
		this.datasetCode = datasetCode;
	}

	public String getDatabaseCode() {
		return databaseCode;
	}

	public void setDatabaseCode(String databaseCode) {
		this.databaseCode = databaseCode;
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

	public static class Builder {
		private Long id;
		private String datasetCode;
		private String databaseCode;
		private String name;
		private String description;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder datasetCode(String datasetCode) {
			this.datasetCode = datasetCode;
			return this;
		}

		public Builder databaseCode(String databaseCode) {
			this.databaseCode = databaseCode;
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

		public DatasetDto build() {
			return new DatasetDto(this);
		}
	}

	private DatasetDto(Builder builder) {
		this.id = builder.id;
		this.datasetCode = builder.datasetCode;
		this.databaseCode = builder.databaseCode;
		this.name = builder.name;
		this.description = builder.description;
	}

	@Override
	public String toString() {
		return "DatasetDto [id=" + id + ", datasetCode=" + datasetCode + ", databaseCode=" + databaseCode + ", name="
				+ name + ", description=" + description + "]";
	}
	
}
