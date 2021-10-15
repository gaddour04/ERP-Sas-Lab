package tn.itbs.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FactureNotFoundException extends Exception {
	public FactureNotFoundException(long id) {
		super("La facture ayant l'id :"+id+" n'existe pas");
	}

}
