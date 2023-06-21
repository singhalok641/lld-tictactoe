package com.scaler.tictactoe.strategies.gamewinningstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy {

    public OrderOneGameWinningStrategy(int dimension) {

    }
    @Override
    public boolean checkWinner(Board board, Player lastMovePlayer, Cell moveCell) {
        return false;
    }
}
