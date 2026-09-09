package com.aviator.user_service.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.aviator.user_service.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;

import com.aviator.user_service.model.User;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user= userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+email));

            GrantedAuthority authority= new SimpleGrantedAuthority(user.getRole().toString());
            Collection<GrantedAuthority> grantedAuthorities= Collections.singletonList(authority);

            return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities
            );
    }


}
