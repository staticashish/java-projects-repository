package io.techmeal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.techmeal.domain.MutualFund;
import io.techmeal.domain.User;
import java.util.List;

@Repository
public interface MutualFundRepository extends CrudRepository<MutualFund, Long> {

	List<MutualFund> findByUser(User user);
}
