package controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.Asseco.Challenge.CoinGameApplication;
import org.Asseco.Challenge.controller.CoinGameController;
import org.Asseco.Challenge.model.GameRequest;
import org.Asseco.Challenge.model.GameResponse;
import org.Asseco.Challenge.service.CoinGameService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoinGameController.class)
@ContextConfiguration(classes = CoinGameApplication.class)
public class CoinGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoinGameService coinGameService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testProcessGame_ValidRequest() throws Exception {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(Arrays.asList("P", "P", "R"));
        request.setLeftPerson(Arrays.asList("P", "R", "R"));

        GameResponse response = new GameResponse(4, 8);

        when(coinGameService.calculateCoins(any(GameRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/coins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rightPerson").value(4))
                .andExpect(jsonPath("$.leftPerson").value(8));
    }

    @Test
    public void testProcessGame_InvalidRequest() throws Exception {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(Arrays.asList("P", "P", "R"));
        request.setLeftPerson(null); // Invalido, teria de ter o mesmo número de ações que a rightPerson

        // Act & Assert
        mockMvc.perform(post("/coins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testProcessGame_ServiceThrowsException() throws Exception {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(Arrays.asList("P", "P", "R"));
        request.setLeftPerson(Arrays.asList("P", "R", "R"));

        when(coinGameService.calculateCoins(any(GameRequest.class)))
                .thenThrow(new IllegalArgumentException("Invalid action"));

        // Act & Assert
        mockMvc.perform(post("/coins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}