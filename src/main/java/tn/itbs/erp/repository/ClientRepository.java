package tn.itbs.erp.repository;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.erp.entites.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{
	public Page<Client> findAll(Pageable pageable);

}
