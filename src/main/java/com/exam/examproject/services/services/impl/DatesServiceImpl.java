package com.exam.examproject.services.services.impl;

import com.exam.examproject.services.services.DatesService;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Dates;

import java.util.Date;


@Service
public class DatesServiceImpl implements DatesService {
    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
