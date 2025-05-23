package org.example.connectfour.core;

import org.example.connectfour.connectFour.core.Board;
import org.example.connectfour.connectFour.core.Cell;
import org.example.connectfour.connectFour.core.CellState;
import org.example.connectfour.connectFour.core.Player;
import org.junit.jupiter.api.Test;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;
    private  int rowCount;
    private  int colCount;
    private final Random randomGenerator = new Random();


    public BoardTest() {
            rowCount = randomGenerator.nextInt(6) + 4;
            colCount = randomGenerator.nextInt(6) + 4;
            board = new Board(rowCount, colCount);

    }

    private void setupFixedBoard(int rows, int cols) {
        this.rowCount = rows;
        this.colCount = cols;
        this.board = new Board(rows, cols);
    }

    @Test
    void testInitializeBoard() {
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                Cell cell = board.getCell(i, j);
                assertNotNull(cell);
                assertEquals(CellState.EMPTY, cell.getState(), "Cell must be empty");
                assertEquals(i, cell.getRow(), "Wrong row index");
                assertEquals(j, cell.getColumn(), "Wrong column index");
            }
        }
    }

    @Test
    void testPlacePiece_ValidMove() {
        int column = randomGenerator.nextInt(colCount);
        Player player = Player.PLAYER_ONE;
        Cell result = board.placePiece(player, column);
        assertNotNull(result, "Result must not be null");
        assertEquals(CellState.PLAYER_ONE, result.getState(), "Cell must contain player piece");
    }

    @Test
    void testPlacePiece_InvalidMove() {
        int column = 11;
        Player player = Player.PLAYER_ONE;
        Cell result = board.placePiece(player, column);
        assertNull(result, "placePiece should return null for out of bounds");
    }

    @Test
    void testPlacePiece_ColumnFull() {
        int column = 0;
        Player player = Player.PLAYER_ONE;
        for (int  row = 0;  row < rowCount;  row++) {
            Cell result = board.placePiece(player, column);
            assertNotNull(result, "Result should not be null");
        }
        Cell result = board.placePiece(player, column);
        assertNull(result, "Result should be null, column full");

    }

    @Test
    void testWin_FourInRow(){
        Player player = Player.PLAYER_ONE;
        Player opponent = Player.PLAYER_TWO;

        board.placePiece(player , 0);
        board.placePiece(player , 1);
        board.placePiece(opponent, 1);
        board.placePiece(player , 2);
        board.placePiece(opponent, 2);
        Cell result =  board.placePiece(player , 3);
        assertTrue( board.isWinner(player, result), "Should return true when player wins with four connected horizontally");
    }

    @Test
    void testWin_FourInColumn(){
        Player player = Player.PLAYER_ONE;
        Player opponent = Player.PLAYER_TWO;
        board.placePiece(player , 0);
        board.placePiece(player , 0);
        board.placePiece(opponent, 1);
        board.placePiece(player , 0);
        board.placePiece(opponent, 1);
        Cell result =  board.placePiece(player , 0);
        assertTrue( board.isWinner(player, result), "Should return true when player wins with four connected vertically");
    }

    @Test
    void testWin_FourInDiagonalLeft(){
        Player player = Player.PLAYER_ONE;
        Player opponent = Player.PLAYER_TWO;
        board.placePiece(player , 0);
        board.placePiece(opponent , 1);
        board.placePiece(player , 1);
        board.placePiece(opponent, 2);
        board.placePiece(player , 0);
        board.placePiece(opponent , 2);
        board.placePiece(player , 2);
        board.placePiece(opponent , 3);
        board.placePiece(player, 3);
        board.placePiece(opponent , 3);
       Cell result =  board.placePiece(player , 3);
       assertTrue(board.isWinner(player, result), "Should return true when player wins with four connected diagonally left");

    }

    @Test
    void testWin_FourInDiagonalRight(){
        Player player = Player.PLAYER_ONE;
        Player opponent = Player.PLAYER_TWO;
        board.placePiece(player , 3);
        board.placePiece(opponent , 2);
        board.placePiece(player , 2);
        board.placePiece(opponent, 1);
        board.placePiece(player , 3);
        board.placePiece(opponent , 1);
        board.placePiece(player , 1);
        board.placePiece(opponent , 0);
        board.placePiece(player, 0);
        board.placePiece(opponent , 0);
        Cell result =  board.placePiece(player , 0);
        assertTrue(board.isWinner(player, result), "Should return true when player wins with four connected diagonally right");

    }

    @Test
    void testDraw(){
        setupFixedBoard(4,4);
        int[][] pattern = {
                {1, 2, 2, 2},
                {1, 2, 1, 1},
                {1, 2, 1, 2},
                {2, 1, 2, 1}
        };

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                board.placePiece(pattern[row][col] == 1 ? Player.PLAYER_ONE : Player.PLAYER_TWO, col);
            }
        }
        Cell lastCell = board.getCell(board.getRows()-1, board.getColumns()-1);
        assertFalse(board.isWinner(lastCell.getState() == CellState.PLAYER_ONE ? Player.PLAYER_ONE : Player.PLAYER_TWO, lastCell), "Should not be winner");
        lastCell = board.getCell(board.getRows()-1, board.getColumns()-2);
        assertFalse(board.isWinner(lastCell.getState() == CellState.PLAYER_ONE ? Player.PLAYER_ONE : Player.PLAYER_TWO, lastCell), "Should not be winner");
        assertTrue(board.isDraw(), "Should return true when draw");
    }





}
