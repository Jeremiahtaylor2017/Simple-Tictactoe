package tictactoe;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static char[][] boardData() {
        String input = scanner.nextLine();
        char[][] board = new char[3][3];
        int i = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = input.charAt(i++);
            }
        }
        return board;
    }

    public static void displayBoard(char[][] board) {
        System.out.println("---------");
        for (char[] row : board) {
            System.out.print("| ");
            for (char col : row) {
                System.out.printf("%c ", col);
            }
            System.out.println("|");
        }
        System.out.print("---------");
    }

    public static void main(String[] args) {
        displayBoard(boardData());
    }
}
