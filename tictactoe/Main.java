package tictactoe;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static char[][] boardData(String input) {
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
        System.out.println("---------");
    }

    public static boolean threeAcross(char[][] board, char playerTicker) {
        for (int i = 0; i < 3; i++) {
            if (Character.valueOf(board[i][0]).equals(playerTicker)
                    && Character.valueOf(board[i][1]).equals(playerTicker)
                    && Character.valueOf(board[i][2]).equals(playerTicker)) {
                return true;
            }
        }
        return false;
    }

    public static boolean threeDown(char[][] board, char playerTicker) {
        for (int i = 0; i < 3; i++) {
            if (Character.valueOf(board[0][i]).equals(playerTicker)
                    && Character.valueOf(board[1][i]).equals(playerTicker)
                    && Character.valueOf(board[2][i]).equals(playerTicker)) {
                return true;
            }
        }
        return false;
    }

    public static boolean threeDiagonal(char[][] board, char playerTicker) {
        for (int i = 0; i < 3; i++) {
            if (Character.valueOf(board[0][0]).equals(playerTicker)
                    && Character.valueOf(board[1][1]).equals(playerTicker)
                    && Character.valueOf(board[2][2]).equals(playerTicker)
                    || Character.valueOf(board[0][2]).equals(playerTicker)
                    && Character.valueOf(board[1][1]).equals(playerTicker)
                    && Character.valueOf(board[2][0]).equals(playerTicker)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasWon(char[][] board, char playerTicker) {
        if (threeAcross(board, playerTicker)) {
            return true;
        } else if (threeDown(board, playerTicker)) {
            return true;
        } else {
            return threeDiagonal(board, playerTicker);
        }
    }

    public static boolean checkOpenSpaces(char[][] board) {
        for (char[] row : board) {
            for (char col : row) {
                if (Character.valueOf(col).equals(' ')) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkNumOfTickerDiff(char[][] board, char x, char o) {
        int countX = 0;
        int countO = 0;

        for (char[] row : board) {
            for (char col : row) {
                if (Character.valueOf(col).equals(x)) {
                    countX++;
                }
                if (Character.valueOf(col).equals(o)) {
                    countO++;
                }
            }
        }
        return countX > countO + 1 || countO > countX + 1;
    }

    public static void checkGameStatus(char[][] board) {
        char x = 'X';
        char o = 'O';

        if (hasWon(board, x) && hasWon(board, o) || checkNumOfTickerDiff(board, x, o)) {
            System.out.print("Impossible");
        } else if (hasWon(board, x) && !hasWon(board, o)) {
            System.out.print("X wins");
        } else if (hasWon(board, o) && !hasWon(board, x)) {
            System.out.print("O wins");
        } else if (!hasWon(board, x) && !hasWon(board, o)) {
            if (checkOpenSpaces(board)) {
                System.out.print("Game not finished");
            } else {
                System.out.print("Draw");
            }
        }
    }

    public static void main(String[] args) {
        String input = scanner.nextLine();
        displayBoard(boardData(input));
        checkGameStatus(boardData(input));
    }
}
