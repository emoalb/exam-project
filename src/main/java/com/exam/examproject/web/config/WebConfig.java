package com.exam.examproject.web.config;

import com.exam.examproject.web.filters.LoggingInterceptor;
import com.exam.examproject.web.filters.SetTitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableScheduling
public class WebConfig implements WebMvcConfigurer {

@Autowired
SetTitleInterceptor setTitleInterceptor;

    @Bean
    public LoggingInterceptor loggingInterceptor(){
        return new LoggingInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor());
        registry.addInterceptor(setTitleInterceptor);
    }

}
