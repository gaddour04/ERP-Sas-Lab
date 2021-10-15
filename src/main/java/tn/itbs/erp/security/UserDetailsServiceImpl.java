
package tn.itbs.erp.security;

import java.util.ArrayList; import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User; import
org.springframework.security.core.userdetails.UserDetails; import
org.springframework.security.core.userdetails.UserDetailsService; import
org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.itbs.erp.entites.AppUser;
import tn.itbs.erp.exception.UserNotFoundException;
import tn.itbs.erp.service.IUserService;






@Service 
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired IUserService accountService;
	@Autowired PasswordEncoder passwordEncoder;

	@Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		
		  AppUser user;
		try {
			user = accountService.findUserByUsername(username);
			 if(user==null)throw new UsernameNotFoundException(username);
			  Collection<GrantedAuthority> authorities=new ArrayList<>();
			  user.getRoles().forEach(r->{ authorities.add(new
			  SimpleGrantedAuthority(r.getRole())); });
			  return new User(user.getUsername(),user.getPassword(),authorities);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		return null;
		//return new User("gaddour",passwordEncoder.encode("123"),AuthorityUtils.NO_AUTHORITIES);
		}

}
