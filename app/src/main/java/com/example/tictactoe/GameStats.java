package com.example.tictactoe;

/**
 *  This GameStats class keeps track of each player's number of wins, losses, and scratch games played
 */

public class GameStats {

    private char player;
    private int wins;
    private  int losses;
    private int totalGames;
    private int scratchCatGames;

    /**
     * DVC for GameStats. All int variables initialized to 0 and symbol for player initialized to be blank, '-'
     */
    public GameStats() {
        player = '-';
        wins = 0;
        losses = 0;
        scratchCatGames = 0;
        totalGames = 0;

    }

    /**
     * EVC for GameStats. Takes in input for playerSymbol based on who's stats we are tracking
     * @param playerSymbol: the player's character symbol ('X' or 'O')
     */
    public GameStats(char playerSymbol) {
        player = playerSymbol;
        wins = 0;
        losses = 0;
        scratchCatGames = 0;
        totalGames = 0;

    }

    /**
     * adds a win, increases total games tracked
     */
    public void addWin() {
        wins += 1;
        totalGames += 1;
    }

    /**
     * adds a loss, increases total games tracked
     */
    public void addLoss() {
        losses += 1;
        totalGames += 1;
    }

    /**
     * adds a scratch games, increases total games tracked
     */
    public void addScratch() {
        scratchCatGames += 1;
        totalGames += 1;
    }

    /**
     * Overrides the totString and outputs the statistics
     * @return string of players statistics
     */
    public String printMe() {
        String stats = "\n";
        stats += "-------------------\n";
        stats += "Win to loss ratio: " + wins + ":" + losses +"\n";
        stats += "Win percentage: " + ((Double.valueOf(wins)/totalGames)) + "%\n";
        stats += "Number of scratch games: " + scratchCatGames + "\n";
        return stats;
    }

}

