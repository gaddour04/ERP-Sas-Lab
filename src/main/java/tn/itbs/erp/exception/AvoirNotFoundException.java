package tn.itbs.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AvoirNotFoundException extends Exception{

	public AvoirNotFoundException(long id) {
		super("l'avoir ayant l'id "+id+"n'existe pas");
		
	}
	
}
