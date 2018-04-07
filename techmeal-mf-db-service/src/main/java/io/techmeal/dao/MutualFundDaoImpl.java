package io.techmeal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.techmeal.domain.MutualFund;
import io.techmeal.domain.User;
import io.techmeal.repository.MutualFundRepository;

@Component
public class MutualFundDaoImpl implements MutualFundDao {

	@Autowired
	private MutualFundRepository mutualFundRepository;
	
	@Override
	public MutualFund save(MutualFund mutualFund) {
		return mutualFundRepository.save(mutualFund);
	}
	
	@Override
	public MutualFund getById(Long mutualFundId) {
		return mutualFundRepository.findOne(mutualFundId);
	}
	
	@Override
	public void deleteById(Long mutualFundid) {
		mutualFundRepository.delete(mutualFundid);
	}
	
	@Override
	public List<MutualFund> getByUser(User user) {
		return mutualFundRepository.findByUser(user);
	}
}
