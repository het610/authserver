package com.example.auth2server.service;

import com.example.auth2server.model.AuthUser;
import com.example.auth2server.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<AuthUser> optUser = userRepo.findByUserName(userName);

        if(optUser.isPresent()){
            AuthUser authUser = optUser.get();

            List<SimpleGrantedAuthority> roles = authUser.getAuthRoles()
                    .stream()
                    .map(role-> new SimpleGrantedAuthority(role.getRoleName()))
                    .collect(Collectors.toList());

            return new User(authUser.getUserName(), authUser.getPassword() , roles);
        }

        throw new UsernameNotFoundException("username not exist");
    }
}
