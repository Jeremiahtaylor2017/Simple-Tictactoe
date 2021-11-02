package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char[][] board = new char[3][3];

    // Sets the board up to start with empty cells
    public static char[][] boardData() {
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }
        return board;
    }

    // Displays the current board status of the game
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

    // Checks the win condition for 3 across
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

    // Checks the wins condition for three down
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

    // Check the win condition for three diagonally
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

    // Checks all the win conditions
    public static boolean hasWon(char[][] board, char playerTicker) {
        if (threeAcross(board, playerTicker)) {
            return true;
        } else if (threeDown(board, playerTicker)) {
            return true;
        } else {
            return threeDiagonal(board, playerTicker);
        }
    }

    // Used during testing to see if the game had not been finished
    public static boolean checkOpenSpaces(char[][] board) {
        for (char[] row : board) {
            for (char col : row) {
                if (Character.valueOf(col).equals('_') || Character.valueOf(col).equals(' ')) {
                    return true;
                }
            }
        }
        return false;
    }

    // Used during testing to check if the game is in an impossible state
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

    // Returns the status of the game
    public static String checkGameStatus(char[][] board) {
        char x = 'X';
        char o = 'O';

        if (hasWon(board, x) && hasWon(board, o) || checkNumOfTickerDiff(board, x, o)) {
            return "Impossible";
        } else if (hasWon(board, x) && !hasWon(board, o)) {
            return "X wins";
        } else if (hasWon(board, o) && !hasWon(board, x)) {
            return "O wins";
        } else if (!hasWon(board, x) && !hasWon(board, o)) {
            if (checkOpenSpaces(board)) {
                return "Game not finished";
            } else {
                return "Draw";
            }
        } else {
            return "Unknown";
        }
    }

    // Adds the corresponding player ticker to the board and updates the game state
    public static void playerMove(char[][] board, char playerTicker) {
        int xInput;
        int yInput;
        try {
            System.out.print("Enter the coordinates: ");
            String[] playerInput = scanner.nextLine().split(" ");
            xInput = Integer.parseInt(playerInput[0]) - 1;
            yInput = Integer.parseInt(playerInput[1]) - 1;
            if (board[xInput][yInput] == 'X' || board[xInput][yInput] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                playerMove(board, playerTicker);
            } else {
                if (Character.valueOf(playerTicker).equals('X')) {
                    board[xInput][yInput] = 'X';
                    System.out.println();
                    displayBoard(board);
                }
                if (Character.valueOf(playerTicker).equals('O')) {
                    board[xInput][yInput] = 'O';
                    System.out.println();
                    displayBoard(board);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            playerMove(board, playerTicker);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Coordinates should be 1 to 3!");
            playerMove(board, playerTicker);
        }
    }

    public static void main(String[] args) {
        char playerTicker = 'X';

        displayBoard(boardData());
        while (true) {
            String result = checkGameStatus(board);
            if (result.equals("Impossible") || result.equals("Draw")
                    || result.equals("X wins") || result.equals("O wins")) {
                System.out.println(result);
                break;
            }
            playerMove(board, playerTicker);
            // Used to alternate turns on which player ticker is being placed
            if (Character.valueOf(playerTicker).equals('X')) {
                playerTicker = 'O';
            } else {
                playerTicker = 'X';
            }
        }
    }
}
