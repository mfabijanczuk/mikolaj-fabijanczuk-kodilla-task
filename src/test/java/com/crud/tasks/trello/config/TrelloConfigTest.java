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
public class TrelloConfigTest {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void getTrelloApiEndpoint() throws Exception {
        //Given
        //When
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        System.out.println(trelloApiEndpoint);
        //Then
        Assert.assertEquals("https://api.trello.com/1", trelloApiEndpoint);

    }

    @Test
    public void getTrelloAppKey() throws Exception {
        //Given
        //When
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        System.out.println(trelloAppKey);
        //Then
        Assert.assertEquals("815a869a2b1dc82f259ecc1d14ba1eb6", trelloAppKey);
    }

    @Test
    public void getTrelloToken() throws Exception {
        //Given
        //When
        String trelloToken = trelloConfig.getTrelloToken();
        System.out.println(trelloToken);
        //Then
        Assert.assertEquals("005df5858fd08b8487c948e62513512fc30029b6b7c12413516b9d4c9a03b0f7", trelloToken);
    }

    @Test
    public void getUsername() throws Exception {
        //Given
        //When
        String username = trelloConfig.getUsername();
        System.out.println(username);
        //Then
        Assert.assertEquals("mfabijanczuk", username);
    }

}