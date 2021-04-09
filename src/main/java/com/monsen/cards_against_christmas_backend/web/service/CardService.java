package com.monsen.cards_against_christmas_backend.web.service;

import com.monsen.cards_against_christmas_backend.data.entity.BlackCard;
import com.monsen.cards_against_christmas_backend.data.entity.DeckResponse;
import com.monsen.cards_against_christmas_backend.data.entity.WhiteCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final static String baseURI = "https://qd1w06e2.api.sanity.io/v2021-04-06/data/query/production";
    private final String listQuery = baseURI + "?query=*[_type == $type]&$type=";
    private final RestTemplate restTemplate;

    @Autowired
    public CardService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Collection<WhiteCard> getWhiteCardDeck() {
        DeckResponse response = restTemplate
                .getForObject(listQuery + "\"whiteCard\"", DeckResponse.class);
        assert response != null;
        return response.getResult().stream()
                .map(card -> new WhiteCard(card.get_id(), card.getContent()))
                .collect(Collectors.toList());
    }

    public Collection<BlackCard> getBlackCardDeck() {
        DeckResponse response = restTemplate
                .getForObject(listQuery + "\"blackCard\"", DeckResponse.class);
        assert response != null;
        return response.getResult()
                .stream()
                .map(card -> new BlackCard(card.get_id(), card.getContent(), card.getNumber_of_blank()))
                .collect(Collectors.toList());
    }

    public WhiteCard getWhiteCardById(String id) {
        String singleQuery = baseURI + "?query=*[_id == $id]&$id=";
        DeckResponse response = restTemplate.getForObject(singleQuery + "\"" + id + "\"", DeckResponse.class);
        assert response != null;
        return response
                .getResult()
                .stream()
                .map(card -> new WhiteCard(card.get_id(), card.getContent()))
                .findFirst().orElse(null);
    }

}
