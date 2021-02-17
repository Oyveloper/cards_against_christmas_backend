package com.monsen.cards_against_christmas_backend.data.repository;

import com.monsen.cards_against_christmas_backend.data.entity.WhiteCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhiteCardRepository extends CrudRepository<WhiteCard, String> {
}
