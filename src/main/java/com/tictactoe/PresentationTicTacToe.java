package com.tictactoe;

import com.tictactoe.builders.control.Game;
import com.tictactoe.builders.presentation.ItemsSetter;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class PresentationTicTacToe extends Application {
    Game game = new Game();
    List<Tile> board = new ArrayList<>();
    TurnTile turnTile = new TurnTile();
    ItemsSetter itemsSetter = new ItemsSetter();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 450);

        Label welcomeLabel = itemsSetter.setLabel("Player please configure your game", 513.0, 44.0, 44.0, 14.0, 29, true);

        Label gameModeLabel = itemsSetter.setLabel("Chose game mode", 330.0, 29.0, 24.0, 75.0, 18.0, true);
        ChoiceBox<String> gameMode = itemsSetter.setChoiceBox(new String[]{"3x3 to 3", "10x10 to 5"}, "Chose", 198.0, 35.0, 364.0, 73.0, true);

        Label difficultlyLevelLabel = itemsSetter.setLabel("Chose difficultly Level",  330.0, 29.0, 24.0, 218.0, 18.0,false);
        ChoiceBox<String> difficultlyLevel = itemsSetter.setChoiceBox(new String[]{"Easy", "Hard"}, "Chose", 198.0, 35.0, 364.0, 218.0,  false);

        Label opponentNameLabel = itemsSetter.setLabel("Enter your name Player 2", 330.0, 29.0, 24.0, 218.0, 18.0, false);
        TextField opponentName = itemsSetter.setTextField(198.0, 35.0, 364.0, 218.0, 19, false);

        Label opponentLabel = itemsSetter.setLabel("Set opponent",  330.0, 29.0, 24.0, 118.0, 18.0, true);
        ChoiceBox<String> opponent = itemsSetter.setChoiceBox(new String[]{"Computer", "Other Player"}, "Chose", 198.0, 35.0, 364.0, 118.0, true);
        opponent.setOnAction(e -> getOpponentChoice(opponent, opponentNameLabel, opponentName, difficultlyLevelLabel, difficultlyLevel));


        Label playerNameLabel = itemsSetter.setLabel("Enter your name Player 1", 333.0, 29.0, 24.0, 166.0, 18.0,true);
        TextField playerName = itemsSetter.setTextField(198.0, 35.0, 364.0, 166.0, 19, true);

        Label winLabel = itemsSetter.setLabel("",250.0, 29.0, 120.0, 510.0,  19, false);
        Label turnLabel = itemsSetter.setLabel("Turn player: ", 150.0, 29.0, 150.0, 510.0, 19, true);
        Label winQuantityLabel = itemsSetter.setLabel("", 400.0, 29.0, 40.0, 540.0, 19, false);

        Button repeatButton = itemsSetter.setButton("Repeat Game", 388.0, 38.0, 31.0, 550.0, 19, false);
        repeatButton.setOnMouseClicked(e -> {
            repeatGame(winLabel, turnLabel, repeatButton);
            primaryStage.setScene(new Scene((createContent(winLabel, turnLabel,  repeatButton, winQuantityLabel))));
            primaryStage.show();
        });


        Button startGameButton = itemsSetter.setButton("StartGame", 388.0, 38.0, 106.0, 370.0, 19, false);
        startGameButton.setOnAction(e -> {
            primaryStage.setScene(new Scene(createContent(winLabel, turnLabel, repeatButton, winQuantityLabel)));
            primaryStage.show();
        });


        Button saveGameButton = itemsSetter.setButton("Save settings", 388.0, 38.0, 106.0, 320.0, 19, true);
        saveGameButton.setOnAction(e -> {
            if (opponent.getValue().equals("Other Player") || (opponent.getValue().equals("Chose"))) {
                if (gameMode.getValue().equals("Chose") || opponent.getValue().equals("Chose")
                        || opponentName.getText().equals("") || playerName.getText().equals("")) {
                    welcomeLabel.setText("                  Enter all data!");
                } else if (difficultlyLevel.getValue().equals("Hard") && gameMode.getValue().equals("10x10 to 5")) {
                    welcomeLabel.setText("Hard level only available in 3x3");
                } else {
                    checklInputData(welcomeLabel, gameMode, difficultlyLevel, opponentName, opponent, playerName, startGameButton, saveGameButton);
                }
            }  else {
                if (gameMode.getValue().equals("Chose") || opponent.getValue().equals("Chose")
                        || playerName.getText().equals("") || difficultlyLevel.getValue().equals("Chose")) {
                    welcomeLabel.setText("                  Enter all data!");
                } else if (difficultlyLevel.getValue().equals("Hard") && gameMode.getValue().equals("10x10 to 5")) {
                    welcomeLabel.setText("Hard level only available in 3x3 mode is 3");
                } else {
                    checklInputData(welcomeLabel, gameMode, difficultlyLevel, opponentName, opponent, playerName, startGameButton, saveGameButton);
                }
            }
        });

        root.getChildren().addAll(gameMode, opponent, playerName, opponentName,saveGameButton, startGameButton, difficultlyLevelLabel);
        root.getChildren().addAll(welcomeLabel,gameModeLabel, opponentLabel, playerNameLabel, opponentNameLabel, difficultlyLevel);
        Scene scene = new Scene(root, 600, 451, Color.GRAY);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void checklInputData(Label welcomeLabel, ChoiceBox<String> gameMode, ChoiceBox<String> difficultlyLevel, TextField opponentName, ChoiceBox<String> opponent, TextField playerName, Button startGameButton, Button saveGameButton) {
        game.setAdvancedRowSize(gameMode.getValue());
        game.setOpponentControl(opponent.getValue());
        game.setPlayersNames(playerName.getText(), opponentName.getText());
        game.prepareGameData();
        game.setHard(difficultlyLevel.getValue());

        startGameButton.setVisible(true);
        saveGameButton.setDisable(true);
        welcomeLabel.setText("                   Press Start!");
    }


    private Parent createContent(Label winLabel, Label turnLabel, Button repeatButton, Label winQuantityLabel) {
        final int paneWidth = 450;
        final int paneHeight = 600;

        Pane root = new Pane();
        root.setPrefSize(paneWidth,paneHeight);

        final int rowSize = game.getRowSize();
        final int tilePosition = paneWidth / rowSize;
        int tileNumber = 0;


        for (int i=0; i<rowSize; i++) {
            for (int j=0; j<rowSize; j++) {
                board.add(new Tile(paneWidth));
                board.get(tileNumber).setTranslateY((i * tilePosition) + 40);
                board.get(tileNumber).setTranslateX(j * tilePosition);

                root.getChildren().add(board.get(tileNumber));
                board.get(tileNumber).setTileNumber(tileNumber);
                tileNumber++;
            }
        }

        for (Tile tile : board) {
            tile.setOnMouseClicked(event -> {
                if (tile.makeMove()) showWinner(winLabel, turnLabel, repeatButton, winQuantityLabel);
            });
        }

        root.getChildren().addAll(turnLabel, turnTile, winLabel, repeatButton, winQuantityLabel);
        return root;
    }

    public class Tile extends StackPane {
        private final Text text = new Text();
        private int tileNumber = 0;

        public Tile(int paneWidth) {

            int rectangleSize = paneWidth / game.getRowSize();
            Rectangle border = new Rectangle(rectangleSize, rectangleSize);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            if (game.getRowSize() == 3) text.setFont(Font.font(72));
            else text.setFont(Font.font(24));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);
            setCursor(Cursor.HAND);
        }

        public void setTileNumber(int tileNumber) {
            this.tileNumber = tileNumber;
        }

        public void draw(String figure) {
            text.setText(figure);
            Color color;
            if (figure.equals("X")) color = BLUE;
            else color = RED;
            text.setFill(color);
        }

        public boolean makeMove() {
            if (!game.isEndGame()) {
                if (game.checkMovePossibility(tileNumber)) {
                    setCursor(Cursor.DEFAULT);
                    if (game.makeBattle(tileNumber)) {
                        draw("X");
                        turnTile.draw("O");
                    }
                    else {
                        draw("O");
                        turnTile.draw("X");
                    }
                    computerMove();
                }
            }
            return game.isEndGame();
        }
    }

    private static class TurnTile extends StackPane {
        private final Text text = new Text();

        public TurnTile() {
            Rectangle border = new Rectangle(20, 20);
            setTranslateX(280); setTranslateY(515);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            text.setFont(Font.font(15));
            draw("O");

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

        }

        public void draw(String figure) {
            text.setText(figure);
            Color color;
            if (figure.equals("X")) color = BLUE;
            else color = RED;
            text.setFill(color);
        }
    }

    public void repeatGame(Label winLabel, Label turnLabel, Button repeatButton) {
        game.repeatGame();
        board.clear();
        repeatButton.setVisible(false);
        winLabel.setVisible(false);
        turnLabel.setVisible(true);
        turnTile.draw("O");
        turnTile.setVisible(true);
    }

    public void showWinner(Label winLabel, Label turnLabel, Button repeatButton, Label winQuantityLabel) {
        winLabel.setText(game.makeEndGame());
        winLabel.setVisible(true);
        turnLabel.setVisible(false);
        turnTile.setVisible(false);
        winQuantityLabel.setVisible(false);
        repeatButton.setVisible(true);
    }

    public void computerMove() {
        if (game.getPlayer2().isAi() && !game.isEndGame()) {
            int pcMove = game.makeAiMove();
            game.makeBattle(pcMove);
            board.get(pcMove).draw("X");
            turnTile.draw("O");
        }
    }

    public void getOpponentChoice(ChoiceBox<String> opponent, Label opponentNameLabel, TextField opponentName, Label difficultlyLevelLabel, ChoiceBox<String> difficultlyLevel) {
        if (opponent.getValue().equals("Computer")) {
            opponentNameLabel.setVisible(false);
            opponentName.setVisible(false);
            difficultlyLevelLabel.setVisible(true);
            difficultlyLevel.setVisible(true);
        } else {
            opponentNameLabel.setVisible(true);
            opponentName.setVisible(true);
            difficultlyLevelLabel.setVisible(false);
            difficultlyLevel.setVisible(false);
        }
    }
}