package tn.itbs.erp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.itbs.erp.entites.Avoir;
import tn.itbs.erp.entites.Facture;
import tn.itbs.erp.exception.AvoirNotFoundException;
import tn.itbs.erp.repository.AvoirRepository;
import tn.itbs.erp.repository.FactureRepository;
@Service
@Transactional
public class AvoirService implements IAvoirService{
	@Autowired AvoirRepository avoirRepository;
	@Autowired FactureRepository factureRepository;
	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
	int i=1;
	
	@Override
	public Avoir save(Avoir avoir) {
		avoir.setDateAvoir(new Date());
		Facture f=factureRepository.findByNumeroFacture(avoir.getFacture().getNumeroFacture());
		avoir.setFacture(f);
		Date dateAjourdhui = new Date();  
		String Ajourdhui= format.format(dateAjourdhui);
		Date dateHier = new Date(new Date().getTime() - 86400000);
		if(avoir.getClient()!=null) {
			String lastNumeroAvoirClient=avoirRepository.lastNumeroAvoirClient();
			//System.out.println(lastNumeroFacture);
			if(lastNumeroAvoirClient ==null) {
				i=1;
			}
			//System.out.println(lastNumeroFacture);
			else {
				String lastFourDigits = "";     //substring containing last 4 characters
				 
				int index= lastNumeroAvoirClient.lastIndexOf("/");
				//System.out.println(index);
				lastFourDigits = lastNumeroAvoirClient.substring( index+1);
				//System.out.println(lastFourDigits);
				i=Integer.parseInt(lastFourDigits);
				i++;
			}
			if(dateAjourdhui.getMonth()!=dateHier.getMonth()) {
				i=1;
			}
			
			String numero=String.format("%04d", i);
			String numeroAvoir="AVRC/"+Ajourdhui+"/"+numero;
			avoir.setNumeroAvoir(numeroAvoir);
		}
		else if(avoir.getFournisseur()!=null){
			String lastNumeroAvoirFournisseur=avoirRepository.lastNumeroAvoirFournisseur();
			//System.out.println(lastNumeroFacture);
			if(lastNumeroAvoirFournisseur ==null) {
				i=1;
			}
			//System.out.println(lastNumeroFacture);
			else {
				String lastFourDigits = "";     //substring containing last 4 characters
				 
				int index= lastNumeroAvoirFournisseur.lastIndexOf("/");
				//System.out.println(index);
				lastFourDigits = lastNumeroAvoirFournisseur.substring( index+1);
				//System.out.println(lastFourDigits);
				i=Integer.parseInt(lastFourDigits);
				i++;
			}
			if(dateAjourdhui.getMonth()!=dateHier.getMonth()) {
				i=1;
			}
			
			String numero=String.format("%04d", i);
			String numeroAvoir="AVRF/"+Ajourdhui+"/"+numero;
			avoir.setNumeroAvoir(numeroAvoir);
		}
		
		return avoirRepository.save(avoir);
	}

	

	@Override
	public Optional<Avoir> findById(long id) throws AvoirNotFoundException {
		// TODO Auto-generated method stub
		return avoirRepository.findById(id);
	}

	@Override
	public void deleteById(long id) throws AvoirNotFoundException {
		Avoir avoir=avoirRepository.findById(id)
				.orElseThrow(()-> new AvoirNotFoundException(id));
		
		avoirRepository.delete(avoir);
		
	}

	@Override
	public Avoir update(long id, Avoir avoirDetails) throws AvoirNotFoundException {
		Avoir avoir=avoirRepository.findById(id).orElseThrow(()->new AvoirNotFoundException(id));
		avoir.setClient(avoirDetails.getClient());
		avoir.setDateAvoir(new Date());
		avoir.setDescription(avoirDetails.getDescription());
		avoir.setFacture(avoirDetails.getFacture());
		avoir.setFournisseur(avoirDetails.getFournisseur());
		avoir.setId(id);
		avoir.setMontant(avoirDetails.getMontant());
		return avoirRepository.save(avoir);
	}

	@Override
	public List<Avoir> findAllAvoirClient() {
		// TODO Auto-generated method stub
		return avoirRepository.findAllAvoirClient();
	}

	@Override
	public Page<Avoir> findAllAvoirClientPagination(int p, int s) {
		// TODO Auto-generated method stub
		return avoirRepository.findAllAvoirClient(PageRequest.of(p, s));
	}

	@Override
	public List<Avoir> findAllAvoirFournisseur() {
		// TODO Auto-generated method stub
		return avoirRepository.findAllAvoirFournisseur();
	}

	@Override
	public Page<Avoir> findAllAvoirFournisseurPagination(int p, int s) {
		// TODO Auto-generated method stub
		 return avoirRepository.findAllAvoirFournisseur(PageRequest.of(p, s));
	}

}
