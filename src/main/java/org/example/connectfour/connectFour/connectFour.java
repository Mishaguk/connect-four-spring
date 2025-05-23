package org.example.connectfour.connectFour;

import org.example.connectfour.connectFour.core.Board;
import org.example.connectfour.connectFour.core.Game;
import org.example.connectfour.connectFour.setup.GameSetup;

import java.util.Scanner;

public class connectFour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = GameSetup.getBoardSize(scanner, "rows");
        int cols = GameSetup.getBoardSize(scanner, "columns");
        boolean playingWithBot = GameSetup.isPlayingWithBot(scanner);

        Board board = new Board(rows, cols);
        Game game  = new Game(board, playingWithBot, 3);
        //ConsoleUI ui = new ConsoleUI(game);

    }
}
