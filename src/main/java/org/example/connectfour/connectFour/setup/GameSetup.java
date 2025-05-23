package org.example.connectfour.connectFour.setup;

import org.example.connectfour.connectFour.core.Board;
import org.example.connectfour.connectFour.core.Game;
import org.springframework.stereotype.Service;


import java.util.Scanner;

@Service
public class GameSetup {
    private static final int MIN_SIZE = 4;
    private static final int MAX_SIZE = 9;
    private final Scanner scanner ;

    public GameSetup() {
        scanner = new Scanner(System.in);
    }

    public static int getBoardSize(Scanner scanner, String dimension) {
        int size;
            System.out.printf("Enter the number of %s (%d-%d): ", dimension, MIN_SIZE, MAX_SIZE);
            size = scanner.nextInt();
        return size;
    }

    public static boolean isPlayingWithBot(Scanner scanner) {
        int option;
        do {
            System.out.println("Play with player - enter [1]");
            System.out.println("Play with AI bot - enter [2]");
            option = scanner.nextInt();
        } while (option != 1 && option != 2);
        return option == 2;
    }


    public Game createGame() {
        int rows = getBoardSize(scanner,"rows");
        int cols = getBoardSize(scanner,"columns");
        boolean playingWithBot = isPlayingWithBot(scanner);
        Board board = new Board(rows, cols);
        return new Game(board, playingWithBot , 3);
    }

}
