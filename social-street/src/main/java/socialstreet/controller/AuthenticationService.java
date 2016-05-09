package socialstreet.controller;

import org.springframework.stereotype.Service;

import socialstreet.person.LoginRequest;
import socialstreet.person.LoginResponse;

@Service
public interface AuthenticationService {
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public LoginResponse login(LoginRequest request);
	
}

