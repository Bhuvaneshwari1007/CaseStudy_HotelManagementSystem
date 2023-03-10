package com.roomservice.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.roomservice.feignclient.RoomAuthenticationClient;
import com.roomservice.model.AuthenticationResponse;

@Service
public class RoomAuthenticationService {
	@Autowired
	RoomAuthenticationClient roomAuthClient;
	
	public boolean isSessionValid(String token) {

		AuthenticationResponse authenticationResponse = roomAuthClient.getValidity(token);
		if (authenticationResponse == null) {
			throw new RuntimeException("Authentication reponse returned as  NULL");
		}

		String role = authenticationResponse.getRole().substring(5);
		if (role.equals("OWNER"))
			return true;
		else if (role.equals("MANAGER"))
			return true;
		else if (role.equals("RECEPTIONIST"))
			return true;
		else
			return false;

}
}
