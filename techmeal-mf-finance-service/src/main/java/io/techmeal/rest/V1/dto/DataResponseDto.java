package io.techmeal.rest.V1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataResponseDto {
	
	@JsonProperty("dataset_data")
	DatasetDataDto datasetData;

	public DataResponseDto() {
	}

	public DatasetDataDto getDatasetData() {
		return datasetData;
	}

	public void setDatasetData(DatasetDataDto datasetData) {
		this.datasetData = datasetData;
	}

}
