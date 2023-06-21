package com.scaler.tictactoe.models;

import com.scaler.tictactoe.exceptions.InvalidGameConstructionParametersException;
import com.scaler.tictactoe.strategies.gamewinningstrategy.GameWinningStrategy;
import com.scaler.tictactoe.strategies.gamewinningstrategy.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;

    // Will check this later
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    // private constructor for game
    private Game() {
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void undo() {}

    public void makeNextMove() {
    }

    public void displayBoard() {
        this.board.display();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean validate() throws InvalidGameConstructionParametersException{
            // 3 * 3 -> 2
            // 2 * 2 -> 1
            if(dimension < 3){
                throw new InvalidGameConstructionParametersException("Dimension of " +
                        "game can't be less than 3");
            }

            if(this.players.size() != this.dimension - 1){
                throw new InvalidGameConstructionParametersException("The number of players" +
                        "should be 1 less than the dimension of the board");
            }

            // Validate that no 2 players with same symbol

            // Validate there is only 1 bot in the game

            return true;
        }

        public Game build() throws InvalidGameConstructionParametersException {
            // validations
            validate();

            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimension));

            game.setNextPlayerIndex(0);

            return game;
        }
    }
}
