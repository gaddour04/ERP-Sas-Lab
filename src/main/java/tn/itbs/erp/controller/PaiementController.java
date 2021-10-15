package tn.itbs.erp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.itbs.erp.entites.ImageModel;
import tn.itbs.erp.entites.Paiement;

import tn.itbs.erp.exception.PaiementNotFoundException;
import tn.itbs.erp.repository.PaiementRepository;
import tn.itbs.erp.service.IFactureService;
import tn.itbs.erp.service.IPaiementService;
import tn.itbs.erp.service.PaiementService;

@RestController
@RequestMapping("/api/v1")
public class PaiementController {
	
	@Autowired IPaiementService paiementService;
	
	@GetMapping("/paiements/client/pagination")
	public Page<Paiement> getAllPaiementsClientPagination(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		return paiementService.findAllClientPagination(p,s);
	}
	@GetMapping("/paiements/fournisseur/pagination")
	public Page<Paiement> getAllPaiementFournisseurPagination(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		return paiementService.findAllFournisseurPagination(p,s);
	}
	
	@PostMapping("/paiements")
	public Paiement save(@RequestBody Paiement p) throws PaiementNotFoundException, IOException {
		paiementService.save(p);
		return p;
	}
	@PutMapping("/paiements/{id}/image")
	public void saveImage(@PathVariable("id") long id,@RequestParam("image") MultipartFile file) throws PaiementNotFoundException, IOException {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		paiementService.saveImage(id,file);
	}
	/*@GetMapping(path = { "/paiements/{id}/get/image" })
	public Paiement getImage(@PathVariable("id") long id) throws IOException {

		 Paiement p = null;
		try {
			p = paiementService.findById(id).get();
			p.setImage(PaiementService.compressBytes(p.getImage()));
		} catch (PaiementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return p;
	}*/
	
	@DeleteMapping("/paiements/{id}")
	  public ResponseEntity<HttpStatus>  deletePaiement(@PathVariable(value = "id") long id)  {
		try {
		paiementService.deleteById(id);
		 return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	  }

}
