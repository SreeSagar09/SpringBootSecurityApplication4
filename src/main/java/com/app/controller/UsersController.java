package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UsersDTO;
import com.app.model.Users;
import com.app.service.UsersService;

@RestController
@RequestMapping(path = "/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping(path = "/saveUser")
	public ResponseEntity<UsersDTO> saveUser(@RequestBody Users users){
		
		ResponseEntity<UsersDTO> responseEntity = null;
		try {
			UsersDTO usersDTO = usersService.saveUser(users);
			
			responseEntity = new ResponseEntity<>(usersDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@PutMapping(path = "/updateUser")
	public ResponseEntity<UsersDTO> updatedUser(@RequestBody Users users){
		
		ResponseEntity<UsersDTO> responseEntity = null;
		try {
			UsersDTO usersDTO = usersService.updateUser(users);
			
			responseEntity = new ResponseEntity<UsersDTO>(usersDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@GetMapping(path = "/getUser")
	public ResponseEntity<UsersDTO> getUser(@RequestParam String userName){
		
		ResponseEntity<UsersDTO> responseEntity = null;
		try {
			UsersDTO usersDTO = usersService.getUser(userName);
			
			responseEntity = new ResponseEntity<UsersDTO>(usersDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@GetMapping(path = "/getAllUsers")
	public ResponseEntity<List<UsersDTO>> getAllUsers(){
		
		ResponseEntity<List<UsersDTO>> responseEntity = null;
		try {
			List<UsersDTO> usersDTOList = usersService.getAllUsers();
			
			responseEntity = new ResponseEntity<List<UsersDTO>>(usersDTOList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
}
