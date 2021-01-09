package com.monsen.cards_against_christmas_backend.web.controller;

import com.monsen.cards_against_christmas_backend.game.CACGameManager;
import com.monsen.cards_against_christmas_backend.web.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameInfoController {

    private final GameService gameService;


    @Autowired
    public GameInfoController(GameService gameService) {
        this.gameService = gameService;
    }



    @GetMapping("/gameExists")
    public ResponseEntity<Boolean> gameExists(@RequestParam String id) {
        return new ResponseEntity<>(this.gameService.gameExists(id), HttpStatus.OK);
    }

    @PostMapping("/createGame")
    public ResponseEntity<?> createGame(@RequestParam String id) {
        if (this.gameService.gameExists(id)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        this.gameService.createGame(id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
