package com.example.auth2server.repo;

import com.example.auth2server.model.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<AuthRole,Long> {

    AuthRole findByRoleNameContaining(String roleName);

}
