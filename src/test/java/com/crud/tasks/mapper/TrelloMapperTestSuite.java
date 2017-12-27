package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("123", "Test", trelloListDtos);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoardTest = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(1, trelloBoardTest.size());
        assertEquals("123",trelloBoardTest.get(0).getId());
        assertEquals("Test", trelloBoardTest.get(0).getName());
        assertEquals(0,trelloBoardTest.get(0).getLists().size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDto = testTrelloListDto();

        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(3, trelloList.size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard("123", "Test", testTrelloList());
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        int size = trelloBoardDtoList.size();
        String id = trelloBoardDtoList.get(0).getId();
        String name = trelloBoardDtoList.get(0).getName();

        //Then
        assertEquals(1, size);
        assertEquals("123", id);
        assertEquals("Test", name);
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloList = testTrelloList();

        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);
        int size = trelloListDto.size();

        //Then
        assertEquals(3, size);
    }

    /*public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
    }*/
    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test", "description test", "test", "123");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Test", trelloCard.getName());
        assertEquals("description test", trelloCard.getDescription());
        assertEquals("test", trelloCard.getPos());
        assertEquals("123", trelloCard.getListId());
    }

    /*public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
    }*/
    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test", "description test", "test", "123");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("Test", trelloCardDto.getName());
        assertEquals("description test", trelloCardDto.getDescription());
        assertEquals("test", trelloCardDto.getPos());
        assertEquals("123", trelloCardDto.getListId());
    }

    private List<TrelloList> testTrelloList() {
        TrelloList testList1 = new TrelloList("1", "Test1", true);
        TrelloList testList2 = new TrelloList("2", "Test2", false);
        TrelloList testList3 = new TrelloList("3", "Test3", true);

        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(testList1);
        trelloList.add(testList2);
        trelloList.add(testList3);
        return trelloList;
    }

    private List<TrelloListDto> testTrelloListDto() {
        TrelloListDto testListDto1 = new TrelloListDto("1", "Test1", true);
        TrelloListDto testListDto2 = new TrelloListDto("2", "Test2", false);
        TrelloListDto testListDto3 = new TrelloListDto("3", "Test3", true);

        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(testListDto1);
        trelloListDto.add(testListDto2);
        trelloListDto.add(testListDto3);
        return trelloListDto;
    }

}
