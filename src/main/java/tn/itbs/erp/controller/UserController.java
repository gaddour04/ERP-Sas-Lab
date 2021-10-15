package tn.itbs.erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import tn.itbs.erp.entites.AppUser;
import tn.itbs.erp.entites.Article;

import tn.itbs.erp.exception.ArticleNotFoundException;
import tn.itbs.erp.exception.UserNotFoundException;
import tn.itbs.erp.repository.UserAppRepository;
import tn.itbs.erp.service.IUserService;
//@EnableSwagger2
@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	IUserService userService;

	@GetMapping("/users")
	public List<AppUser> getAllUsers() {
		return userService.findAll();

	}



	@GetMapping("/users/{id}")
	public ResponseEntity<AppUser> getUserById(@PathVariable(value = "id") int id)
			throws UserNotFoundException {
		AppUser user =
				userService.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		return ResponseEntity.ok().body(user);
	}




	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	//@ResponseHeader(description = "abc")
	public ResponseEntity<HttpStatus> createAppUser(@Valid @RequestBody AppUser user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<>(HttpStatus.OK); 
		}
		catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping("/users/{id}") public ResponseEntity<HttpStatus> updateUser(

			@PathVariable(value = "id") int id, @Valid @RequestBody AppUser userDetails)
					throws UserNotFoundException { userService.updateUser(id,userDetails );
					return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@DeleteMapping("/users/{code}")
	  public ResponseEntity<HttpStatus>  deleteArticle(@PathVariable(value = "id") int id)  {
		try {
		userService.deleteUser(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	  }









}
