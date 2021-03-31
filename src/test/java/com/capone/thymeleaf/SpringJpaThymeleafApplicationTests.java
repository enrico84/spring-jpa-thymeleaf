package com.capone.thymeleaf;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.capone.thymeleaf.config.ConfigProperty;

@SpringBootTest
class SpringJpaThymeleafApplicationTests {
    
    @Autowired
    private ApplicationContext context;

    @Test
    public void test_loadBean() {
	assertNotNull(context.getBean(ConfigProperty.class));
    }
	
}
