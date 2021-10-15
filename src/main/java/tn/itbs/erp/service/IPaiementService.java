package tn.itbs.erp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;


import tn.itbs.erp.entites.Paiement;
import tn.itbs.erp.exception.PaiementNotFoundException;
import org.springframework.web.multipart.MultipartFile;


public interface IPaiementService {
	
	Paiement save(Paiement paiement);
	List<Paiement> findAllClient();
	Page<Paiement> findAllClientPagination(int p,int s);
	List<Paiement> findAllFournisseur();
	Page<Paiement> findAllFournisseurPagination(int p,int s);
	Optional<Paiement> findById(long id) throws PaiementNotFoundException;
	void deleteById(long id) throws PaiementNotFoundException;
	public Paiement update(long id, Paiement paiementDetails) throws PaiementNotFoundException;
	public void saveImage(long id,MultipartFile file) throws PaiementNotFoundException, IOException;

}
