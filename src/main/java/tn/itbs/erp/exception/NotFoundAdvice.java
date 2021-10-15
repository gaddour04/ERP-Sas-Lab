package tn.itbs.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@ControllerAdvice
public class NotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(ArticleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String ArticleNotFoundHandler(ArticleNotFoundException ex) {
		return ex.getMessage();
		}
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String UserNotFoundHandler(UserNotFoundException ex) {
		return ex.getMessage();
		}
	
	@ResponseBody
	@ExceptionHandler(FactureNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String FactureNotFoundHandler(FactureNotFoundException ex) {
		return ex.getMessage();
		}
	
	
	@ResponseBody
	@ExceptionHandler(PaiementNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String PaiementNotFoundHandler(PaiementNotFoundException ex) {
		return ex.getMessage();
		}
	
	
	@ResponseBody
	@ExceptionHandler(AvoirNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String AvoirNotFoundHandler(AvoirNotFoundException ex) {
		return ex.getMessage();
		}
	
	@ResponseBody
	@ExceptionHandler(ClientNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String ClientNotFoundHandler(ClientNotFoundException ex) {
		return ex.getMessage();
		}
	
	@ResponseBody
	@ExceptionHandler(FournisseurNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String FournisseurNotFoundHandler(FournisseurNotFoundException ex) {
		return ex.getMessage();
		}
	
	
	
}
