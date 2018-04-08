package io.techmeal.rest.V1.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatasetDataDto {

	@JsonProperty("limit")
	private Long limit;

	@JsonProperty("column_index")
	private Long columnIndex;

	@JsonProperty("column_names")
	private List<String> columnNames;

	@JsonProperty("start_date")
	private Date startDate;

	@JsonProperty("end_date")
	private Date endDate;

	@JsonProperty("data")
	private List<List<Object>> data;

}
