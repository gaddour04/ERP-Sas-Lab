package tn.itbs.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ArticleNotFoundException extends Exception {
	public ArticleNotFoundException(String code) {
		super("L'article ayant le code :"+code+" n'existe pas");
	}

}
