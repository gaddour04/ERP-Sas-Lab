package tn.itbs.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.itbs.erp.entites.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long>{
	
	@Query(nativeQuery = true, value ="select * from paiement where numero_paiement  like 'PAIC%'  ")
	public Page<Paiement> findAllPaiementClient(Pageable pageable);
	
	@Query(nativeQuery = true, value ="select * from paiement where numero_paiement  like 'PAIF%'  ")
	public Page<Paiement> findAllPaiementFournisseur(Pageable pageable);
	
	@Query(nativeQuery = true, value ="select * from paiement where numero_paiement  like 'PAIC%'  ")
	public List<Paiement> findAllPaiementClient();
	
	@Query(nativeQuery = true, value ="select * from paiement where numero_paiement  like 'PAIF%'  ")
	public List<Paiement> findAllPaiementFournisseur();
	
	//@Query(nativeQuery = true, value =" select numero_paiement from paiement where 1 =( SELECT CASE WHEN EXISTS ( select * from facture where client_id is not null order by id desc limit 1 ) THEN '1' ELSE '0' END) order by id desc limit 1")
	@Query(nativeQuery = true, value ="select numero_paiement from paiement where numero_paiement  like 'PAIC%'  order by id desc limit 1")
	public String lastNumeroPaiementClient();
	
	@Query(nativeQuery = true, value =" select numero_paiement from paiement where numero_paiement  like 'PAIF%'  order by id desc limit 1")
	public String lastNumeroPaiementFournisseur();
}
