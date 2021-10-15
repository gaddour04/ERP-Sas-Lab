package tn.itbs.erp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import tn.itbs.erp.entites.AppUser;


public interface UserAppRepository extends JpaRepository<AppUser, Integer>{
	 public Optional<AppUser> findByUsername(String username);
	 public Page<AppUser> findAll(Pageable pageable);

}
