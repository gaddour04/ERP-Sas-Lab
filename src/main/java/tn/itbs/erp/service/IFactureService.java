package tn.itbs.erp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import tn.itbs.erp.entites.Facture;
import tn.itbs.erp.exception.FactureNotFoundException;


public interface IFactureService {
	Facture saveFactureClient(Facture facture);
	Facture saveFactureFournisseur(Facture facture);
	List<Facture> findAllFactureClient();
	List<Facture> findAllFactureFournisseur();
	Page<Facture> findAllFactureFournisseurPagination(int p,int s);
	Page<Facture> findAllFactureClientPagination(int p,int s);
	Optional<Facture> findById(long id) throws FactureNotFoundException;
	void deleteById(long id) throws FactureNotFoundException;
	public Facture update(long id, Facture factureDetails) throws FactureNotFoundException;
	public void saveImage(long id,MultipartFile file) throws FactureNotFoundException, IOException;

}
