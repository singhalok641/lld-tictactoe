package com.scaler.tictactoe.models;

import java.util.Scanner;

public class Player {
    // 0, X
    private char symbol;

    private String name;

    // Human or Bot
    private PlayerType type;

    public Player(String name, char aChar, PlayerType type) {
        this.name = name;
        this.symbol = aChar;
        this.type = type;
    }

    public Move decideMove(Board board) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please tell the row where you want to make the move, " +
                "starting the 0");
        int row = in.nextInt();

        System.out.println("Please tell the col where you want to make the move, " +
                "starting the 0");
        int col = in.nextInt();

        return new Move(this, new Cell(row, col));
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }
}
