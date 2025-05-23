package org.example.connectfour.server.multiplayer;

import lombok.Getter;
import lombok.Setter;
import org.example.connectfour.connectFour.core.Board;
import org.example.connectfour.connectFour.core.Game;
import org.example.connectfour.connectFour.core.GameState;

import java.util.Objects;

public class MultiplayerGame extends Game {
    @Setter @Getter
    private  String player1;
    @Getter
    private  String player2;
    @Getter
    private String currentPlayerTurn;

    public MultiplayerGame(Board board, boolean playingWithBot, int botDifficulty, String player1, String player2) {
        super(board, playingWithBot, botDifficulty);
        this.player1 = player1;
        this.player2 = player2;
        currentPlayerTurn = player1;
        this.gameState = GameState.WAITING_FOR_PLAYER;
    }


    private void switchPlayerTurn() {
        currentPlayerTurn = Objects.equals(currentPlayerTurn, player1) ? player2 : player1;
    }


    public void makeMove(String username, int column) {

        if(Objects.equals(currentPlayerTurn, username)) {
            super.makeMove(column);
            switchPlayerTurn();
        }
    }

    public void setPlayer2(String username) {
        this.player2 = username;
        if(isGameOver()) return;
        if(username == null) {
            this.gameState = GameState.WAITING_FOR_PLAYER;
        }  else this.gameState = GameState.PLAYING;
    }


}
