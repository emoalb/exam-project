package com.exam.examproject.services.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public CustomUser(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password,authorities);
        setId(id);
    }
}
