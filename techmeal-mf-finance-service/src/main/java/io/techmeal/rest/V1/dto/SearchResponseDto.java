package io.techmeal.rest.V1.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponseDto {

	@JsonProperty("datasets")
	List<DatasetDto> datasets;

	@JsonProperty("meta")
	MetaDto meta;

	public SearchResponseDto() {
	}

	public List<DatasetDto> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DatasetDto> datasets) {
		this.datasets = datasets;
	}

	public MetaDto getMeta() {
		return meta;
	}

	public void setMeta(MetaDto meta) {
		this.meta = meta;
	}

	public static class Builder {
		private List<DatasetDto> datasets;
		private MetaDto meta;

		public Builder datasets(List<DatasetDto> datasets) {
			this.datasets = datasets;
			return this;
		}

		public Builder meta(MetaDto meta) {
			this.meta = meta;
			return this;
		}

		public SearchResponseDto build() {
			return new SearchResponseDto(this);
		}
	}

	private SearchResponseDto(Builder builder) {
		this.datasets = builder.datasets;
		this.meta = builder.meta;
	}

	@Override
	public String toString() {
		return "SearchResponseDto [datasets=" + datasets + ", meta=" + meta + "]";
	}
	
}
