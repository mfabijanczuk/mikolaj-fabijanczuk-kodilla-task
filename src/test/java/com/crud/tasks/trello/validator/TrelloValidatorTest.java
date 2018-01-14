package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {
    @Test
    public void validateCard() throws Exception {
    }


    @Autowired
    private TrelloValidator trelloValidator;


    @Test
    public void testValidateTrelloBoards() throws Exception {
        //Given
        TrelloList trelloList = new TrelloList("1","test1",true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("1","test1", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        TrelloValidator trelloValidator = new TrelloValidator();
        //When
        List<TrelloBoard> validatedTrelloBoard = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assert.assertEquals(1,validatedTrelloBoard.size());
    }

}