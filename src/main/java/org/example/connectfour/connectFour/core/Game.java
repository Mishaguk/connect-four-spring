package org.example.connectfour.connectFour.core;

import lombok.Getter;

public class Game {
    @Getter
    private final Board board;
    @Getter
    private Player currentPlayer;
    @Getter
    protected GameState gameState;
    private final BotPlayer botPlayer;
    private final boolean playingWithBot;
    public static String GAME_TITLE = "connectFour";
    @Getter
    private long lastActive;

    private int moveCount = 0;
    public Game(Board board, boolean playingWithBot, int botDifficulty) {
        this.board = board;
        this.currentPlayer = Player.PLAYER_ONE;
        this.gameState = GameState.PLAYING;
        this.playingWithBot = playingWithBot;
        this.botPlayer = playingWithBot ? new BotPlayer(Player.PLAYER_TWO, botDifficulty) : null; // in default case bot play as PLAYER_TWO
        updateLastActive();
    }

    public void updateLastActive() {
        lastActive = System.currentTimeMillis();
    }

    public boolean isGameOver() {
        return gameState != GameState.PLAYING && gameState != GameState.WAITING_FOR_PLAYER;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.getOpposite();
    }


    public boolean makeMove(int column) {
        if(gameState == GameState.PLAYING) {
            Cell lastCell = board.placePiece(currentPlayer, column);
            if (lastCell == null) return false;
            moveCount++;
            System.out.println(lastActive);
            updateLastActive();
            if (board.isWinner(currentPlayer, lastCell)) {
                gameState = currentPlayer == Player.PLAYER_ONE ? GameState.PLAYER_ONE_WON : GameState.PLAYER_TWO_WON;
            } else if (board.isDraw()) {
                gameState = GameState.DRAW;
            } else {
                switchPlayer();
            }
            return true;
        }
        return false;

    }

    public boolean playBotTurn() {
        if (playingWithBot && currentPlayer == Player.PLAYER_TWO) {
            int column = botPlayer.getBestMove(board);
            makeMove(column);
            updateLastActive();
            return true;
        }
        return false;
    }


    public int getScore(){
        if(!playingWithBot){
            return 0;
        }
        if(gameState == GameState.PLAYER_ONE_WON) {
            return 1000-30*(moveCount-4);
        }else if(gameState == GameState.PLAYER_TWO_WON) {
            return 0;
        } else if (gameState == GameState.DRAW) {
            return 600-30*(moveCount-4);
        }
        return 0;
    }

}
