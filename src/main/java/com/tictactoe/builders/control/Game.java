package com.tictactoe.builders.control;

import com.tictactoe.builders.ingredients.Judge;
import com.tictactoe.builders.ingredients.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static final Judge judge = new Judge();
    private final List<String> gameBoard = new ArrayList<>();
    private final Player player1 = new Player();
    private final Player player2 = new Player();
    private boolean endGame = false;
    private boolean turn = false;
    private int rowSize = 3;
    private int strike = 3;
    private boolean isHard = false;
    private int bestMove;


    public void setHard(String hard) {
        isHard = hard.equals("Hard");
    }

    public boolean isEndGame() {
        return endGame;
    }

    public int getRowSize() {
        return rowSize;
    }
    private boolean controlActualTurn(boolean actualTurn) {
        return !actualTurn;
    }

    public void setOpponentControl(String ai) {player2.setAi(ai.equals("Computer"));}

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayersNames(String playerName, String opponentName) {
        player1.setName(playerName);
        if (player2.isAi()) player2.setName("Computer");
        else player2.setName(opponentName);
    }

    protected void setFigures() {
        player1.setFigure("O");
        player2.setFigure("X");
    }


    public void prepareGameData() {
        setFigures();
        loadBoard();
    }

    public void setAdvancedRowSize(String advanced) {
        boolean isAdvanced = (advanced.equals("10x10 to 5"));
        if (isAdvanced) {
            rowSize = 10;
            strike = 5;

        } else {
            rowSize = 3;
            strike = 3;
        }
    }

    private void loadBoard() {
        for (int i = 0; i< rowSize * rowSize; i++) {
            gameBoard.add(" ");
        }
    }

    public boolean makeBattle(int move) {

        if (!turn) {
            player1.setWinner(choseMovePlace(player1, move));
            if (player1.isWinner()) endGame = true;
        } else {
            player2.setWinner(choseMovePlace(player2, move));
            if (player2.isWinner()) endGame = true;
        }

        turn = controlActualTurn(turn);
        return  !turn;
    }

    private boolean choseMovePlace(Player player, int move) {
        String figure = player.getFigure();

        gameBoard.set(move, figure);
        endGame = judge.checkEndGame(gameBoard);
        return judge.checkWinner(gameBoard, player.getFigure(), rowSize, strike);
    }

    public boolean checkMovePossibility(int move) {
        return gameBoard.get(move).equals(" ");
    }
    private boolean checkMovePossibilityMinMax(int move, List<String> minMax) {
        return minMax.get(move).equals(" ");
    }

    public int makeAiMove() {
        if (!isHard) return makeEasyMove();
        else {
            return makeHardMove();
        }
    }

    private int makeHardMove() {
        minMax(player2.getFigure(), 0);
        return bestMove;
    }

    private int minMax(String figure, int level) {
        int counter = 0;
        int holder = 0;

        for (int i=0; i<gameBoard.size(); i++) {
            if (gameBoard.get(i).equals(" ")) {
                gameBoard.set(i, figure);
                holder = i;
                counter++;

                boolean isWin = judge.checkWinner(gameBoard, figure, rowSize, strike);

                gameBoard.set(i, " ");
                if (isWin) {
                    bestMove = i;
                    if (figure.equals("X")) return -1;
                    else return 1;
                }
            }
        }

        if (counter == 1) {
            if (level == 0) bestMove = holder;
            return 0;
        }

        int v;
        int vmax;

        vmax = figure.equals("X") ? 2 : -2;

        //if (figure.equals("X")) vmax = 2;
        //else vmax = -2;

        for (int i=0; i< gameBoard.size(); i++) {
            if (gameBoard.get(i).equals(" ")) {
                gameBoard.set(i, figure);
                v = minMax(figure.equals("X") ? "O" : "X", level + 1);
                gameBoard.set(i, " ");

                if (((figure.equals("X")) && (v < vmax)) || ((figure.equals("O")) && (v > vmax))) {
                    vmax = v;
                    holder = i;
                }
            }
        }

        if (level == 0) bestMove = holder;

        return vmax;
    }

  /*  private int minMax(List<String> boardMinMax, int moveQuantity, int actualMove) {
        boardMinMax.set(actualMove, player2.getFigure());
        if (judge.checkEndGame(boardMinMax)) moveQuantity = 1000;
        else {
            for (int i = 0; i<boardMinMax.size(); i++) {
                if (checkMovePossibilityMinMax(i, boardMinMax)) {
                    boardMinMax.set(i, player2.getFigure());
                    if (judge.checkWinner(boardMinMax, player2.getFigure(), rowSize, strike)) {
                        return moveQuantity++;
                    } else {
                        return minMax(boardMinMax, moveQuantity++, i);
                    }
                }
            }
        }
        return moveQuantity;
    }

    private int makeHardMove() {
        List<String> boardMinMax = new ArrayList<>(gameBoard);
        int lowestQuantity = 999999998;
        int quantityMoveToWin = 999999999;
        int bestMove = 777;

        for (int i=0; i<gameBoard.size(); i++) {
            if (checkMovePossibilityMinMax(i, boardMinMax)) {
                boardMinMax.set(i, player2.getFigure());
                if (judge.checkWinner(boardMinMax, player2.getFigure(), rowSize, strike)) {
                    return i;
                }
                boardMinMax.set(i, " ");
            }
        }
        for (int i=0; i<gameBoard.size(); i++) {
            if (checkMovePossibilityMinMax(i, boardMinMax)) {
                boardMinMax.set(i, player1.getFigure());
                if (judge.checkWinner(boardMinMax, player1.getFigure(), rowSize, strike)) {
                    return i;
                }
                boardMinMax.set(i, " ");
            }
        }

        for (int i=0 ; i<gameBoard.size(); i++) {
            if (checkMovePossibilityMinMax(i, boardMinMax)) {
                quantityMoveToWin = minMax(boardMinMax, quantityMoveToWin, i);
                if (quantityMoveToWin < lowestQuantity) bestMove = i;
            }
        }
        if (bestMove > gameBoard.size()) {
            return makeEasyMove();
        }

        return bestMove;
    }*/

    private int makeEasyMove() {
        Random random = new Random();
        int move = random.nextInt(gameBoard.size());

        while (!checkMovePossibility(move)) {
            move = random.nextInt(gameBoard.size());
        }
        return move;
    }

    public void repeatGame() {
        endGame = false;
        gameBoard.clear();
        player2.setWinner(false);
        player1.setWinner(false);
        turn = false;
        loadBoard();
    }

    public String makeEndGame() {
        String winner = "The winner is ";
        if (player1.isWinner()) {
            winner += player1.getName();
        }
        else if (player2.isWinner()) {
            winner += player2.getName();
        }
        else return "   The result is Draw!";


        return beautifyInscription(winner);
    }

    private String beautifyInscription(String inscription) {
        if (inscription.length() < 19) {
            inscription = "    " + inscription;
        }
        return inscription;
    }
}