package com.example.auth2server.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name="Auth_user")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private String email;

    private String mobile;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="user_role",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id")
    ,inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private List<AuthRole> authRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<AuthRole> getAuthRoles() {
        return authRoles;
    }

    public void setAuthRoles(List<AuthRole> authRoles) {
        this.authRoles = authRoles;
    }
}
