package tn.itbs.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ClientNotFoundException extends Exception {
	public ClientNotFoundException(long id) {
		super("le client ayant l'id :"+id+" n'existe pas");
	}

}
