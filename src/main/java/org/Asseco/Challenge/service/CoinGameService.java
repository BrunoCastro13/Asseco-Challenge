package org.Asseco.Challenge.service;

import org.Asseco.Challenge.model.GameRequest;
import org.Asseco.Challenge.model.GameResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinGameService {

    private static final int INITIAL_COINS = 3;
    private static final int COST_TO_SHARE = 1;
    private static final int REWARD_FOR_SHARE = 3;
    private static final String SHARE = "P";
    private static final String RECEIVE = "R";

    public GameResponse calculateCoins(GameRequest request) {
        List<String> rightPersonActions = request.getRightPerson();
        List<String> leftPersonActions = request.getLeftPerson();

        // Validate actions format
        validateActions(rightPersonActions);
        validateActions(leftPersonActions);

        // Validate both lists have same size
        if (rightPersonActions.size() != leftPersonActions.size()) {
            throw new IllegalArgumentException("Both persons must have the same number of actions");
        }

        int rightPersonCoins = INITIAL_COINS;
        int leftPersonCoins = INITIAL_COINS;

        for (int i = 0; i < rightPersonActions.size(); i++) {
            String rightAction = rightPersonActions.get(i);
            String leftAction = leftPersonActions.get(i);

            // Right person shares
            if (SHARE.equals(rightAction)) {
                if (rightPersonCoins >= COST_TO_SHARE) {
                    rightPersonCoins -= COST_TO_SHARE;
                    leftPersonCoins += REWARD_FOR_SHARE;
                }
            }

            // Left person shares
            if (SHARE.equals(leftAction)) {
                if (leftPersonCoins >= COST_TO_SHARE) {
                    leftPersonCoins -= COST_TO_SHARE;
                    rightPersonCoins += REWARD_FOR_SHARE;
                }
            }
        }

        return new GameResponse(rightPersonCoins, leftPersonCoins);
    }

    private void validateActions(List<String> actions) {
        if (actions == null) {
            throw new IllegalArgumentException("Actions cannot be null");
        }

        for (String action : actions) {
            if (!SHARE.equals(action) && !RECEIVE.equals(action)) {
                throw new IllegalArgumentException("Invalid action: " + action + ". Only 'P' (Share) or 'R' (Receive) are allowed");
            }
        }
    }
}