package tn.itbs.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;


import tn.itbs.erp.entites.AppUser;
import tn.itbs.erp.exception.ArticleNotFoundException;
import tn.itbs.erp.exception.UserNotFoundException;

public interface IUserService {
	List<AppUser> findAll();
	Page<AppUser> findAllPagination(int p,int s);
	public AppUser saveUser(AppUser user); 
	public AppUser findUserByUsername(String username) throws UserNotFoundException;
	Optional<AppUser> findById(int id) throws UserNotFoundException;
	public AppUser updateUser(int id,AppUser user) throws UserNotFoundException;
	public void deleteUser(int id) throws UserNotFoundException;

}
