package org.example.connectfour.core;

import org.example.connectfour.connectFour.core.Board;
import org.example.connectfour.connectFour.core.Game;
import org.example.connectfour.connectFour.core.GameState;
import org.example.connectfour.connectFour.core.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game;
    GameTest() {
        game = new Game(new Board(5,6), false, 3);
    }

    @Test
    void testMakeMove_ValidMove() {
          boolean result =  game.makeMove(3);
            assertTrue(result, "Should return true after valid move");
    }
    @Test
    void testMakeMove_InvalidMove() {
        boolean result =  game.makeMove(-10);
        assertFalse(result, "Should return false after invalid move");
    }
    @Test
    void testMakeMove_SwitchPlayerAfterValidMove() {
        Player currentPlayer = game.getCurrentPlayer();
        game.makeMove(3);
        assertEquals(currentPlayer, game.getCurrentPlayer().getOpposite(), "Should switch player after valid move");
    }
    @Test
    void testMakeMove_NotSwitchPlayerAfterInvalidMove() {
        Player currentPlayer = game.getCurrentPlayer();
        game.makeMove(-11);
        assertEquals(currentPlayer, game.getCurrentPlayer(), "Shouldn't switch player after invalid move");
    }
    @Test
    void testMakeMove_GameStatePlaying() {
        game.makeMove(0);
        game.makeMove(1);
        game.makeMove(0);
        assertEquals(GameState.PLAYING,game.getGameState(), "Game state should be PLAYING   ");
    }

    @Test
    void testMakeMove_StateChangeWinPlayerOne() {
        game.makeMove(0);
        game.makeMove(1);
        game.makeMove(0);
        game.makeMove(1);
        game.makeMove(0);
        game.makeMove(1);
        game.makeMove(0);
        assertEquals(GameState.PLAYER_ONE_WON,game.getGameState(), "Game state should be PLAYER_ONE_WON");
    }
    @Test
    void testMakeMove_StateChangeWinPlayerTwo() {
        game.makeMove(0);
        game.makeMove(1);
        game.makeMove(0);
        game.makeMove(1);
        game.makeMove(0);
        game.makeMove(1);
        game.makeMove(2);
        game.makeMove(1);
        assertEquals(GameState.PLAYER_TWO_WON,game.getGameState(), "Game state should be PLAYER_TWO_WON");
    }
    @Test
    void testMakeMove_StateChangeDraw() {
        int[] pattern = {
                0,1,0,1,0,1,
                1,0,1,0,2,3,
                2,3,3,2,2,2,
                3,3,5,5,4,4,
                5,4,5,4,4,5,

        };
        for (int i = 0; i < game.getBoard().getColumns()*game.getBoard().getRows(); i++) {
               game.makeMove(pattern[i]);
        }
        assertEquals(GameState.DRAW,game.getGameState(), "Game state should be DRAW");
    }

    @Test
    void testMakeMove_ShouldReturnFalse_WhenStateNotPlaying() {
        game.makeMove(0);
        game.makeMove(1);
        game.makeMove(0);
        game.makeMove(1);
        game.makeMove(0);
        game.makeMove(1);
        game.makeMove(2);
        game.makeMove(1);
        assertEquals(GameState.PLAYER_TWO_WON,game.getGameState(), "Game state should be PLAYER_TWO_WON");
        boolean result = game.makeMove(4);
        assertFalse(result, "Should return false after player won");
    }

    @Test
    void testPlayBotTurn_ShouldReturnFalse_WhenNotPlayingWithBot(){
        boolean result = game.playBotTurn();
        assertFalse(result, "Should return false if playing without bot");
    }

    @Test
    void testPlayBotTurn_ShouldReturnTrue_WhenItsBotTurn(){
        game = new Game(new Board(5,6), true, 3);
        game.makeMove(0);
        boolean result = game.playBotTurn();
        assertTrue(result, "Should return true if playing with bot");
    }


}
