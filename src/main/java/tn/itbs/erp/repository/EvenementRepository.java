package tn.itbs.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.itbs.erp.entites.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Integer>{
	public Page<Evenement> findAll(Pageable pageable);
}
