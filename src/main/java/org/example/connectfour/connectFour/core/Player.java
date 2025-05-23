package org.example.connectfour.connectFour.core;

public enum Player {
    PLAYER_ONE, PLAYER_TWO ;

    public CellState toCellState() {
        return this == PLAYER_ONE ? CellState.PLAYER_ONE : CellState.PLAYER_TWO;
    }
    public Player getOpposite(){
        return this == PLAYER_ONE ? PLAYER_TWO : PLAYER_ONE;
    }
}
