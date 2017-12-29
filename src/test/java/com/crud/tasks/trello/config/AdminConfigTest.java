package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminConfigTest {

    @Autowired
    AdminConfig adminConfig;

    @Test
    public void getAdminMail() throws Exception {
        //Given
        //When
        String mail = adminConfig.getAdminMail();
        System.out.println(mail);
        //Then
        Assert.assertEquals("kodillacourse@gmail.com", mail);
    }

}