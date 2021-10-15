
  package tn.itbs.erp.security;
  
  import javax.sql.DataSource;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.context.annotation.Bean; import
  org.springframework.context.annotation.Configuration; import
  org.springframework.http.HttpMethod; import
  org.springframework.security.config.annotation.authentication.builders.
  AuthenticationManagerBuilder; import
  org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.
  EnableWebSecurity; import
  org.springframework.security.config.annotation.web.configuration.
  WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import
  org.springframework.security.core.userdetails.UserDetailsService; import
  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
  org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
  
  //@Configuration
  
  @EnableWebSecurity // pour activer la sécurité web public class
  public class securityConfig extends WebSecurityConfigurerAdapter{
  
  @Autowired private DataSource dataSource;
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired PasswordEncoder passwordEncoder;
  
  
  
  
  
  
  @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	  auth.userDetailsService(userDetailsService)
	  .passwordEncoder(passwordEncoder);
	  
  }
/*
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { System.out.println(passwordEncoder().encode("123"));
 * 
 * 
 * auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles(
 * "ADMIN","USER");
 * auth.inMemoryAuthentication().withUser("user").password("{noop}123").roles(
 * "USER");
 * 
 * 
 * 
 * 
 * 
 * auth.jdbcAuthentication().dataSource(dataSource).
 * usersByUsernameQuery("select username as principal,password as credentials,'1' from users where username=?"
 * )
 * .authoritiesByUsernameQuery("select username as principal ,roles as role from users where username=?"
 * ) .rolePrefix("ROLE_") .passwordEncoder(passwordEncoder());
 * 
 * 
 * 
 * auth.userDetailsService(userDetailsService)
 * .passwordEncoder(passwordEncoder());
 * 
 * 
 * }
 */
  private static final String[] AUTH_WHITELIST = {

          // -- swagger ui
          "/swagger-resources/**",
          "/swagger-ui.html",
          "/v2/api-docs",
          "/webjars/**"
  };
  @Override
  public void configure(final WebSecurity webSecurity) {
      webSecurity.ignoring().antMatchers(
    		  AUTH_WHITELIST);
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  //on  va définir les stratégies de sécurité les régles 
	  //http.formLogin();
	//http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v2/categories").
  http.csrf().disable()
			
			  .sessionManagement() .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			  .and()
			 
			
			  .authorizeRequests() //.antMatchers("/api/v2/**").permitAll()
			  .antMatchers("/login","/swagger-ui.html").permitAll()
			  .antMatchers(HttpMethod.POST,"/api/v1/**").hasAuthority("ADMIN")
			  .antMatchers(HttpMethod.PUT,"/api/v1/**").hasAuthority("ADMIN")
			  .antMatchers(HttpMethod.DELETE,"/api/v1/**").hasAuthority("ADMIN")
			  .anyRequest().authenticated()
			  
			  //.and() .httpBasic() 
			  
			  .and()
			 
	  	.addFilter(new JWTAuthenticationFilter(authenticationManager()))
	  	.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
  		
	  	
  	;

  

  
  /*
  http.authorizeRequests().antMatchers("/**").hasAnyRole("CLIENT","ADMIN");
  http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();
  
  http.authorizeRequests().antMatchers(
  HttpMethod.POST,"/api/v2/categories").hasRole("ADMIN");
  http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();
  //http.authorizeRequests().antMatchers("/*").hasRole("USER");
  http.authorizeRequests().anyRequest().authenticated();
  http.exceptionHandling().accessDeniedPage("/403"); }*/
  
  }
  }
 