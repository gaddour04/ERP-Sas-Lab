package tn.itbs.erp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter  extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
			response.addHeader("Access-Control-Allow-Origin","*");
			response.addHeader("Access-Control-Allow-Methods","GET,HEAD,OPTIONS,POST,PUT,DELETE");
			response.addHeader("Access-Control-Allow-Headers",
			"Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method,Access-Control-Request-Headers, Authorization");
			response.addHeader("Access-Control-Expose-Headers","Authorization,Access-Control-Allow-Origin,Access-Control-Allow-Credentials ");
			//response.addHeader("Access-Control-Expose-Headers
			if(request.getMethod().equals("OPTIONS")) {
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else {
		String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
		 //si le token et null Ou ne commance pas par le TOKEN_PRFIX  alors ne rien faire
		if(jwt==null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);return;
		}
		Claims claims=Jwts.parser()
				.setSigningKey(SecurityConstants.SECRET)
				.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX,""))
				.getBody();
		
				String username=claims.getSubject();
				//System.out.println(username);
				ArrayList<Map<String, String>> roles=(ArrayList<Map<String,String>>)claims.get("role");
				Collection<GrantedAuthority> authorities=new ArrayList<>();
				roles.forEach(r->{
					authorities.add(new SimpleGrantedAuthority(r.get("authority")));
				});
				roles.forEach(r->{
					System.out.println(r.toString());
				});
				 //mettre dans authenticationToken le nom et authoriter d'un utilisateur authentifier dans le token 
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,null, authorities);
				//charger l'identiter dans le context de l'application
				SecurityContextHolder.getContext().setAuthentication(authenticationToken); //charge le contenu de token dans la security
				filterChain.doFilter(request, response);
			}
		
	}

}
