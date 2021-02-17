package com.monsen.cards_against_christmas_backend.web.controller;

import com.monsen.cards_against_christmas_backend.data.entity.WhiteCard;
import com.monsen.cards_against_christmas_backend.game.CACGame;
import com.monsen.cards_against_christmas_backend.game.Player;
import com.monsen.cards_against_christmas_backend.web.DTO.ClientMessage;
import com.monsen.cards_against_christmas_backend.web.DTO.GameUpdateDTO;
import com.monsen.cards_against_christmas_backend.web.DTO.Message;
import com.monsen.cards_against_christmas_backend.web.DTO.PlayerHandDTO;
import com.monsen.cards_against_christmas_backend.web.service.CardService;
import com.monsen.cards_against_christmas_backend.web.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class GamePlayController {

    private final GameService gameService;
    private final CardService cardService;

    Logger logger = LoggerFactory.getLogger(GamePlayController.class);

    @Autowired
    public GamePlayController(GameService gameService, CardService cardService) {
        this.gameService = gameService;
        this.cardService = cardService;
    }


    @MessageMapping("/joinGame/{gameId}")
    @SendTo("/topic/GameUpdate/{gameId}")
    public Message<GameUpdateDTO> joinGame(@DestinationVariable("gameId") String gameId, @RequestParam ClientMessage clientMessage) {
        logger.info("A new player is trying to join " + gameId + ". PlayerName: " + clientMessage.getPlayerName());
        Message<GameUpdateDTO> message = new Message<>();


        try {
            CACGame game = gameService.getGame(gameId);
            boolean isHost = game.getPlayers().size() == 0;
            Player player = new Player(clientMessage.getPlayerName(), isHost);
            game.addPlayer(player);
            GameUpdateDTO gameUpdateDTO = new GameUpdateDTO(game, "join");
            message.setData(gameUpdateDTO);
            message.setStatus("OK");
            message.setExplanation("");

        } catch (Exception e) {
            logger.error("Something went wrong when joining a game");
            logger.error(e.getMessage());
            message.setStatus("Error");
            message.setExplanation("Something went wrong i guess");
        }

        return message;
    }

    @MessageMapping("/{gameId}/newRound")
    @SendTo("/topic/GameUpdate/{gameId}")
    public Message<GameUpdateDTO> startRound(@DestinationVariable("gameId") String gameId) {
        logger.info("Starting a new round for the game with id: " + gameId);

        Message<GameUpdateDTO> message = new Message<>();
        try {
            CACGame game = gameService.getGame(gameId);
            game.newRound();
            message.setData(new GameUpdateDTO(game, "newRound"));
            message.setStatus("OK");
            message.setExplanation("");
        } catch (Exception e) {
            logger.error("Error occured when starting a new round");
            logger.error(e.getMessage());
            message.setStatus("Error");
            message.setExplanation("Could not create a new round!");
        }

        return message;
    }

    @MessageMapping("/{gameId}/playCard")
    @SendTo("/topic/GameUpdate/{gameId}")
    public Message<GameUpdateDTO> playCard(@DestinationVariable("gameId") String gameId, @RequestParam("message") ClientMessage clientMessage) {
        Message<GameUpdateDTO> message = new Message<>();

        logger.info(clientMessage.getPlayerName() + " just played the card with id: " + clientMessage.getCardId());

        try {
            CACGame game = gameService.getGame(gameId);
            Player player = game.getPlayerByName(clientMessage.getPlayerName());
            WhiteCard card = cardService.getWhiteCardById(clientMessage.getCardId());
            game.getCurrentRound().playCard(player, card);
            game.addWhiteCardToDeck(card);
            message.setData(new GameUpdateDTO(game, "cardPlayed"));
            message.setStatus("OK");
            message.setExplanation("");
        } catch (Exception e) {
            logger.error("Something went wrong when playing card");
            logger.error(e.getMessage());
            message.setStatus("Error");
            message.setExplanation("Could not play the card");
        }


        return message;
    }

    @MessageMapping("/{gameId}/chooseWinnerCard")
    @SendTo("/topic/GameUpdate/{gameId}")
    public Message<GameUpdateDTO> chooseWinnerCard(@DestinationVariable("gameId") String gameId, @RequestParam("message") ClientMessage clientMessage) {
        logger.info("Chosing the card with id: " + clientMessage.getCardId() + " as the winner for this round in the game: " + gameId);
        Message<GameUpdateDTO> message = new Message<>();

        try {
            CACGame game = gameService.getGame(gameId);
            WhiteCard card = cardService.getWhiteCardById(clientMessage.getCardId());
            game.getCurrentRound().pickWinnerCard(card);

            logger.info("The winner was: " + game.getCurrentRound().getWinner().getName());

            message.setData(new GameUpdateDTO(game, "cardChosen"));
            message.setStatus("OK");
            message.setExplanation("");
        } catch (Exception e) {
            logger.error("Something went wrong when chosing winner");
            logger.error(e.getMessage());
            message.setStatus("Error");
            message.setExplanation("Could not choose the winner card");
        }

        return message;
    }

    @GetMapping("/drawHand")
    public ResponseEntity<Message<PlayerHandDTO>> drawHand(@RequestParam("gameId") String gameId) {
        logger.info("Player is drawing a hand in game: " + gameId);
        if (!this.gameService.gameExists(gameId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PlayerHandDTO dto = new PlayerHandDTO(this.gameService.drawHand(gameId));

        return new ResponseEntity<>(new Message<>(dto, "OK", ""), HttpStatus.OK);
    }

    @GetMapping("/drawCard")
    public ResponseEntity<Message<PlayerHandDTO>> drawCard(@RequestParam("gameId") String gameId) {
        logger.info("Player is drawing a card in game: " + gameId);
        if (!this.gameService.gameExists(gameId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PlayerHandDTO dto = new PlayerHandDTO(Collections.singletonList(this.gameService.drawCard(gameId)));

        return new ResponseEntity<>(new Message<>(dto, "OK", ""), HttpStatus.OK);

    }


}
