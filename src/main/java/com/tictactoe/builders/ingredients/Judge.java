package com.tictactoe.builders.ingredients;

import java.util.List;

public class Judge {

    public boolean checkWinner(List <String> gameData, String figure, int rowSize, int strike){
        boolean isWinner = false;

        isWinner = checkInitialWinnerAdvanced(gameData, figure, rowSize, strike);
        if (!isWinner) isWinner = checkColumnWinner(gameData, figure, rowSize, strike);
        if (!isWinner) isWinner = checkRowWinner(gameData, figure, rowSize, strike);

        return isWinner;
    }

    private boolean checkInitialWinnerAdvanced(List <String> gameData, String figure, int rowSize, int strike) {
        boolean isWinner = true;
        int figuresStrike = 0;

        for (int i=0; i<gameData.size()-((strike*rowSize)-rowSize); i+=rowSize) {

            for (int j=i; j<gameData.size()-(i/10); j+=rowSize+1) {
                if (!(gameData.get(j).equals(figure))) figuresStrike = 0;
                else figuresStrike++;
                isWinner = figuresStrike >= strike;
                if (isWinner) break;
            }
            if (isWinner) break;

            figuresStrike = 0;
            for (int j=i/10; j<gameData.size(); j+=rowSize+1) {
                if (j >= gameData.size()-i) break;
                else {
                    if (!(gameData.get(j).equals(figure))) figuresStrike = 0;
                    else figuresStrike++;
                    isWinner = figuresStrike >= strike;
                }
                if (isWinner) break;
            }
            if (isWinner) break;
        }


        if (!isWinner) {
            figuresStrike = 0;
            for (int i=rowSize - 1; i<gameData.size()-((strike*rowSize)-rowSize); i+=rowSize) {

                for (int j=i; j<gameData.size()-(strike-1); j+=rowSize - 1) {
                    if (!(gameData.get(j).equals(figure))) figuresStrike = 0;
                    else figuresStrike++;
                    isWinner = figuresStrike >= strike;
                    if (isWinner) break;
                }
                if (isWinner) break;

                figuresStrike = 0;
                for (int j=i/10; j<gameData.size(); j+=rowSize-1) {
                    if (j >= i) break;
                    else {
                        if (!(gameData.get(j).equals(figure))) figuresStrike = 0;
                        else figuresStrike++;
                        isWinner = figuresStrike >= strike;
                    }
                    if (isWinner) break;
                }
                if (isWinner) break;
            }
        }

        return isWinner;
    }

    private boolean checkColumnWinner(List <String> gameData, String figure, int rowSize, int strike) {
        boolean isWinner = false;
        int figuresStrike = 0;

        for (int i=0; i<rowSize; i++) {
            isWinner = true;
            figuresStrike = 0;

            for (int j=0; j<gameData.size(); j+=rowSize) {
                if (!gameData.get(j+i).equals(figure)) figuresStrike = 0;
                else figuresStrike++;
                if (figuresStrike >= strike) break;
            }
            if (figuresStrike < strike) isWinner = false;
            if (isWinner) break;
        }

        return isWinner;
    }

    private boolean checkRowWinner(List <String> gameData, String figure, int rowSize, int strike) {
        boolean isWinner = false;
        int figuresStrike = 0;

        for (int i=0; i<gameData.size(); i+=rowSize) {
            isWinner = true;
            figuresStrike = 0;

            for (int j=0; j<rowSize; j++) {
                if (!gameData.get(j+i).equals(figure)) figuresStrike = 0;
                else figuresStrike++;
                if (figuresStrike >= strike) break;
            }
            if (figuresStrike < strike) isWinner = false;
            if (isWinner) break;
        }
        return isWinner;
    }

    public boolean checkEndGame(List <String> gameData) {
        boolean end = true;
        for(String theGame : gameData) {
            if (theGame.equals(" ")) {
                end = false;
                break;
            }
        }
        return end;
    }
}