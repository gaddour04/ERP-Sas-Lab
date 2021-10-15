package tn.itbs.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UserNotFoundException extends Exception {
	public UserNotFoundException(int id) {
		super("L'utilisateur ayant l'id :"+id+" n'existe pas");
	}

}
