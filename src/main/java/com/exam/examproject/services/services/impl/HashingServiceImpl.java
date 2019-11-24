package com.exam.examproject.services.services.impl;

import com.exam.examproject.services.services.HashingService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class HashingServiceImpl implements HashingService {


    @Override
    public String hash(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
