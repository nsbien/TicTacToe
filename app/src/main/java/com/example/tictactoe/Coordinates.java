package com.example.tictactoe;

/**
 * This Coordinates class represents the coordinates to the cell, on the tic tac toe board.
 */

public class Coordinates {

    private int row;
    private int column;

    /**
     * DVC for Coordinates is initialized with a row and column of 0, 0
     */
    public Coordinates() {
        row = 0;
        column = 0;
    }

    /**
     * EVC for Coordinates takes input for row and column, sets the private variables in the class to those passed in
     * @param row: row of the game tic tac toe board grid
     * @param column: column of the game tic tac toe board grid
     */
    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * getter for the variable row
     * @return row of the game tic tac toe board grid
     */
    public int getX() {
        return row;
    }

    /**
     * getter for the variable column
     * @return column of the game tic tac toe board grid
     */
    public int getY() {
        return column;
    }
}
