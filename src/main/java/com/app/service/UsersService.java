package com.app.service;

import java.util.List;

import com.app.dto.UsersDTO;
import com.app.model.Users;

public interface UsersService {
	
	public UsersDTO saveUser(Users users);
	
	public UsersDTO updateUser(Users users);
	
	public UsersDTO getUser(String userName);
	
	public List<UsersDTO> getAllUsers();
}
