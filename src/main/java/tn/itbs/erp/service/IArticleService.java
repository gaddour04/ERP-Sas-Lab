package tn.itbs.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import tn.itbs.erp.entites.Article;

import tn.itbs.erp.exception.ArticleNotFoundException;


public interface IArticleService {
	
	Article save(Article article);
	List<Article> findAll();
	Page<Article> findAllPagination(int p,int s);
	Optional<Article> findById(String code) throws ArticleNotFoundException;
	void deleteById(String code) throws ArticleNotFoundException;
	public Article update(String code, Article articleDetails) throws ArticleNotFoundException;

}
