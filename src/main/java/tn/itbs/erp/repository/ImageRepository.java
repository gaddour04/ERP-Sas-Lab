package tn.itbs.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.itbs.erp.entites.ImageModel;



public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	Optional<ImageModel> findByName(String name);
}
