package io.techmeal.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.techmeal.dao.MutualFundDao;
import io.techmeal.domain.MutualFund;
import io.techmeal.domain.User;
import io.techmeal.rest.V1.dto.MutualFundDto;
import io.techmeal.rest.V1.dto.UserDto;
import io.techmeal.util.ApplicationModelMapper;

@Service
public class MutualFunServiceImpl implements MutualFundService {

	@Autowired
	private UserService userService;

	@Autowired
	private MutualFundDao mutualFundDao;

	@Autowired
	private ApplicationModelMapper applicationModelMapper;

	@Override
	public Set<MutualFundDto> getMutualFundsByUser(UserDto userDto){
		User user = applicationModelMapper.convertUserDtoToUser(userDto);
		List<MutualFund> mutualFunds = mutualFundDao.getByUser(user);
		return mutualFunds.stream()
				.map(m -> applicationModelMapper.convertMutualFundToMutualFundDto(m))
				.collect(Collectors.toSet());
	}

	@Override
	public MutualFundDto getMutualFundsById(Long id){
		MutualFund mutualFund = mutualFundDao.getById(id);
		if(mutualFund != null) {
			return applicationModelMapper.convertMutualFundToMutualFundDto(mutualFund);
		}
		return null;
	}

	@Override
	public MutualFundDto createMutualFund(MutualFundDto mutualFundDto){
		if(mutualFundDto.getUser() == null || mutualFundDto.getUser().getUsername() == null) {
			throw new EntityNotFoundException("User.username not provided as input. Request Json : [ "+mutualFundDto+" ]");
		} else {
			UserDto userDto = userService.getUser(mutualFundDto.getUser().getUsername());
			mutualFundDto.setUser(userDto);
			MutualFund mutualFund = applicationModelMapper.convertMutualFundDtoToMutualFund(mutualFundDto);
			mutualFund = mutualFundDao.save(mutualFund);
			return applicationModelMapper.convertMutualFundToMutualFundDto(mutualFund);
		}
	}

	@Override
	public Long delete(MutualFundDto mutualFundDto) {
		mutualFundDao.deleteById(mutualFundDto.getId());
	return mutualFundDto.getId();
	}
}
