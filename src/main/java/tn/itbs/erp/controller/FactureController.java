package tn.itbs.erp.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.itbs.erp.entites.AppUser;
import tn.itbs.erp.entites.Article;
import tn.itbs.erp.entites.Facture;
import tn.itbs.erp.entites.LigneFacture;
import tn.itbs.erp.exception.ArticleNotFoundException;
import tn.itbs.erp.exception.FactureNotFoundException;
import tn.itbs.erp.exception.PaiementNotFoundException;
import tn.itbs.erp.exception.UserNotFoundException;
import tn.itbs.erp.repository.ArticleRepository;
import tn.itbs.erp.repository.FactureRepository;
import tn.itbs.erp.repository.LigneFactureReoisitory;
import tn.itbs.erp.service.IFactureService;


@RestController
@RequestMapping("/api/v1")

public class FactureController {
	
	double sum = 0;
	double tva=0;

	@Autowired 
	IFactureService factureService;
	@Autowired
	LigneFactureReoisitory ligneRepository;
	@Autowired
	FactureRepository factureRepository;
	
	 Logger log = LoggerFactory.getLogger(FactureController.class);

	
	@PostMapping("/factures/client")
	public void createFactureClient(@RequestBody Facture facture) throws FactureNotFoundException {
		factureService.saveFactureClient(facture);
		log.info("successful ");	
	}
	@PostMapping("/factures/fournisseur")
	public Facture createFactureFournisseur(@RequestBody Facture facture) throws FactureNotFoundException {
		factureService.saveFactureFournisseur(facture);
		log.info("successful ");
		return facture;
	}
	
	@GetMapping("/factures_client_pagination")
	public Page<Facture> getAllFacturesClientPagination(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		
		return factureService.findAllFactureClientPagination(p,s);
	}
	@GetMapping("/factures_client")
	public List<Facture> getAllFacturesClient(){
		return factureService.findAllFactureClient();
	}
	
	@GetMapping("/factures_fournisseur_pagination")
	public Page<Facture> getAllFacturesFournisseurPagination(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		
		return factureService.findAllFactureFournisseurPagination(p,s);
	}
	@GetMapping("/factures_fournisseur")
	public List<Facture> getAllFacturesFournisseur(){
		return factureService.findAllFactureFournisseur();
	}
	
	@GetMapping("/factures/{id}")
	public ResponseEntity<Facture> getFactureById(@PathVariable(value = "id") long id) throws FactureNotFoundException{
		Facture facture=factureService.findById(id).orElseThrow(()->new FactureNotFoundException(id));
		return ResponseEntity.ok().body(facture);
	}
	@DeleteMapping("/factures/{id}")
	  public ResponseEntity<HttpStatus>  deleteArticle(@PathVariable(value = "id") long id)  {
		try {
		factureService.deleteById(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	  }
	

	@PutMapping("/factures/{id}")
	  public ResponseEntity<HttpStatus> updateArticle(
	      @PathVariable(value = "id") long id, @Valid @RequestBody Facture factureDetails) throws FactureNotFoundException
	    		   {
		 factureService.update(id,factureDetails );
			 return new ResponseEntity<>(HttpStatus.OK);
		
	
	   
	  }
	@GetMapping("/facture/paiement/{id}")
	public Facture facture(@PathVariable(value = "id")long id) {
		
		return factureRepository.chercher(id);
	}
	
	@PutMapping("/factures/{id}/image")
	public void saveImage(@PathVariable("id") long id,@RequestParam("image") MultipartFile file) throws  IOException, FactureNotFoundException {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		factureService.saveImage(id,file);
	}
	
	}
	
	
	


