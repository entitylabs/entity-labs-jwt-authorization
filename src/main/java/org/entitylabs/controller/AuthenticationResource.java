package org.entitylabs.controller;

import org.entitylabs.model.AuthenticationRequest;
import org.entitylabs.model.AuthenticationResponse;
import org.entitylabs.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/authenticate")
public class AuthenticationResource {
	
	private JWTUtil jWTUtil;
	
	private AuthenticationManager authenticationManager;
	
	
	private UserDetailsService userDetailsService;
	
	@PostMapping
	public ResponseEntity<AuthenticationResponse> authenticte(@RequestBody AuthenticationRequest request){
		
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}catch (BadCredentialsException badCredException){

			throw new BadCredentialsException("Exception in auth");	
		}

		final UserDetails userDetail=this.userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt= jWTUtil.gerenateToken(userDetail);
		return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(jwt), HttpStatus.OK);
	}

}
