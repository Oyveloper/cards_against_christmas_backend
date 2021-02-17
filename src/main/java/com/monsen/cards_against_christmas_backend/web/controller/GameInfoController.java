package com.monsen.cards_against_christmas_backend.web.controller;

import com.monsen.cards_against_christmas_backend.game.CACGame;
import com.monsen.cards_against_christmas_backend.game.Player;
import com.monsen.cards_against_christmas_backend.web.DTO.Message;
import com.monsen.cards_against_christmas_backend.web.service.CardService;
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
    private final CardService cardService;


    @Autowired
    public GameInfoController(GameService gameService, CardService cardService) {
        this.gameService = gameService;
        this.cardService = cardService;
    }


    @GetMapping("/gameExists")
    public ResponseEntity<Message<Boolean>> gameExists(@RequestParam String gameId) {
        return new ResponseEntity<>(new Message<>(this.gameService.gameExists(gameId), "OK", ""), HttpStatus.OK);
    }

    @PostMapping("/createGame")
    public ResponseEntity<Message<String>> createGame() {
        CACGame game = this.gameService.createGame();
        game.setWhiteCardDeck(cardService.getWhiteCardDeck());
        game.setBlackCardDeck(cardService.getBlackCardDeck());


        return new ResponseEntity<>(new Message<>(game.getId(), "OK", ""), HttpStatus.CREATED);
    }

    @GetMapping("/isPlayerNameTaken")
    public ResponseEntity<Message<Boolean>> isPlayerNameTaken(@RequestParam("playerName") String playerName, @RequestParam("gameId") String gameId) {
        if (!this.gameService.gameExists(gameId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean nameTaken = this.gameService.getGame(gameId)
                .getPlayers()
                .stream()
                .map(Player::getName)
                .anyMatch(n -> n.equals(playerName));

        return new ResponseEntity<>(new Message<>(nameTaken, "OK", ""), HttpStatus.OK);
    }

}
