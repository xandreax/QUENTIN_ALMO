package gui;

import entities.Board;
import utils.IntUtils;

public class BoardShellPrinter {
    //FIELDS
    protected Board board;

    //CONSTRUCTORS
    public BoardShellPrinter(Board board) {
        this.board = board;
    }

    //METHODS
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void printOnStdOut(boolean withLegend) {
        System.out.println("");
        if (withLegend) {
            System.out.println(this.stampWithLegend());
        }
        else {
            System.out.println(this.stamp());
        }
    }

    private String stamp() {
        String result = "";
        result = result.concat(String.format("%24s%s%s%n", "-".repeat(19), " BLACK LINE ", "-".repeat(18)));
        for (int y = 0; y < 2*this.board.getDIMENSION() - 1; y++) {
            String borderString = IntUtils.mapBorderChar(y);
            if ((y % 2) == 0) {
                result = result.concat(String.format("%4s%2s", borderString, this.board.getMatrix()[0][y/2].getSymbol()));
                for (int x = 1; x < this.board.getDIMENSION(); x++) {
                    result = result.concat(String.format("%4s", " - "+this.board.getMatrix()[x][y/2].getSymbol()));
                }
                result = result.concat(String.format("%2s%n", borderString));
            }
            else {
                result = result.concat(String.format("%4s%2s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%2s%n", borderString, "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", borderString));
            }
        }
        result = result.concat(String.format("%24s%s%s%n%n", "-".repeat(19), " BLACK LINE ", "-".repeat(18)));
        return result;
    }

    private String stampWithLegend() {
        String result = "";
        result = result.concat(String.format("%7s%4s%4s%4s%4s%4s%4s%4s%4s%4s%5s%4s%4s%n%n", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
        result = result.concat(String.format("%25s%s%s%n", "-".repeat(19), " BLACK LINE ", "-".repeat(18)));
        for (int y = 0; y < 2*this.board.getDIMENSION() - 1; y++) {
            String borderString = IntUtils.mapBorderChar(y);
            String legendString = IntUtils.mapLegendChar(y);
            if ((y % 2) == 0) {
                result = result.concat(String.format("%s%4s%2s", legendString, borderString, this.board.getMatrix()[0][y/2].getSymbol()));
                for (int x = 1; x < this.board.getDIMENSION(); x++) {
                    result = result.concat(String.format("%4s", " - "+this.board.getMatrix()[x][y/2].getSymbol()));
                }
                result = result.concat(String.format("%2s%4s%n", borderString, legendString));
            }
            else {
                result = result.concat(String.format("%s%4s%2s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%2s%4s%n", legendString, borderString, "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", borderString, legendString));
            }
        }
        result = result.concat(String.format("%25s%s%s%n%n", "-".repeat(19), " BLACK LINE ", "-".repeat(18)));
        result = result.concat(String.format("%7s%4s%4s%4s%4s%4s%4s%4s%4s%4s%5s%4s%4s%n%n", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
        return result;
    }
}
