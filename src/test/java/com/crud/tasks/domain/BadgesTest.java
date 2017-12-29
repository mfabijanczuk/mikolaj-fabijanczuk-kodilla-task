package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BadgesTest {

    Trello trello = new Trello(1, 1);
    AttachmentsByTypes attachmentsByType = new AttachmentsByTypes(trello);
    Badges badges = new Badges(1,attachmentsByType);

    @Test
    public void getVotes() throws Exception {
        //Given
        //When
        int votes = badges.getVotes();
        //Then
        Assert.assertEquals(1,votes );
    }

    @Test
    public void getAttachmentsByType() throws Exception {
        //Given
        //When
        //Then
        Assert.assertEquals(1,attachmentsByType.getTrello().getBoard());
        Assert.assertEquals(1,attachmentsByType.getTrello().getCard());
    }

}