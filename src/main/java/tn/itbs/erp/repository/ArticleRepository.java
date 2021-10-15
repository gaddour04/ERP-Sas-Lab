package tn.itbs.erp.repository;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.erp.entites.Article;


public interface ArticleRepository extends JpaRepository<Article, String>{
	public Page<Article> findAll(Pageable pageable);

}
