package com.exam.examproject.services.services.impl;

import com.exam.examproject.services.services.HashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class HashingServiceImpl implements HashingService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public HashingServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override

    public String hash(String str){
        return passwordEncoder.encode(str);
    }
}
