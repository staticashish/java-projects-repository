package io.techmeal.service;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.techmeal.rest.V1.dto.DataRequestDto;
import io.techmeal.rest.V1.dto.DataResponseDto;
import io.techmeal.rest.V1.dto.SearchRequestDto;
import io.techmeal.rest.V1.dto.SearchResponseDto;

@Service
public class AMFIServiceImpl implements AMFIService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private DateFormat dateFormat;

	@Value("${quandle.timeseries.search.base-url:https://www.quandl.com/api/v3/datasets.json?database_code=AMFI}")
	private String quandleTSSearchUrl;

	@Value("${quandle.api.key:bxXW_aq2FdzVzW7sTxcq}")
	private String quandleApiKey;

	@Value("${quandle.timeseries.data.base-url:https://www.quandl.com/api/v3/datasets/AMFI/{schemeCode}/data.json}")
	private String quandleTSDataUrl;

	@Override
	public SearchResponseDto searchMutualFund(SearchRequestDto searchRequestDto) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("query", searchRequestDto.getQuery());
		queryParams.add("per_page", searchRequestDto.getPerPage().toString());
		queryParams.add("current_page", searchRequestDto.getCurrentPage().toString());
		queryParams.add("api_key", quandleApiKey);

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(quandleTSSearchUrl).queryParams(queryParams);
		String searchUrl = builder.build().encode().toString();

		ResponseEntity<SearchResponseDto> response = restTemplate.getForEntity(searchUrl, SearchResponseDto.class);
		return response.getBody();
	}

	@Override
	public DataResponseDto getDataForMutualFund(DataRequestDto dataRequestDto) {
		
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("start_date", dateFormat.format(dataRequestDto.getStartDate()));
		queryParams.add("end_date", dateFormat.format(dataRequestDto.getEndDate()));
		queryParams.add("limit", dataRequestDto.getLimit().toString());
		queryParams.add("column_index", dataRequestDto.getColumnIndex().toString());
		queryParams.add("api_key", quandleApiKey);
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put("schemeCode", dataRequestDto.getDatasetCode());
		
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(quandleTSDataUrl).queryParams(queryParams);
		String dataUrl = builder.buildAndExpand(params).encode().toUriString();
		
		ResponseEntity<DataResponseDto> response = restTemplate.getForEntity(dataUrl, DataResponseDto.class);
		return response.getBody();
	}

}
