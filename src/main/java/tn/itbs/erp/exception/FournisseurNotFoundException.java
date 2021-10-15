package tn.itbs.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FournisseurNotFoundException extends Exception {
	public FournisseurNotFoundException(long id) {
		super("le fournisseur ayant l'id :"+id+" n'existe pas");
	}

}
