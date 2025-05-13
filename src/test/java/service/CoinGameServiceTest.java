package service;


import org.Asseco.Challenge.model.GameRequest;
import org.Asseco.Challenge.model.GameResponse;
import org.Asseco.Challenge.service.CoinGameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

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
    public void testCalculateCoins_BothPersonsShare() {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(Arrays.asList("P", "P"));
        request.setLeftPerson(Arrays.asList("P", "P"));

        // Act
        GameResponse response = coinGameService.calculateCoins(request);

        // Assert
        assertEquals(7, response.getRightPerson());
        assertEquals(7, response.getLeftPerson());
    }

    @Test
    public void testCalculateCoins_BothPersonsReceive() {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(Arrays.asList("R", "R"));
        request.setLeftPerson(Arrays.asList("R", "R"));

        // Act
        GameResponse response = coinGameService.calculateCoins(request);

        // Assert
        assertEquals(3, response.getRightPerson());
        assertEquals(3, response.getLeftPerson());
    }

    @Test
    public void testCalculateCoins_RightOnlyShares() {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(Arrays.asList("P", "P", "P"));
        request.setLeftPerson(Arrays.asList("R", "R", "R"));

        // Act
        GameResponse response = coinGameService.calculateCoins(request);

        // Assert
        // Right person starts with 3, gives 3, ends with 0
        // Left person starts with 3, gets 9, ends with 12
        assertEquals(0, response.getRightPerson());
        assertEquals(12, response.getLeftPerson());
    }

    @Test
    public void testCalculateCoins_LeftOnlyShares() {
        // Arrange
        GameRequest request = new GameRequest();
        request.setRightPerson(Arrays.asList("R", "R", "R"));
        request.setLeftPerson(Arrays.asList("P", "P", "P"));

        // Act
        GameResponse response = coinGameService.calculateCoins(request);

        // Assert
        assertEquals(12, response.getRightPerson());
        assertEquals(0, response.getLeftPerson());
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