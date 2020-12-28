package com.monsen.cards_against_christmas_backend.controller;

import com.monsen.cards_against_christmas_backend.game.CACGameManager;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean gameExists(@RequestParam String id) {
        return this.gameManager.doesGameExist(id);
    }

    @PostMapping("/createGame")
    public void createGame(@RequestParam String id) {
        if (this.gameManager.doesGameExist(id)) {
            throw new IllegalStateException("Game already exxists");
        }
        this.gameManager.addGame(id);
    }

}
