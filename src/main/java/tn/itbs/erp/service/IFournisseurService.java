package tn.itbs.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;



import tn.itbs.erp.entites.Fournisseur;
import tn.itbs.erp.exception.FournisseurNotFoundException;


public interface IFournisseurService {
	List<Fournisseur> findAll();
	Page<Fournisseur> findAllPagination(int p,int s);
	public Fournisseur save(Fournisseur Fournisseur); 
	Optional<Fournisseur> findById(long id) throws FournisseurNotFoundException;
	public Fournisseur updateFournisseur(long id,Fournisseur Fournisseur) throws FournisseurNotFoundException;
	public void deleteFournisseur(long id) throws FournisseurNotFoundException;

}
