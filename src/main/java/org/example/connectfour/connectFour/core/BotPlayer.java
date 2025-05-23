package org.example.connectfour.connectFour.core;

import java.util.ArrayList;
import java.util.List;

public class BotPlayer {
    private  final int MAX_DEPTH;
    private final Player player;

    public BotPlayer(Player player, int depth) {
        this.player = player;
        MAX_DEPTH = depth;
    }

    public int getBestMove(Board board) {
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

         for(int col: getValidMoves(board.getCells())){
             Board tempBoard = copyBoard(board);
             Cell testCell = makeMove(tempBoard, col, player);
             int score = minimax(tempBoard, MAX_DEPTH, false, Integer.MIN_VALUE, Integer.MAX_VALUE,testCell );
             if(score > bestScore){
                 bestScore = score;
                 bestMove = col;
             }
         }
        return bestMove;
    }

    private Cell makeMove(Board board, int col, Player player) {
        for (int row = board.getRows() - 1; row >= 0; row--) {
            Cell cell = board.getCells()[row][col];
            if (cell.getState() == CellState.EMPTY) {
                return board.placePiece(player, col);
            }

        }
        return null;
    }

    private int minimax(Board board, int depth, boolean maximizingPlayer, int alpha, int beta, Cell lastCell) {
        Cell[][] cells = board.getCells();
        if (depth == 0 || isTerminalNode(board, lastCell)) {
            return evaluateBoard(cells);
        }
        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int col : getValidMoves(cells)) {
                Board tempBoard = copyBoard(board);

                int eval = minimax(tempBoard, depth - 1, false, alpha, beta, makeMove(tempBoard, col, player));
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break;
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int col : getValidMoves(cells)) {
                Board tempBoard = copyBoard(board);
                int eval = minimax(tempBoard, depth - 1, true, alpha, beta, makeMove(tempBoard, col, player.getOpposite()));
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;
            }
            return minEval;
        }

    }

    private int evaluateBoard(Cell[][] board) {
        int score = 0;
        int centerColumn = board[0].length / 2;
        int centerCount = 0;
        for (Cell[] cells : board) {
            if (cells[centerColumn].getState() == player.toCellState()) centerCount++;
        }
        score += centerCount * 5;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col <= board[0].length - 4; col++) {
                score += evaluateWindow(board, row, col, 0, 1);
            }
        }
        for (int col = 0; col < board[0].length; col++) {
            for (int row = 0; row <= board.length - 4; row++) {
                score += evaluateWindow(board, row, col, 1, 0);
            }
        }
        for (int row = 0; row <= board.length - 4; row++) {
            for (int col = 0; col <= board[0].length - 4; col++) {
                score += evaluateWindow(board, row, col, 1, 1);
            }
        }
        for (int row = 0; row <= board.length - 4; row++) {
            for (int col = 3; col < board[0].length; col++) {
                score += evaluateWindow(board, row, col, 1, -1);
            }
        }
        return score;
    }

    private int evaluateWindow(Cell[][] board, int row, int col, int dy, int dx) {
        int botCount = 0;
        int opponentCount = 0;
        int emptyCount = 0;
        for (int i = 0; i < 4; i++) {

            Cell cell = board[row + i * dy][col + i * dx];
            if (cell.getState() == CellState.EMPTY) {
                emptyCount++;
            } else if (cell.getState() == player.getOpposite().toCellState()) {
                opponentCount++;
            } else if (cell.getState() == player.toCellState()) {
                botCount++;
            }
        }
        if (botCount == 4) return 100000;
        if (opponentCount == 4) return -100000;
        if (botCount == 3 && emptyCount == 1) return 100;
        if (opponentCount == 3 && emptyCount == 1) return -100;
        if (botCount == 2 && emptyCount == 2) return 10;
        if (opponentCount == 2 && emptyCount == 2) return -10;
        return 0;
    }

    private boolean isTerminalNode(Board board, Cell lastCell) {
        return board.isDraw() || board.isWinner(player, lastCell);
    }

    private List<Integer> getValidMoves(Cell[][] board) {
        List<Integer> validMoves = new ArrayList<>();
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col].getState() == CellState.EMPTY) validMoves.add(col);
        }
        return validMoves;
    }

    private Board copyBoard(Board board) {
        Board newBoard = new Board(board.getRows(), board.getColumns());
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                newBoard.getCells()[i][j] = new Cell(board.getCells()[i][j].getState(), i, j);
            }
        }
        return newBoard;
    }
}
