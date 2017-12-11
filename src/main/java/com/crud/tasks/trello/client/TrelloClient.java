package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class TrelloClient {

    @org.springframework.beans.factory.annotation.Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @org.springframework.beans.factory.annotation.Value("${trello.app.key}")
    private String trelloAppKey;

    @org.springframework.beans.factory.annotation.Value("${trello.app.token}")
    private String trelloToken;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/mfabijanczuk/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id").build().encode().toUri();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
    }
}
