package tn.itbs.erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tn.itbs.erp.entites.Article;
import tn.itbs.erp.entites.Facture;
import tn.itbs.erp.exception.ArticleNotFoundException;
import tn.itbs.erp.service.IArticleService;


@RestController
@RequestMapping("/api/v1")
@EnableSwagger2
public class ArticleController {
	@Autowired 
	IArticleService articleService;
	
	

	//@ApiOperation(value = "Récupère tous les articles !")
	/*
	 * @GetMapping("/articles") public List<Article> getAllArticles() { return
	 * articleService.findAll();
	 * 
	 * }
	 */
	@GetMapping("/articles/pagination")
	public Page<Article> getAllArticlesPagination(@RequestParam(name = "page",defaultValue = "0") int p,
			@RequestParam(name = "size",defaultValue = "5")int s){
		return articleService.findAllPagination(p,s);
	}
	
	@GetMapping("articles")
	public List<Article> getAllArticles(){
		return articleService.findAll();
	}
	
	
	
	 @GetMapping("/articles/{code}")
	  public ResponseEntity<Article> getArticleByCode(@PathVariable(value = "code") String code)
	      throws ArticleNotFoundException {
		 Article article =
				 articleService
	            .findById(code)
	            .orElseThrow(() -> new ArticleNotFoundException(code));
	    return ResponseEntity.ok().body(article);
	  }
	
	
	@PostMapping("/articles")
    @ResponseStatus(HttpStatus.CREATED)
	//@ResponseHeader(description = "abc")
	  public ResponseEntity<HttpStatus> createArticle(@Valid @RequestBody Article article) {
		try {
			articleService.save(article);
			return new ResponseEntity<>(HttpStatus.OK); 
		}
		catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	  }

	
	
	@PutMapping("/articles/{code}")
	  public ResponseEntity<HttpStatus> updateArticle(
	      @PathVariable(value = "code") String code, @Valid @RequestBody Article articleDetails) throws ArticleNotFoundException
	    		   {
			 articleService.update(code,articleDetails );
			 return new ResponseEntity<>(HttpStatus.OK);
		
	
	   
	  }

	
	@DeleteMapping("/articles/{code}")
	  public ResponseEntity<HttpStatus>  deleteArticle(@PathVariable(value = "code") String code)  {
		try {
		articleService.deleteById(code);
		 return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
		
	  }

}
