package tn.itbs.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import tn.itbs.erp.entites.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long> {
	//      **********************        Client	******************
	public Page<Facture> findAllByClientNotNull(Pageable pageable);
	public List<Facture> findAllByClientNotNull();
	//  **********************        Fournisseur	******************
	public Page<Facture> findAllByFournisseurNotNull(Pageable pageable);
	public List<Facture> findAllByFournisseurNotNull();
	public Facture findByNumeroFacture(String numeroFacture);
	@Query("select x.facture from Paiement x where x.id =:x ") 
	public Facture chercher(@Param("x") long idPaiement);
	
	@Query(nativeQuery = true, value ="select numero_facture from facture  where client_id is not null order by id desc limit 1 ")
	public String lastNumeroFactureClient();
	
	@Query(nativeQuery = true, value ="select numero_facture from facture  where fournisseur_id is not null order by id desc limit 1 ")
	public String lastNumeroFactureFournisseur();
	
	/*
	 *  findFirstByOrderByIdAsc();
		 findTopByOrderByIdAsc();
*/
	 

}
