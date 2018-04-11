package io.techmeal.rest.V1.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataRequestDto implements Serializable {

	private static final long serialVersionUID = -4748511131109439011L;

	@JsonProperty("dataset_code")
	private String datasetCode;

	@JsonProperty("limit")
	private Long limit;

	@JsonProperty("start_date")
	private Date startDate;

	@JsonProperty("end_date")
	private Date endDate;

	@JsonProperty("column_index")
	private Long columnIndex = 1L;

	public DataRequestDto() {
	}

	public String getDatasetCode() {
		return datasetCode;
	}

	public void setDatasetCode(String datasetCode) {
		this.datasetCode = datasetCode;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(Long columnIndex) {
		this.columnIndex = columnIndex;
	}

	public static class Builder {
		private String datasetCode;
		private Long limit;
		private Date startDate;
		private Date endDate;
		private Long columnIndex;

		public Builder datasetCode(String datasetCode) {
			this.datasetCode = datasetCode;
			return this;
		}

		public Builder limit(Long limit) {
			this.limit = limit;
			return this;
		}

		public Builder startDate(Date startDate) {
			this.startDate = startDate;
			return this;
		}

		public Builder endDate(Date endDate) {
			this.endDate = endDate;
			return this;
		}

		public Builder columnIndex(Long columnIndex) {
			this.columnIndex = columnIndex;
			return this;
		}

		public DataRequestDto build() {
			return new DataRequestDto(this);
		}
	}

	private DataRequestDto(Builder builder) {
		this.datasetCode = builder.datasetCode;
		this.limit = builder.limit;
		this.startDate = builder.startDate;
		this.endDate = builder.endDate;
		this.columnIndex = builder.columnIndex;
	}

	@Override
	public String toString() {
		return "DataRequestDto [datasetCode=" + datasetCode + ", limit=" + limit + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", columnIndex=" + columnIndex + "]";
	}

}
