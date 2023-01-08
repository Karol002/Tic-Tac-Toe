package com.tictactoe;

import com.tictactoe.builders.ingredients.Judge;
import com.tictactoe.builders.ingredients.Player;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicTacToeTestSuite {
    private static int testCounter = 0;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Tests begin" + "\n");
    }

    @BeforeEach
    public void beforeEveryTest() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Test #" + testCounter + " complete \n");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Test finished");
    }

    public static List<String> emptyBoardGenerator() {
        List<String> emptyBoard = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            emptyBoard.add(" ");
        }
        return emptyBoard;
    }

    @Nested
    @DisplayName("Tests for Judge class")
    class JudgeFunctionTest {

        @Test
        void testForRowWinnerX() {
            //Given
            Judge judge = new Judge();
            Player player = new Player();
            List<String> row1 = emptyBoardGenerator();
            List<String> row2 = emptyBoardGenerator();
            List<String> row3 = emptyBoardGenerator();
            int strike = 3;
            int rowSize = 3;

            //When
            player.setFigure("X");
            for (int i = 0; i < 9; i++) {
                if (i < 3) row1.set(i, player.getFigure());
                else if (i < 6) row2.set(i, player.getFigure());
                else row3.set(i, player.getFigure());
            }
            boolean result1 = judge.checkWinner(row1, player.getFigure(), rowSize, strike);
            boolean result2 = judge.checkWinner(row2, player.getFigure(), rowSize, strike);
            boolean result3 = judge.checkWinner(row3, player.getFigure(), rowSize, strike);

            //Then
            assertTrue(result1);
            assertTrue(result2);
            assertTrue(result3);
        }

        @Test
        void testForColumnWinnerX() {
            //Given
            Judge judge = new Judge();
            Player player = new Player();
            List<String> row1 = emptyBoardGenerator();
            List<String> row2 = emptyBoardGenerator();
            List<String> row3 = emptyBoardGenerator();
            int strike = 3;
            int rowSize = 3;

            //When
            player.setFigure("X");
            for (int i = 0; i < 9; i++) {
                if (i == 0 || i == 3 || i == 6) row1.set(i, player.getFigure());
                if (i == 1 || i == 4 || i == 7) row2.set(i, player.getFigure());
                if (i == 2 || i == 5 || i == 8) row3.set(i, player.getFigure());
            }
            boolean result1 = judge.checkWinner(row1, player.getFigure(), rowSize, strike);
            boolean result2 = judge.checkWinner(row2, player.getFigure(), rowSize, strike);
            boolean result3 = judge.checkWinner(row3, player.getFigure(), rowSize, strike);

            //Then
            assertTrue(result1);
            assertTrue(result2);
            assertTrue(result3);
        }


        @Test
        void testForInitialWinnerX() {
            //Given
            Judge judge = new Judge();
            Player player = new Player();
            List<String> row1 = emptyBoardGenerator();
            List<String> row2 = emptyBoardGenerator();
            int strike = 3;
            int rowSize = 3;

            //When
            player.setFigure("X");
            for (int i = 0; i < 9; i++) {
                if (i == 0 || i == 4 || i == 8) row1.set(i, player.getFigure());
                if (i == 2 || i == 4 || i == 6) row2.set(i, player.getFigure());
            }
            boolean result1 = judge.checkWinner(row1, player.getFigure(), rowSize, strike);
            boolean result2 = judge.checkWinner(row2, player.getFigure(), rowSize, strike);

            //Then
            assertTrue(result1);
            assertTrue(result2);
        }

        @Test
        void testForRowWinnerO() {
            //Given
            Judge judge = new Judge();
            Player player = new Player();
            List<String> row1 = emptyBoardGenerator();
            List<String> row2 = emptyBoardGenerator();
            List<String> row3 = emptyBoardGenerator();
            int strike = 3;
            int rowSize = 3;

            //When
            player.setFigure("O");
            for (int i = 0; i < 9; i++) {
                if (i < 3) row1.set(i, player.getFigure());
                else if (i < 6) row2.set(i, player.getFigure());
                else row3.set(i, player.getFigure());
            }
            boolean result1 = judge.checkWinner(row1, player.getFigure(), rowSize, strike);
            boolean result2 = judge.checkWinner(row2, player.getFigure(), rowSize, strike);
            boolean result3 = judge.checkWinner(row3, player.getFigure(), rowSize, strike);

            //Then
            assertTrue(result1);
            assertTrue(result2);
            assertTrue(result3);
        }

        @Test
        void testForColumnWinnerO() {
            //Given
            Judge judge = new Judge();
            Player player = new Player();
            List<String> row1 = emptyBoardGenerator();
            List<String> row2 = emptyBoardGenerator();
            List<String> row3 = emptyBoardGenerator();
            int strike = 3;
            int rowSize = 3;

            //When
            player.setFigure("O");
            for (int i = 0; i < 9; i++) {
                if (i == 0 || i == 3 || i == 6) row1.set(i, player.getFigure());
                if (i == 1 || i == 4 || i == 7) row2.set(i, player.getFigure());
                if (i == 2 || i == 5 || i == 8) row3.set(i, player.getFigure());
            }
            boolean result1 = judge.checkWinner(row1, player.getFigure(), rowSize, strike);
            boolean result2 = judge.checkWinner(row2, player.getFigure(), rowSize, strike);
            boolean result3 = judge.checkWinner(row3, player.getFigure(), rowSize, strike);

            //Then
            assertTrue(result1);
            assertTrue(result2);
            assertTrue(result3);
        }


        @Test
        void testForInitialWinnerO() {
            //Given
            Judge judge = new Judge();
            Player player = new Player();
            List<String> row1 = emptyBoardGenerator();
            List<String> row2 = emptyBoardGenerator();
            int strike = 3;
            int rowSize = 3;

            //When
            player.setFigure("O");
            for (int i = 0; i < 9; i++) {
                if (i == 0 || i == 4 || i == 8) row1.set(i, player.getFigure());
                if (i == 2 || i == 4 || i == 6) row2.set(i, player.getFigure());
            }
            boolean result1 = judge.checkWinner(row1, player.getFigure(), rowSize, strike);
            boolean result2 = judge.checkWinner(row2, player.getFigure(), rowSize, strike);

            //Then
            assertTrue(result1);
            assertTrue(result2);
        }

        @Test
        void testForDraw() {
            //Given
            Judge judge = new Judge();
            Player player1 = new Player();
            Player player2 = new Player();
            List<String> row = emptyBoardGenerator();

            //When
            player1.setFigure("O");
            player2.setFigure("X");

            for (int i = 0; i < 9; i++) {
                if (i == 0 || i == 1 || i == 5 || i == 8) row.set(i, player1.getFigure());
                else row.set(i, player2.getFigure());
            }
            boolean result1 = judge.checkEndGame(row);

            //Then
            assertTrue(result1);
        }
    }
}
