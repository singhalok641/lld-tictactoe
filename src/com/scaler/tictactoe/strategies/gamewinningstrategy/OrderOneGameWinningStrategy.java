package com.scaler.tictactoe.strategies.gamewinningstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy {

    // 3 + 3 + 2
    // n + n + 2

    // n hashmaps in this list for rows
    private List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();

    //n hashmaps in this list for cols
    private List<HashMap<Character, Integer>> colSymbolsCounts = new ArrayList<>();
    private HashMap<Character, Integer> topLeftDiagonalSymbolCounts = new HashMap<>();
    private HashMap<Character, Integer> topRightDiagonalSymbolCOunts = new HashMap<>();

    public OrderOneGameWinningStrategy(int dimension) {
        for(int i=0;i<dimension;i++){
            rowSymbolCounts.add(new HashMap<>());
            colSymbolsCounts.add(new HashMap<>());
        }

    }

    private boolean isTopLeftDiagonal(int row, int col){
        return row==col;
    }

    private boolean isTopRightDiagonal(int row, int col, int dimension){
        return row + col == dimension - 1;
    }

    @Override
    public boolean checkWinner(Board board, Player lastMovePlayer, Cell moveCell) {
        char symbol = lastMovePlayer.getSymbol();
        int row = moveCell.getRow();
        int col = moveCell.getCol();
        int dimension = board.getBoard().size();

        // If not present then initialise the key "symbol" with 0
        if(!rowSymbolCounts.get(row).containsKey(symbol)){
            rowSymbolCounts.get(row).put(symbol, 0);
        }

        // update the value of the current symbol
        rowSymbolCounts.get(row).put(
                symbol, rowSymbolCounts.get(row).get(symbol) + 1
        );

        // If not present then initialise the key "symbol" with 0
        if(!colSymbolsCounts.get(col).containsKey(symbol)){
            colSymbolsCounts.get(col).put(symbol, 0);
        }

        // update the value of the current symbol
        colSymbolsCounts.get(col).put(
                symbol, colSymbolsCounts.get(col).get(symbol) + 1
        );

        // Check if cell is part of topLeftDiagonal
        if(isTopLeftDiagonal(row, col)){
            // If not present then initialise the key "symbol" with 0
            if(!topLeftDiagonalSymbolCounts.containsKey(symbol)){
                topLeftDiagonalSymbolCounts.put(symbol, 0);
            }

            // update the value of the current symbol
            topLeftDiagonalSymbolCounts.put(
                    symbol, topLeftDiagonalSymbolCounts.get(symbol) + 1
            );
        }

        // Check if cell is part of topRightDiagonal
        if(isTopRightDiagonal(row, col, dimension)){
            // If not present then initialise the key "symbol" with 0
            if(!topRightDiagonalSymbolCOunts.containsKey(symbol)){
                topRightDiagonalSymbolCOunts.put(symbol, 0);
            }

            // update the value of the current symbol
            topRightDiagonalSymbolCOunts.put(
                    symbol, topRightDiagonalSymbolCOunts.get(symbol) + 1
            );
        }

        // I am only checking for diagonal
        if(rowSymbolCounts.get(row).get(symbol) == dimension) return true;
        if(colSymbolsCounts.get(col).get(symbol) == dimension) return true;


        if(isTopRightDiagonal(row,col,dimension) &&
                topRightDiagonalSymbolCOunts.get(symbol) == dimension) return true;
        if(isTopLeftDiagonal(row,col) &&
                topLeftDiagonalSymbolCounts.get(symbol) == dimension) return true;


        return false;
    }
}

// map.put(1, 1);






