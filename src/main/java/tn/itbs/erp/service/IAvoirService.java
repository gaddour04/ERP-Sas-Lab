package tn.itbs.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import tn.itbs.erp.entites.Avoir;

import tn.itbs.erp.exception.AvoirNotFoundException;


public interface IAvoirService {
	
	Avoir save(Avoir avoir);
	List<Avoir> findAllAvoirClient();
	Page<Avoir> findAllAvoirClientPagination(int p,int s);
	List<Avoir> findAllAvoirFournisseur();
	Page<Avoir> findAllAvoirFournisseurPagination(int p,int s);
	Optional<Avoir> findById(long id) throws AvoirNotFoundException;
	void deleteById(long id) throws AvoirNotFoundException;
	public Avoir update(long id, Avoir avoirDetails) throws AvoirNotFoundException;

}
