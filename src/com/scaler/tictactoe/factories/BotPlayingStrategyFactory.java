package com.scaler.tictactoe.factories;

import com.scaler.tictactoe.models.BotDifficultyLevel;
import com.scaler.tictactoe.strategies.botplayingstrategy.BotPlayingStrategy;
import com.scaler.tictactoe.strategies.botplayingstrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getStrategyForDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        // switch case
        return new RandomBotPlayingStrategy();
    }
}
