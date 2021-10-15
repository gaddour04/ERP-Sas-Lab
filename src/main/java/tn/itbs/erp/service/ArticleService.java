package tn.itbs.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import tn.itbs.erp.entites.Article;
import tn.itbs.erp.exception.ArticleNotFoundException;
import tn.itbs.erp.repository.ArticleRepository;

@Service
public class ArticleService implements IArticleService{
	@Autowired
	ArticleRepository articleRepository;

	@Override
	public Article save(Article article) {
		// TODO Auto-generated method stub
		return articleRepository.save(article);
	}

	@Override
	public List<Article> findAll() {
		// TODO Auto-generated method stub
		return articleRepository.findAll();
	}

	@Override
	public Optional<Article> findById(String code) throws ArticleNotFoundException {
		// TODO Auto-generated method stub
		return articleRepository.findById(code);
	}

	@Override
	public void deleteById(String code) throws ArticleNotFoundException {
		Article article=articleRepository.findById(code)
				.orElseThrow(()-> new ArticleNotFoundException(code));
		
		articleRepository.delete(article);
		
	}

	@Override
	public Article update(String code, Article articleDetails) throws ArticleNotFoundException {
		Article article=articleRepository.findById(code)
				.orElseThrow(()-> new ArticleNotFoundException(code));
		article.setCode(code);
		article.setDescription(articleDetails.getDescription());
		article.setPrix(articleDetails.getPrix());
		article.setQte(articleDetails.getQte());
		
		return articleRepository.save(article);
	}

	@Override
	public Page<Article> findAllPagination(int p, int s) {
		// TODO Auto-generated method stub
		return articleRepository.findAll(PageRequest.of(p, s));
	}

}
