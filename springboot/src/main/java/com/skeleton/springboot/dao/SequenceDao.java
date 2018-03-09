package com.skeleton.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.skeleton.springboot.entity.Sequence;
import com.skeleton.springboot.exception.ApplicationException;

@Component
public class SequenceDao {

	@Autowired
	private MongoOperations mongoOperation;

	public long getNextSequenceId(String key) throws ApplicationException {

	  //get sequence id
	  Query query = new Query(Criteria.where("_id").is(key));

	  //increase sequence id by 1
	  Update update = new Update();
	  update.inc("seq", 1);

	  //return new increased id
	  FindAndModifyOptions options = new FindAndModifyOptions();
	  options.returnNew(true);
	  options.upsert(true);
	  
	  Sequence seq =
            mongoOperation.findAndModify(query, update, options, Sequence.class);

	  //if no seq, throws Exception
          //optional, just a way to tell user when the sequence id is failed to generate.
	  if (seq == null) {
		throw new ApplicationException("Unable to get sequence id for key :"+ key, HttpStatus.NOT_FOUND.value());
	  }
	  return seq.getSeq();
	}
}
