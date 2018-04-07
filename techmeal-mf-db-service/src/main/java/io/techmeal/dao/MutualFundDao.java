package io.techmeal.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import io.techmeal.domain.MutualFund;
import io.techmeal.domain.User;

@Component
public interface MutualFundDao {

	MutualFund save(MutualFund mutualFund);

	MutualFund getById(Long mutualFundid);

	void deleteById(Long mutualFundid);

	List<MutualFund> getByUser(User user);

}
