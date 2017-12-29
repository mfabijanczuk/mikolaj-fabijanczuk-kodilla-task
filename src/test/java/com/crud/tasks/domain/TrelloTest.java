package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrelloTest {
    Trello trello = new Trello(1, 123);

    @Test
    public void getBoard() throws Exception {
        //Given
        //When
        int board = trello.getBoard();
        //Then
        Assert.assertEquals(1,board);
    }

    @Test
    public void getCard() throws Exception {
        //Given
        //When
        int card = trello.getCard();
        //Then
        Assert.assertEquals(123,card);
    }

}