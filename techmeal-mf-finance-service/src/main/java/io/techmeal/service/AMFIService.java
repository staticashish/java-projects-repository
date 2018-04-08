package io.techmeal.service;

import io.techmeal.rest.V1.dto.DataRequestDto;
import io.techmeal.rest.V1.dto.DataResponseDto;
import io.techmeal.rest.V1.dto.SearchRequestDto;
import io.techmeal.rest.V1.dto.SearchResponseDto;

public interface AMFIService {

	SearchResponseDto searchMutualFund(SearchRequestDto searchRequestDto);

	DataResponseDto getDataForMutualFund(DataRequestDto dataRequestDto);

}