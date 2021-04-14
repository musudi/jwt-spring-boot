package com.flexylabs.jwt.springboot.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.flexylabs.jwt.springboot.services.MyUserDetailsService;
import com.flexylabs.jwt.springboot.util.JWTUtil;

@Component
public class JWTRequestFilter extends OncePerRequestFilter{

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwtToken = authorizationHeader.substring(7);
			username = jwtUtil.extractUserName(jwtToken);
		}
		 
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
			if (jwtUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usrpswAuthToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usrpswAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usrpswAuthToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}
}
