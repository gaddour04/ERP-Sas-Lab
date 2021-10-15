package tn.itbs.erp.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import tn.itbs.erp.entites.Avoir;


public interface AvoirRepository extends JpaRepository<Avoir, Long>{
	public Page<Avoir> findAll(Pageable pageable);
	
	@Query(nativeQuery = true, value ="select * from avoir where numero_avoir  like 'AVRC%'  ")
	public Page<Avoir> findAllAvoirClient(Pageable pageable);
	
	@Query(nativeQuery = true, value ="select * from avoir where numero_avoir  like 'AVRF%'  ")
	public Page<Avoir> findAllAvoirFournisseur(Pageable pageable);
	
	@Query(nativeQuery = true, value ="select * from avoir where numero_avoir  like 'AVRC%'  ")
	public List<Avoir> findAllAvoirClient();
	
	@Query(nativeQuery = true, value ="select * from avoir where numero_avoir  like 'AVRF%'  ")
	public List<Avoir> findAllAvoirFournisseur();
	
	@Query(nativeQuery = true, value ="select numero_avoir from avoir where numero_avoir  like 'AVRC%' order by id desc limit 1  ")
	public String lastNumeroAvoirClient();
	
	@Query(nativeQuery = true, value ="select numero_avoir from avoir where numero_avoir  like 'AVRF%' order by id desc limit 1  ")
	public String lastNumeroAvoirFournisseur();

}
