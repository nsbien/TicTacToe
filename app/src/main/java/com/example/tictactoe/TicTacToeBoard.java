package com.example.tictactoe;

/**
 * This TicTacToeBoard class represents one tic tac toe board.
 * It has dimensions set by the user and is updated throughout the game.
 */

public class TicTacToeBoard {

    private Cell[][] grid;
    private int N = 3;

    /**
     * DVC for TicTacToeBoard is initialized with a grid of size N (set originally to 3) of Cells
     */
    public TicTacToeBoard() {
        grid = new Cell[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = new Cell('-', i, j);
            }
        }
    }

    /**
     * replaces a cell on the tic tac toe game board with the player symbol
     * @param coordinates: the row and column being accessed on the tic tac toe board
     * @param playerSymbol: the player's character symbol ('X' or 'O')
     */
    public void makeMove(Coordinates coordinates, char playerSymbol) {
        grid[coordinates.getX()][coordinates.getY()].setSymbol(playerSymbol);
    }

    /**
     * checks to see if there is a winner for the game
     * @param playerSymbol: the player's character symbol ('X' or 'O')
     * @return true if there is a winner, false if not
     */
    public boolean isWinner(char playerSymbol) {
        if (isHorizontal(playerSymbol) || isVertical(playerSymbol) || isDiagonalDown(playerSymbol) || isDiagonalUp(playerSymbol)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks to see if a player has won by horizontally getting (N) in a row
     * @param playerSymbol: the player's character symbol ('X' or 'O')
     * @return true if there is a winner, false if not
     */
    public boolean isHorizontal(char playerSymbol) {
        int keepCount = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (grid[i][j].getSymbol() == (playerSymbol)) {
                    keepCount += 1;
                }
                if (keepCount == N) {
                    return true;
                }
            }
            keepCount = 0;
        }
        return false;
    }

    /**
     * checks to see if a player has won by vertically getting (N) in a row
     * @param playerSymbol: the player's character symbol ('X' or 'O')
     * @return true if there is a winner, false if not
     */
    public boolean isVertical(char playerSymbol) {
        int keepCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[j][i].getSymbol() == (playerSymbol)) {
                    keepCount += 1;
                }
                if (keepCount == N) {
                    return true;
                }
            }
            keepCount = 0;
        }
        return false;
    }

    /**
     * checks to see if a player has won by diagonally down, left to right, getting (N) in a row
     * @param playerSymbol: the player's character symbol ('X' or 'O')
     * @return true if there is a winner, false if not
     */
    public boolean isDiagonalDown(char playerSymbol) {
        int keepCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    if (grid[i][j].getSymbol() == (playerSymbol)) {
                        keepCount += 1;
                    }
                }
            }
        }
        if (keepCount == N) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks to see if a player has won by diagonally up, left to right, getting (N) in a row
     * @param playerSymbol: the player's character symbol ('X' or 'O')
     * @return true if there is a winner, false if not
     */
    public boolean isDiagonalUp(char playerSymbol) {
        int keepCount = 0;
        int j = 0;

        for (int i = N - 1; i >= 0; i--) {
            if (grid[i][j].getSymbol() == (playerSymbol)) {
                keepCount += 1;
            }
            j++;
        }
        if (keepCount == N) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns true or false if there was a scratch game (game of cats)
     * @return true if no one one and the game board is full, false if the board is not full yet
     */
    public boolean determineCatScratch() {
        int keepCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (grid[i][j].getSymbol() != '-') {
                    keepCount += 1;
                }
            }
        }
        if (keepCount == N*N) {
            return true;
        } else {
            return false;
        }
    }
}