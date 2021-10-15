package tn.itbs.erp.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import javax.websocket.server.PathParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.itbs.erp.entites.Article;
import tn.itbs.erp.entites.Evenement;
import tn.itbs.erp.entites.Facture;
import tn.itbs.erp.repository.EvenementRepository;
import tn.itbs.erp.service.IEmailService;
import tn.itbs.erp.service.IEvenementService;

@RestController
@RequestMapping("/api/v1")
public class EvenementController {
	@Autowired
	IEvenementService evenementService;
	@Autowired
	EvenementRepository evenementRepository;
	@Autowired 
	IEmailService emailService;

	@PostMapping("/start")
	ResponseEntity<Void> start(@RequestBody Evenement evenement) throws Exception {
		evenementService.save(evenement);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}






	@GetMapping("/mail")
	public void sendmail() {
		Map<String, Object> model = new HashMap<>();
		model.put("Name", "Beep Beep Services");
		model.put("location", "Nabeul,Tunisie");
		emailService.sendEmail(model);

	}
	@GetMapping("/evenements")
	public List<Evenement> getAllEvenements(){
		return evenementRepository.findAll();
	}

}
