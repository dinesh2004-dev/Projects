package com.farmSystem.service.impl;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmSystem.DTO.UserDTO;
import com.farmSystem.Integration.GoogleMapsIntegration;
import com.farmSystem.Repository.UserRepository;
import com.farmSystem.Util.UserMapper;
import com.farmSystem.entity.User;
import com.farmSystem.exception.UserNotFoundException;
import com.farmSystem.service.EmailService;
import com.farmSystem.service.UserService;

import jakarta.mail.MessagingException;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private  UserMapper userMapper;
	@Autowired
	private  GoogleMapsIntegration googleMapsService;
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	
	
	@Value("${google.maps.key}")
	private String mapKey;

	@Value("${user.not.found}")
	private String ownerNotFound;
	
	public int saveUser(UserDTO userDTO){
		
		GoogleMapsIntegration.Coordinates coords = googleMapsService.getCoordinatesFromAddress(userDTO.getAddress());
		
		userDTO.setLatitude(coords.latitude);
		
		userDTO.setLongitude(coords.longitude);
		
		userDTO.setAddress(coords.formattedAddress);
		
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		
		userDTO.setPassword(encodedPassword);
		
		User user = userMapper.UserDTOToUser(userDTO);
		
		userRepository.save(user);
		
		emailService.sendWelcomeMessage(user.getEmailId());
		
		return user.getId();
	}

	@Override
	public UserDTO findById(int id) throws UserNotFoundException {
		
		User user = userRepository.findById(id)
								  .orElseThrow(() -> new UserNotFoundException(String.format(ownerNotFound, id)));

		
		return userMapper.UserEntityToUserDTO(user);

	}

	@Override
	public User findUser(String emailId, String password) throws UserNotFoundException  {
		User user = userRepository.findUser(emailId, password);

		if (Objects.isNull(user)) {

			throw new UserNotFoundException(String.format(ownerNotFound,emailId));
		}
		
		return user;
	}
	
	@Override
	public boolean existsById(int id) {
		
		return userRepository.existsById(id);
	}

	@Override
	public boolean existsByEmailId(String emailId) {
		
		return userRepository.existsByEmailId(emailId);
	}
	
	@Override
	public void deleteById(int id) {
		
		userRepository.deleteById(id);
	}
	@Override
	public List<UserDTO> findAllUsers(){
		
		List<User> userList = userRepository.findAll();
		
		return userList.stream().map(user -> userMapper.UserEntityToUserDTO(user))
								.toList();
	}

	

	
	
	
}
