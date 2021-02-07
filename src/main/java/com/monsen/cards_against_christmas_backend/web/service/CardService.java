package com.monsen.cards_against_christmas_backend.web.service;

import com.monsen.cards_against_christmas_backend.data.entity.BlackCard;
import com.monsen.cards_against_christmas_backend.data.entity.WhiteCard;
import com.monsen.cards_against_christmas_backend.data.repository.BlackCardRepository;
import com.monsen.cards_against_christmas_backend.data.repository.WhiteCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CardService {

    private final BlackCardRepository blackCardRepository;
    private final WhiteCardRepository whiteCardRepository;

    @Autowired
    public CardService(BlackCardRepository blackCardRepository, WhiteCardRepository whiteCardRepository) {
        this.blackCardRepository = blackCardRepository;
        this.whiteCardRepository = whiteCardRepository;
    }

    public Collection<WhiteCard> getWhiteCardDeck() {
        return StreamSupport.stream(this.whiteCardRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Collection<BlackCard> getBlackCardDeck() {
        return StreamSupport.stream(this.blackCardRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
