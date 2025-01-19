package com.app.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.UsersDTO;
import com.app.dto.UsersDTO.UsersDTOBuilder;
import com.app.model.Users;
import com.app.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UsersDTO saveUser(Users users) {
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			UsersDTO usersDTO = new UsersDTO();
			if(users.getUserId() != null) {
				boolean isExisted = usersRepository.existsById(users.getUserId());
				if(isExisted) {
					return usersDTO;
				}
			}
			
			Users newUsers = new Users();
			
			newUsers.setUserName(users.getUserName());
			newUsers.setRawPassword(users.getRawPassword().trim());
			newUsers.setEncPassword(passwordEncoder.encode(users.getRawPassword().trim()));
			newUsers.setFirstName(users.getFirstName());
			newUsers.setLastName(users.getLastName());
			newUsers.setAddress(users.getAddress());
			newUsers.setCreateDate(date);
			newUsers.setUpdatedDate(date);
			
			newUsers = usersRepository.save(newUsers);
			
			UsersDTOBuilder usersDTOBuilder = new UsersDTO.UsersDTOBuilder(newUsers)
					.setCreateDate(simpleDateFormat.format(newUsers.getCreateDate()))
					.setUpdatedDate(simpleDateFormat.format(newUsers.getUpdatedDate()));
			
			usersDTO = usersDTOBuilder.build();
			
			return usersDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UsersDTO updateUser(Users users) {
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			Users existedUsers = null;
			UsersDTO usersDTO = new UsersDTO();
			if(users.getUserId() != null) {
				existedUsers = usersRepository.findByUserId(users.getUserId());
				if(existedUsers != null) {
					if(users.getUserName() != null && !users.getUserName().trim().isEmpty()) {
						existedUsers.setUserName(users.getUserName());
					}

					if(users.getRawPassword() != null && !users.getRawPassword().trim().isEmpty()) {
						existedUsers.setRawPassword(users.getRawPassword().trim());
						existedUsers.setEncPassword(passwordEncoder.encode(users.getRawPassword().trim()));
					}

					if(users.getFirstName() != null && !users.getFirstName().trim().isEmpty()) {
						existedUsers.setFirstName(users.getFirstName());
					}

					if(users.getLastName() != null) {
						existedUsers.setLastName(users.getLastName());
					}

					if(users.getAddress() != null) {
						existedUsers.setAddress(users.getAddress());
					}

					existedUsers.setUpdatedDate(date);

					existedUsers = usersRepository.save(existedUsers);

					UsersDTOBuilder usersDTOBuilder = new UsersDTO.UsersDTOBuilder(existedUsers)
							.setCreateDate(simpleDateFormat.format(existedUsers.getCreateDate()))
							.setUpdatedDate(simpleDateFormat.format(existedUsers.getUpdatedDate()));

					usersDTO = usersDTOBuilder.build();
				}
			}
			
			return usersDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UsersDTO getUser(String userName) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			UsersDTO usersDTO = new UsersDTO();
			
			Users users = usersRepository.findByUserName(userName);
			if(users != null) {
				UsersDTOBuilder usersDTOBuilder = new UsersDTO.UsersDTOBuilder(users)
						.setCreateDate(simpleDateFormat.format(users.getCreateDate()))
						.setUpdatedDate(simpleDateFormat.format(users.getUpdatedDate()));
				
				usersDTO = usersDTOBuilder.build();
			}
			
			return usersDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<UsersDTO> getAllUsers() {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			List<UsersDTO> usersDTOList = new LinkedList<>();
			
			List<Users> usersList = usersRepository.findAll();
			
			usersList.forEach(u->{
				UsersDTOBuilder usersDTOBuilder = new UsersDTO.UsersDTOBuilder(u)
						.setCreateDate(simpleDateFormat.format(u.getCreateDate()))
						.setUpdatedDate(simpleDateFormat.format(u.getUpdatedDate()));
				
				usersDTOList.add(usersDTOBuilder.build());
			});
			
			return usersDTOList;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
