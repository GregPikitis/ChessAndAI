package com.hbh7;

import static com.hbh7.Util.*;

public class Rook extends ChessPiece{

    private String pieceType = "Rook";
    private String owner;
    private int pointValue = 4;

    public Rook(String owner, String position) {
        super("Rook", owner, position);
        this.owner = owner;
    }

    public boolean checkValidMove_movePatternValidCheck(int playerNum, int originalRow, String originalColumn, int newRow, String newColumn, ChessPiece[][] boardArray) {

        // Check that the move pattern requested is valid
        // Ex: Rook can move forward, backwards, left, or right any number of spaces until it collides with the board
        // border or another piece.
        
        if(toArrayIndex(newRow) >= 0 && toArrayIndex(newRow) <= 7 && toArrayIndex(newColumn) >= 0 && toArrayIndex(newColumn) <= 7) {
            if((originalColumn.equals(newColumn)) ^ (originalRow == newRow)) { // bitwise XOR, must change only one to be true
                if(originalColumn.equals(newColumn)) {
                    System.out.println("Row is changing, piece is moving vertical");
                    // Row is changing, piece is moving vertical
                    if (toArrayIndex(originalRow) < toArrayIndex(newRow)) { // If moving downwards
                        System.out.println("Is moving downwards");
                        boolean error = false;
                        for(int i = toArrayIndex(originalRow)+1; i < toArrayIndex(newRow); i++) {
                            if(boardArray[i][toArrayIndex(originalColumn)] != null) {
                                error = true;
                            }
                        }
                        if (error) {
                            System.out.println("Error: Invalid Move. Piece(s) obstructing path.");
                            return false;
                        } else {
                            return true;
                        }
                    } else { // if moving upwards
                        System.out.println("Is moving upwards");
                        boolean error = false;
                        for(int i = toArrayIndex(originalRow)-1; i > toArrayIndex(newRow); i--) {
                            if(boardArray[i][toArrayIndex(originalColumn)] != null) {
                                error = true;
                            }
                        }
                        if (error) {
                            System.out.println("Error: Invalid Move. Piece(s) obstructing path.");
                            return false;
                        } else {
                            return true;
                        }
                    }

                } else {
                    // Column is changing, piece is moving horizontal
                    if (toArrayIndex(originalColumn) < toArrayIndex(newColumn)) { // If moving right
                        boolean error = false;
                        for(int i = toArrayIndex(originalColumn)+1; i < toArrayIndex(newColumn); i++) {
                            if(boardArray[toArrayIndex(originalRow)][i] != null) {
                                error = true;
                            }
                        }
                        if (error) {
                            System.out.println("Error: Invalid Move. Piece(s) obstructing path.");
                            return false;
                        } else {
                            return true;
                        }
                    } else { // if moving left
                        boolean error = false;
                        for (int i = toArrayIndex(originalColumn) - 1; i > toArrayIndex(newColumn); i--) {
                            if (boardArray[toArrayIndex(originalRow)][i] != null) {
                                error = true;
                            }
                        }
                        if (error) {
                            System.out.println("Error: Invalid Move. Piece(s) obstructing path.");
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            } else {
                System.out.println("Error: Invalid Move. Cannot move across 2 axis at once.");
                return false;
            }
        } else {
            System.out.println("Error: Invalid Move. Piece(s) outside of game board.");
            return false;
        }
    }
}
