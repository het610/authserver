package com.example.auth2server.repo;

import com.example.auth2server.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<AuthUser,Long> {

    Optional<AuthUser>  findByUserName(String userName);

    Optional<AuthUser>  findByUserNameOrEmail(String userName, String email);

}
