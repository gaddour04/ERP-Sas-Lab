package tn.itbs.erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tn.itbs.erp.entites.Article;
import tn.itbs.erp.entites.Client;



import tn.itbs.erp.exception.ClientNotFoundException;

import tn.itbs.erp.service.IClientService;
import tn.itbs.erp.service.IUserService;
//@EnableSwagger2
@RestController
@RequestMapping("/api/v1")
public class ClientController {
	@Autowired
	IClientService clientService;

	@GetMapping("/clients")
	public List<Client> getAllClients() {
		return clientService.findAll();
	}
	
	@GetMapping("/clients/pagination")
	public Page<Client> getAllcCientsPagination(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		return clientService.findAllPagination(p,s);
	}



	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable(value = "id") long id)
			throws ClientNotFoundException {
		Client client =
				clientService.findById(id).orElseThrow(()-> new ClientNotFoundException(id));
		return ResponseEntity.ok().body(client);
	}




	@PostMapping("/clients")
	@ResponseStatus(HttpStatus.CREATED)
	//@ResponseHeader(description = "abc")
	public ResponseEntity<HttpStatus> createClient(@Valid @RequestBody Client client) {
		try {
			clientService.save(client);
			return new ResponseEntity<>(HttpStatus.OK); 
		}
		catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping("/clients/{id}") public ResponseEntity<HttpStatus> updateClient(

			@PathVariable(value = "id") long id, @Valid @RequestBody Client clientDetails)
					throws ClientNotFoundException { clientService.updateClient(id,clientDetails );
					return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@DeleteMapping("/clients/{id}")
	  public ResponseEntity<HttpStatus>  deleteClient(@PathVariable(value = "id") long id)  {
		try {
			clientService.deleteClient(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	  }









}
