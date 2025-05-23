package com.farmSystem.customservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.farmSystem.Repository.UserRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private  UserRepository userRepository;
	

//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailId(username)
            .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }
}
