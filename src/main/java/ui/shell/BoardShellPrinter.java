package ui.shell;

import entities.Board;
import entities.BoardCoordinate;
import exceptions.InvalidCoordinateException;
import utils.IntUtils;

public class BoardShellPrinter {
    //FIELDS
    private Board board;

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

    public void printOnStdOut() {
        System.out.println();
        try {
            System.out.println(this.stampWithLegend());
        } catch (InvalidCoordinateException ignored) {
        }
    }

    private String stampWithLegend() throws InvalidCoordinateException {
        String result = "";
        result = result.concat(String.format("%7s%4s%4s%4s%4s%4s%4s%4s%4s%4s%5s%4s%4s%n%n", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
        result = result.concat(String.format("%25s%s%s%n", "-".repeat(19), " BLACK LINE ", "-".repeat(18)));
        for (int r = 0; r < 2 * this.board.getDIMENSION() - 1; r++) {
            String borderString = IntUtils.mapBorderChar(r);
            String legendString = IntUtils.mapLegendChar(r);
            if ((r % 2) == 0) {
                BoardCoordinate bc = new BoardCoordinate(r / 2, 0);
                result = result.concat(String.format("%s%4s%2s", legendString, borderString, this.board.getPieceByCoordinate(bc).getSymbol()));
                for (int c = 1; c < this.board.getDIMENSION(); c++) {
                    bc = new BoardCoordinate(r / 2, c);
                    result = result.concat(String.format("%4s", " - " + this.board.getPieceByCoordinate(bc).getSymbol()));
                }
                result = result.concat(String.format("%2s%4s%n", borderString, legendString));
            } else {
                result = result.concat(String.format("%s%4s%2s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%2s%4s%n", legendString, borderString, "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", borderString, legendString));
            }
        }
        result = result.concat(String.format("%25s%s%s%n%n", "-".repeat(19), " BLACK LINE ", "-".repeat(18)));
        result = result.concat(String.format("%7s%4s%4s%4s%4s%4s%4s%4s%4s%4s%5s%4s%4s%n%n", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
        return result;
    }
}
