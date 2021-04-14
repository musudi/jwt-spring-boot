package com.flexylabs.jwt.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flexylabs.jwt.springboot.model.AuthenticationRequest;
import com.flexylabs.jwt.springboot.model.AuthenticationResponse;
import com.flexylabs.jwt.springboot.services.MyUserDetailsService;
import com.flexylabs.jwt.springboot.util.JWTUtil;

@Controller
public class HelloApi {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping("/hello")
	public ResponseEntity<?> hello() {
		return ResponseEntity.ok("Hello World");
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} catch (BadCredentialsException bce) {
			throw new Exception("Incorrect username or password", bce);
		}
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUserName());
		final String jwtToken = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
}
