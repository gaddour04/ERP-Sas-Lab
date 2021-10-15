package tn.itbs.erp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.itbs.erp.entites.Evenement;
import tn.itbs.erp.entites.Facture;
import tn.itbs.erp.repository.EvenementRepository;
import tn.itbs.erp.repository.FactureRepository;
@Service
@Transactional
public class EvenementService implements IEvenementService{
	@Autowired
	EvenementRepository evenementRepository;
	@Autowired 
	FactureRepository factureRep;
	
	@Autowired
	IEmailService emailService;

	@Autowired
	TaskScheduler taskScheduler;

	ScheduledFuture<?> scheduledFuture;

	Logger log=LoggerFactory.getLogger(EvenementService.class);
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  


	private Runnable evenement() {
		return () ->{
			log.info("envoie mail à samir");
		};
	}
	
	private Runnable sendMail() {
        return () ->{
        	 Map<String, Object> model = new HashMap<>();
    		 model.put("Name", "ERP");
    		 model.put("location", "Nabeul,Tunisie");
    	    emailService.sendEmail(model);
        };
    }
	@Override
	public Evenement save(Evenement evenement) {
		Date date = new Date();  
    	evenement.setDateStart(date);
		String dateStart= format.format(evenement.getDateStop());
		Date d2 = null; 

		try {
			d2 = format.parse(dateStart);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		scheduledFuture =  taskScheduler.schedule(evenement(),d2);
		/*
		 * evenement.getFactures().forEach(f->{ Facture
		 * facture=factureRep.findById(f.getId()).get();
		 * facture.setEvenement(evenement); factureRep.save(facture); });
		 */
		Facture facture=factureRep.findById(evenement.getFactures().getId()).get();
		facture.setEvenement(evenement);
		//maghir save khatar bel cascade.all isayvi wa7dou factureRep.save(facture);
		
		return evenementRepository.save(evenement);
	}

	@Override
	public Page<Evenement> findAllPagination(int p, int s) {
		
		return evenementRepository.findAll(PageRequest.of(p, s));
	}

}
