package com.example.tictactoe;

/**
 * This Cell class represents one cell of the Tic tac toe board.
 * It holds the players symbol and the coordinates to the cell on the .
 */

public class Cell {

    private char symbol;
    private Coordinates coordinates;

    /**
     * DVC of Cell is initialized with symbol with a blank board character '-'
     * and with coordinates set a new Coordinate with no parameters.
     */
    public Cell() {
        symbol = '-';
        coordinates = new Coordinates();
    }

    /**
     * EVC of Cell takes in input for the players symbol, the row and column, and creates a cell on the board
     * @param symbol: the player's character symbol ('X' or 'O')
     * @param row: row of the game tic tac toe board grid
     * @param column: column of the game tic tac toe board grid
     */
    public Cell(char symbol, int row, int column) {
        this.symbol = symbol;
        this.coordinates = new Coordinates(row, column);
    }

    /**
     * allows to set the symbol of the cell
     * @param symbol: the player's character symbol ('X' or 'O')
     */
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * getter for the symbol
     * @return symbol: the player's character symbol ('X' or 'O')
     */
    public char getSymbol() {
        return symbol;
    }
}
