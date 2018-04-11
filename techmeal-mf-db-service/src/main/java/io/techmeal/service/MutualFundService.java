package io.techmeal.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import io.techmeal.rest.V1.dto.MutualFundDto;
import io.techmeal.rest.V1.dto.UserDto;

@Service
public interface MutualFundService {

	Set<MutualFundDto> getMutualFundsByUser(UserDto userDto);

	MutualFundDto createMutualFund(MutualFundDto mutualFundDto);

	Long delete(MutualFundDto mutualFundDto);

	MutualFundDto getMutualFundsById(Long id);

}
