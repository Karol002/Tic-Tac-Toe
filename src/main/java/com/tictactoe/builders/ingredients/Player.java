package com.tictactoe.builders.ingredients;

public class Player {
    private static int playerNumber = 1;
    private String name = "unNamed" + playerNumber;
    private String figure = "E";
    private boolean ai = false;
    private boolean winner = false;

    public Player() {
        playerNumber++;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }
}