package org.example.connectfour.connectFour.core;

public class Board {

    private final Cell[][] cells;
    private final int rows;
    private final int columns;


    public Board(int rows, int columns) {
        if(rows < 4 || rows > 9 || columns < 4 || columns > 9){
            throw new IllegalArgumentException("rows and columns must be between 4 and 9");
        }
        this.rows = rows;
        this.columns = columns;
        cells = new Cell[rows][columns];
        initializeBoard();
    }

    private void initializeBoard(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(CellState.EMPTY, i, j);
            }
        }
    }

    public int getColumns() {
        return columns;
    }
    public int getRows() {
        return rows;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public Cell placePiece(Player currentPlayer, int column){
        if(column < 0 || column >= columns){
            return null;
        }
            for(int row = rows-1; row >= 0; row--){
                if(this.cells[row][column].getState() == CellState.EMPTY){
                    this.cells[row][column].setState(currentPlayer.toCellState());
                    return this.cells[row][column];
                }
        }
        return null;
    }

    private boolean isValid(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    private int countCells(int row , int column, Player currentPlayer, int dy, int dx){
        int rowCount = row + dy;
        int columnCount = column + dx;
        int count = 0;
        while(isValid(rowCount, columnCount) && cells[rowCount][columnCount].getState() == currentPlayer.toCellState()){
            rowCount +=dy;
            columnCount += dx;
            count++;
        }
        return count;
    }

    private boolean checkDirection(Cell cell, Player currentPlayer, int dy, int dx){
        int count = 1;
        count += countCells(cell.getRow(), cell.getColumn(), currentPlayer, dy, dx);
        count += countCells(cell.getRow(), cell.getColumn(), currentPlayer, -dy, -dx);
        return count >= 4;

    }

    public boolean isWinner(Player currentPlayer, Cell cell){
        if(cell == null) return false;
        return checkDirection(cell, currentPlayer, -1, 0)
                || checkDirection(cell, currentPlayer, 0, -1)
                || checkDirection(cell, currentPlayer, -1, -1)
                || checkDirection(cell, currentPlayer, -1, 1);
    }
    public boolean isDraw(){
        for (int i = 0; i < columns; i++) {
            if(this.cells[0][i].getState() == CellState.EMPTY) return false;
        }
        return true;
    }


}
