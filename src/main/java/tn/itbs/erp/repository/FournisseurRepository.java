package tn.itbs.erp.repository;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.erp.entites.Fournisseur;


public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>{
	public Page<Fournisseur> findAll(Pageable pageable);

}
