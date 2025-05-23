package org.example.connectfour.connectFour.core;

import lombok.Setter;

public class Cell {

    @Setter
    private CellState state;
    private final int row;
    private final int column;

    public Cell(CellState state, int row, int column) {
        this.state = state;
        this.row = row;
        this.column = column;
    }

    public String getStyle(){
        if(this.state == CellState.PLAYER_ONE){
            return "player-one-cell";
        } else if (this.state == CellState.PLAYER_TWO){
            return "player-two-cell";
        }
        return "empty-cell";
    }

    public CellState getState() {
        return state;
    }

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
}
