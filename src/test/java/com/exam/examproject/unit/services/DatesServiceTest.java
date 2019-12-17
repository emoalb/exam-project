package com.exam.examproject.unit.services;


import com.exam.examproject.services.services.DatesService;
import com.exam.examproject.services.services.impl.DatesServiceImpl;
import org.junit.Before;

import org.junit.jupiter.api.Test;



import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;


 class DatesServiceTest {


    @Before
    void setup() {

    }



@Test
    void getCurrentDate_shouldReturnCurrentDate(){
    Date date = new Date();
    DatesService datesService = new DatesServiceImpl();
    assertEquals(date,datesService.getCurrentDate());
}
}
