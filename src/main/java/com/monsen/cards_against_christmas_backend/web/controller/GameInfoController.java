package com.monsen.cards_against_christmas_backend.web.controller;

import com.monsen.cards_against_christmas_backend.game.CACGameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameInfoController {

    private final CACGameManager gameManager;


    @Autowired
    public GameInfoController(CACGameManager manager) {
        gameManager = manager;
    }

    @GetMapping("/gameExists")
    public ResponseEntity<Boolean> gameExists(@RequestParam String id) {
        return new ResponseEntity<>(this.gameManager.doesGameExist(id), HttpStatus.OK);
    }

    @PostMapping("/createGame")
    public ResponseEntity<?> createGame(@RequestParam String id) {
        if (this.gameManager.doesGameExist(id)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        this.gameManager.addGame(id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
