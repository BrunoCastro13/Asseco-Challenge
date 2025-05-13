package controller;

import org.Asseco.Challenge.model.GameRequest;
import org.Asseco.Challenge.model.GameResponse;
import org.Asseco.Challenge.service.CoinGameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CoinGameServiceTest {

    @InjectMocks
    private CoinGameService coinGameService;

    @Test
    public void testCalculateCoins_ExampleCase() {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(Arrays.asList("P", "P", "R"));
        request.setLeftPerson(Arrays.asList("P", "R", "R"));

        // Act
        GameResponse response = coinGameService.calculateCoins(request);

        // Assert
        assertEquals(4, response.getRightPerson());
        assertEquals(8, response.getLeftPerson());
    }

    @Test
    public void testCalculateCoins_InvalidAction() {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(Arrays.asList("P", "X", "R"));
        request.setLeftPerson(Arrays.asList("P", "R", "R"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> coinGameService.calculateCoins(request));
    }

    @Test
    public void testCalculateCoins_DifferentSizeLists() {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(Arrays.asList("P", "P"));
        request.setLeftPerson(Arrays.asList("P", "R", "R"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> coinGameService.calculateCoins(request));
    }

    @Test
    public void testCalculateCoins_NullLists() {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(null);
        request.setLeftPerson(Arrays.asList("P", "R", "R"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> coinGameService.calculateCoins(request));
    }
}