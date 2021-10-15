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
import tn.itbs.erp.entites.Fournisseur;
import tn.itbs.erp.exception.FournisseurNotFoundException;
import tn.itbs.erp.service.IFournisseurService;

//@EnableSwagger2
@RestController
@RequestMapping("/api/v1")
public class FournisseurController {
	@Autowired
	IFournisseurService fournisseurService;

	@GetMapping("/fournisseurs")
	public List<Fournisseur> getAllFournisseurs() {
		return fournisseurService.findAll();
	}
	
	@GetMapping("/fournisseurs/pagination")
	public Page<Fournisseur> getAllcCientsPagination(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		return fournisseurService.findAllPagination(p,s);
	}



	@GetMapping("/fournisseurs/{id}")
	public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable(value = "id") long id)
			throws FournisseurNotFoundException {
		Fournisseur Fournisseur =
				fournisseurService.findById(id).orElseThrow(()-> new FournisseurNotFoundException(id));
		return ResponseEntity.ok().body(Fournisseur);
	}




	@PostMapping("/fournisseurs")
	@ResponseStatus(HttpStatus.CREATED)
	//@ResponseHeader(description = "abc")
	public ResponseEntity<HttpStatus> createFournisseur(@Valid @RequestBody Fournisseur Fournisseur) {
		try {
			fournisseurService.save(Fournisseur);
			return new ResponseEntity<>(HttpStatus.OK); 
		}
		catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping("/fournisseurs/{id}") public ResponseEntity<HttpStatus> updateFournisseur(

			@PathVariable(value = "id") long id, @Valid @RequestBody Fournisseur FournisseurDetails)
					throws FournisseurNotFoundException { fournisseurService.updateFournisseur(id,FournisseurDetails );
					return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@DeleteMapping("/fournisseurs/{id}")
	  public ResponseEntity<HttpStatus>  deleteFournisseur(@PathVariable(value = "id") long id)  {
		try {
			fournisseurService.deleteFournisseur(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	  }









}
