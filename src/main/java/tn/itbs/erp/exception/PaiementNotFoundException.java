package tn.itbs.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PaiementNotFoundException extends Exception {
	public PaiementNotFoundException(long id) {
		super("le paiement ayant l'id :"+id+" n'existe pas");
	}

}
