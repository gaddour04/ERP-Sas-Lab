
package tn.itbs.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

import tn.itbs.erp.entites.Fournisseur;
import tn.itbs.erp.exception.FournisseurNotFoundException;
import tn.itbs.erp.repository.FournisseurRepository;





@Service

@Transactional
public class FournisseurService implements IFournisseurService{


	@Autowired FournisseurRepository fournisseurRepository;
	
	@Override
	public Fournisseur save(Fournisseur Fournisseur) {
		
		return fournisseurRepository.save(Fournisseur);
	}
	
	@Override
	public Fournisseur updateFournisseur(long id, Fournisseur Fournisseurdetails) throws FournisseurNotFoundException {
		Fournisseur Fournisseur=fournisseurRepository.findById(id).orElseThrow(()-> new FournisseurNotFoundException(id));
		Fournisseur.setId(id);
		Fournisseur.setEmail(Fournisseurdetails.getEmail());
		Fournisseur.setMatriculeFiscale(Fournisseurdetails.getMatriculeFiscale());
		Fournisseur.setFirstName(Fournisseurdetails.getFirstName());
		Fournisseur.setLastName(Fournisseurdetails.getLastName());
		Fournisseur.setTelephone(Fournisseurdetails.getTelephone());
		Fournisseur.setAdresse(Fournisseurdetails.getAdresse());
		Fournisseur.setFactures(Fournisseurdetails.getFactures());
		Fournisseur.setAvoirs(Fournisseurdetails.getAvoirs());
		
		
		return fournisseurRepository.save(Fournisseur);
	}
	
	@Override
	public void deleteFournisseur(long id) throws FournisseurNotFoundException {
		Fournisseur Fournisseur=fournisseurRepository.findById(id).orElseThrow(()-> new FournisseurNotFoundException(id));
		
		fournisseurRepository.delete(Fournisseur);
	}
	@Override
	public List<Fournisseur> findAll() {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAll();
	}
	@Override
	public Optional<Fournisseur> findById(long id) throws FournisseurNotFoundException {
		// TODO Auto-generated method stub
		return fournisseurRepository.findById(id);
	}
	@Override
	public Page<Fournisseur> findAllPagination(int p, int s) {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAll(PageRequest.of(p, s));
	}



}
