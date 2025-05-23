package org.example.connectfour.core;

import org.example.connectfour.connectFour.core.Board;
import org.example.connectfour.connectFour.core.BotPlayer;
import org.example.connectfour.connectFour.core.Player;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class BotPlayerTest {
    private BotPlayer botPlayer;
    private Board board;
    BotPlayerTest() {
        botPlayer = new BotPlayer(Player.PLAYER_TWO, 3);
        board = new Board(6,7);
    }

    @Test
    public void testGetValidMove() {
        int column = botPlayer.getBestMove(board);
        assertTrue(column >=0 && column <= board.getColumns(),"Bot should return a valid column");
    }

    @Test
    public void testBotMove_AvoidFullColumns() {
        Player currentPlayer = Player.PLAYER_ONE;
        for(int row = 0; row < board.getRows(); row++) {
            board.placePiece(currentPlayer, 2);
            currentPlayer = currentPlayer.getOpposite();
        }
        int column = botPlayer.getBestMove(board);
        assertNotEquals(2, column, "Bot should return index of not full column");
    }

    @Test
    public void testBotMove_BlocksWinningMoveInRow() {
        board.placePiece(Player.PLAYER_ONE, 0);
        board.placePiece(Player.PLAYER_ONE, 1);
        board.placePiece(Player.PLAYER_ONE, 2);
        int column = botPlayer.getBestMove(board);
        assertEquals(3,column,"Bot should block the winning combination in row");
    }
    @Test
    public void testBotMove_BlocksWinningMoveInColumn() {
        board.placePiece(Player.PLAYER_ONE, 0);
        board.placePiece(Player.PLAYER_ONE, 0);
        board.placePiece(Player.PLAYER_ONE, 0);
        int column = botPlayer.getBestMove(board);
        assertEquals(0,column,"Bot should block the winning combination in column");
    }
    @Test
    public void testBotMove_BlocksWinningMoveInDiagonalLeft() {
        Player player = Player.PLAYER_ONE;
        Player opponent = Player.PLAYER_TWO;
        board.placePiece(player , 0);
        board.placePiece(opponent , 1);
        board.placePiece(player , 1);
        board.placePiece(opponent, 2);
        board.placePiece(player , 0);
        board.placePiece(opponent , 2);
        board.placePiece(player , 2);
        board.placePiece(opponent , 0);
        board.placePiece(player, 3);
        board.placePiece(opponent , 3);
        board.placePiece(player, 3);

        int column = botPlayer.getBestMove(board);
        assertEquals(3,column,"Bot should block the winning combination in diagonal left");
    }

    @Test
    public void testBotMove_BlocksWinningMoveInDiagonalRight() {
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

        int column = botPlayer.getBestMove(board);
        assertEquals(0,column,"Bot should block the winning combination in diagonal right");
    }



    @Test
    public void testBotMove_WinningMoveInRow() {
        board.placePiece(Player.PLAYER_TWO , 0);
        board.placePiece(Player.PLAYER_TWO , 1);
        board.placePiece(Player.PLAYER_TWO , 2);
        int column = botPlayer.getBestMove(board);
        assertEquals(3,column,"Bot should choose the winning combination in row");
    }

    @Test
    public void testBotMove_WinningMoveInColumn() {
        board.placePiece(Player.PLAYER_TWO , 1);
        board.placePiece(Player.PLAYER_TWO , 1);
        board.placePiece(Player.PLAYER_TWO , 1);
        int column = botPlayer.getBestMove(board);
        assertEquals(1,column,"Bot should choose the winning combination in row");
    }

    @Test
    public void testBotMove_WinningMoveInDiagonalLeft() {
        Player botPlayer = Player.PLAYER_TWO;
        Player opponent = Player.PLAYER_ONE;
        board.placePiece(botPlayer, 0);
        board.placePiece(opponent , 1);
        board.placePiece(botPlayer, 1);
        board.placePiece(opponent, 2);
        board.placePiece(botPlayer, 0);
        board.placePiece(opponent , 2);
        board.placePiece(botPlayer, 2);
        board.placePiece(opponent , 3);
        board.placePiece(botPlayer, 3);
        board.placePiece(opponent , 3);

        int column = this.botPlayer.getBestMove(board);
        assertEquals(3,column,"Bot should choose the winning combination in diagonal left");
    }

    @Test
    public void testBotMove_WinningMoveInDiagonalRight() {
        Player player = Player.PLAYER_TWO;
        Player opponent = Player.PLAYER_ONE ;
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

        int column = botPlayer.getBestMove(board);

        assertEquals(0,column,"Bot should choose the winning combination in diagonal right");
    }




}
