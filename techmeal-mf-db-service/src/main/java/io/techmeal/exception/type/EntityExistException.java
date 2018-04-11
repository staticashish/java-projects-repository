package io.techmeal.exception.type;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class EntityExistException extends RuntimeException {

	public EntityExistException(String exception) {
		super(exception);
	}
}
