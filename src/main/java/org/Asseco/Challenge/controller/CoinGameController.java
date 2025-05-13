package org.Asseco.Challenge.controller;

import jakarta.validation.Valid;
import org.Asseco.Challenge.model.GameRequest;
import org.Asseco.Challenge.model.GameResponse;
import org.Asseco.Challenge.service.CoinGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoinGameController {

    private final CoinGameService coinGameService;

    @Autowired
    public CoinGameController(CoinGameService coinGameService) {
        this.coinGameService = coinGameService;
    }

    @PostMapping("/coins")
    public ResponseEntity<GameResponse> processGame(@Valid @RequestBody GameRequest request) {
        GameResponse response = coinGameService.calculateCoins(request);
        return ResponseEntity.ok(response);
    }
}