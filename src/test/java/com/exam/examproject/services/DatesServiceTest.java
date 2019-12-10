package com.exam.examproject.services;

import com.exam.examproject.repositories.UserRepository;
import com.exam.examproject.services.services.DatesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
 class DatesServiceTest {


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    DatesService datesService;

@Test
    void getCurrentDate_shouldReturnCurrentDatae(){
    Date date = new Date();
    assertEquals(date,datesService.getCurrentDate());
}
}
