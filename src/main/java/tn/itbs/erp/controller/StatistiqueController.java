package tn.itbs.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.itbs.erp.repository.LigneFactureReoisitory;

@RestController
@RequestMapping("/api/v1")
@EnableSwagger2
public class StatistiqueController {
	@Autowired LigneFactureReoisitory ligneFactureRepository;
	
	@GetMapping("/nbrarticlevendus")
	public List<Object> getNbrArticleVendus(){
		
		return ligneFactureRepository.nbrArticleVendus();
	}
	
	@GetMapping("/sumtotalclient")
	public List<Object> getfAllFacturesClient(){
		
		return ligneFactureRepository.sumtotalclient();
	}
	@GetMapping("/sumtotalfournisseur")
	public List<Object> getfAllFacturesFournisseur(){
		
		return ligneFactureRepository.sumtotalfournisseur();
	}
}
