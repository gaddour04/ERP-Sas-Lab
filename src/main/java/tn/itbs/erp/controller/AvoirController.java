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
import tn.itbs.erp.entites.Avoir;
import tn.itbs.erp.exception.ArticleNotFoundException;
import tn.itbs.erp.exception.AvoirNotFoundException;
import tn.itbs.erp.service.IAvoirService;

@RestController
@RequestMapping("/api/v1")
public class AvoirController {
	@Autowired 
	IAvoirService avoirService;
	
	@GetMapping("/avoirs/clients/pagination")
	public Page<Avoir> getAllAvoirsClientPagination(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		return avoirService.findAllAvoirClientPagination(p, s);
	}
	
	@GetMapping("/avoirs/clients")
	public List<Avoir> getAllAvoirsClient(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		return avoirService.findAllAvoirClient();
	}
	@GetMapping("/avoirs/fournisseurs/pagination")
	public Page<Avoir> getAllAvoirsFournisseurPagination(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		return avoirService.findAllAvoirFournisseurPagination(p, s);
	}
	
	@GetMapping("/avoirs/fournisseurs")
	public List<Avoir> getAllAvoirsFournisseur(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		return avoirService.findAllAvoirFournisseur();
	}
	
	/*@PostMapping("/avoirs")
	public void save(@RequestBody Avoir avoir) throws AvoirNotFoundException {
		avoirService.save(avoir);
	}*/
	
	@PostMapping("/avoirs")
    @ResponseStatus(HttpStatus.CREATED)
	
	  public ResponseEntity<HttpStatus> createAvoir(@Valid @RequestBody Avoir avoir) {
		try {
			avoirService.save(avoir);
			return new ResponseEntity<>(HttpStatus.OK); 
		}
		catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	  }

	
	
	@PutMapping("/avoirs/{id}")
	  public ResponseEntity<HttpStatus> updateAvoir(
	      @PathVariable(value = "id") long id, @Valid @RequestBody Avoir avoirDetails) throws AvoirNotFoundException
	    		   {
		avoirService.update(id,avoirDetails );
			 return new ResponseEntity<>(HttpStatus.OK);
		
	
	   
	  }

	
	@DeleteMapping("/avoirs/{id}")
	  public ResponseEntity<HttpStatus>  deleteAvoir(@PathVariable(value = "id") long id)  {
		try {
			avoirService.deleteById(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	  }
	
	@GetMapping("/avoirs/{id}")
	  public ResponseEntity<Avoir> getArticleByCode(@PathVariable(value = "id") long id)
	      throws AvoirNotFoundException {
		 Avoir avoir =
				 avoirService
	            .findById(id)
	            .orElseThrow(() -> new AvoirNotFoundException(id));
	    return ResponseEntity.ok().body(avoir);
	  }
}
