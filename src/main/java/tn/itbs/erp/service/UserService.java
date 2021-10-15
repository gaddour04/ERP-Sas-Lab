
package tn.itbs.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

import tn.itbs.erp.entites.Article;
import tn.itbs.erp.entites.AppUser;
import tn.itbs.erp.exception.ArticleNotFoundException;
import tn.itbs.erp.exception.UserNotFoundException;
import tn.itbs.erp.repository.UserAppRepository;




@Service

@Transactional
public class UserService implements IUserService{


	@Autowired UserAppRepository userrepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Override
	public AppUser saveUser(AppUser user) {
		String password=passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		return userrepository.save(user);
	}
	@Override
	public AppUser findUserByUsername(String username) throws UserNotFoundException {
		
		return userrepository.findByUsername(username).get();
	}
	@Override
	public AppUser updateUser(int id, AppUser userdetails) throws UserNotFoundException {
		AppUser user=userrepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		user.setId(id);
		user.setEmail(userdetails.getEmail());
		user.setUsername(userdetails.getUsername());
		user.setRoles(userdetails.getRoles());
		user.setPassword(userdetails.getPassword());
		String password=passwordEncoder.encode(userdetails.getPassword());
		user.setPassword(password);
		
		return userrepository.save(user);
	}
	/*
	 * @Override public User deleteUser(long id) throws UserNotFoundException { User
	 * user=userrepository.findById(id).orElseThrow(()-> new
	 * UserNotFoundException(id));
	 * 
	 * return userrepository.delete(user); }
	 */
	@Override
	public void deleteUser(int id) throws UserNotFoundException {
		AppUser user=userrepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		
		userrepository.delete(user);
	}
	@Override
	public List<AppUser> findAll() {
		// TODO Auto-generated method stub
		return userrepository.findAll();
	}
	@Override
	public Optional<AppUser> findById(int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return userrepository.findById(id);
	}
	@Override
	public Page<AppUser> findAllPagination(int p, int s) {
		// TODO Auto-generated method stub
		return userrepository.findAll(PageRequest.of(p, s));
	}



}
