package org.example.Lab11.Helpers;

import java.io.Serializable;

public class BoardData implements Serializable {
    private int boardSize;
    private int idPlayer1;
    private int idPlayer2;
    private int winner;
    private long timePlayer1;
    private long timePlayer2;
    private int[][] board = new int[boardSize][boardSize];

    public BoardData() {
    }

    public void setBoardSize(int bs) {
        boardSize = bs;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getIdPlayer1() {
        return idPlayer1;
    }

    public void setIdPlayer1(int idPlayer1) {
        this.idPlayer1 = idPlayer1;
    }

    public int getIdPlayer2() {
        return idPlayer2;
    }

    public void setIdPlayer2(int idPlayer2) {
        this.idPlayer2 = idPlayer2;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public long getTimePlayer1() {
        return timePlayer1;
    }

    public void setTimePlayer1(long timePlayer1) {
        this.timePlayer1 = timePlayer1;
    }

    public long getTimePlayer2() {
        return timePlayer2;
    }

    public void setTimePlayer2(long timePlayer2) {
        this.timePlayer2 = timePlayer2;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
