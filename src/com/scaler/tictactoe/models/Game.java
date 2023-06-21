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

    //TODO:: Implement UNDO
    public void undo() {}

    public void makeNextMove() {
        Player toMovePlayer = players.get(nextPlayerIndex);
        System.out.println("It is " + toMovePlayer.getName() + "'s turn");

        Move move = toMovePlayer.decideMove(board);

        // validate the move

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        System.out.println("Move happened at: " + row + ", " + col + ".");

        // update board
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(toMovePlayer);

        Move finalMove = new Move(
                toMovePlayer,
                board.getBoard().get(row).get(col)
        );

        moves.add(finalMove);

        if(gameWinningStrategy.checkWinner(board,
                 toMovePlayer, finalMove.getCell())){
            gameStatus = GameStatus.ENDED;
            winner = toMovePlayer;
        }

        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();
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
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));

            game.setNextPlayerIndex(0);

            return game;
        }
    }
}

/*
1 2 3 4 5 6 7 8 9 10


 */
