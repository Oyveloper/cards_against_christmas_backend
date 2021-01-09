package com.monsen.cards_against_christmas_backend.web.controller;

import com.monsen.cards_against_christmas_backend.game.CACGame;
import com.monsen.cards_against_christmas_backend.game.Player;
import com.monsen.cards_against_christmas_backend.web.DTO.GameUpdateDTO;
import com.monsen.cards_against_christmas_backend.web.DTO.Message;
import com.monsen.cards_against_christmas_backend.web.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GamePlayController {

    private final GameService gameService;

    @Autowired
    public GamePlayController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/joinGame/{gameId}")
    @SendTo("/topic/GameUpdate/{gameId}")
    public Message<GameUpdateDTO> joinGame(@DestinationVariable("gameId") String gameId, @RequestParam("playerName") String playerName) {
        Message<GameUpdateDTO> message = new Message<>();
        if (this.gameService.gameExists(gameId)) {
            CACGame game = gameService.getGame(gameId);
            game.addPlayer(new Player(playerName));
            GameUpdateDTO gameUpdateDTO = new GameUpdateDTO(game);
            message.setData(gameUpdateDTO);
            message.setStatus("OK");
            message.setExplanation("");
        } else {
            message.setStatus("Error");
            message.setExplanation("Something went wrong i guess");
        }

        System.out.println(message);

        return message;
    }
}
