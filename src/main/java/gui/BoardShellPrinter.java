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
        String interRowStamp = "|   |   |   |   |   |   |   |   |   |   |   |   |";
        result = result.concat("  ------------------- BLACK LINE ------------------\n");
        for (int i = 0; i < this.board.getDIMENSION(); i++) {
            if (i == 3) result = result.concat("W ");
            else if (i == 4) result = result.concat("I ");
            else if (i == 5) result = result.concat("E ");
            else if (i == 7) result = result.concat("L ");
            else if (i == 8) result = result.concat("N ");
            else result = result.concat("| ");
            for (int j = 0; j < this.board.getDIMENSION(); j++) {
                if (j == this.board.getDIMENSION() - 1) {
                    result = result.concat(this.board.getMatrix()[i][j].getSymbol());
                }
                else {
                    result = result.concat(this.board.getMatrix()[i][j].getSymbol() + " - ");
                }
            }
            if (i == 3) result = result.concat(" W\n");
            else if (i == 4) result = result.concat(" I\n");
            else if (i == 5) result = result.concat(" E\n");
            else if (i == 7) result = result.concat(" L\n");
            else if (i == 8) result = result.concat(" N\n");
            else result = result.concat(" |\n");
            if (i != this.board.getDIMENSION() - 1) {
                if (i == 3) result = result.concat("H ");
                else if (i == 4) result = result.concat("T ");
                else if (i == 7) result = result.concat("I ");
                else if (i == 8) result = result.concat("E ");
                else result = result.concat("| ");
                result = result.concat(interRowStamp);
                if (i == 3) result = result.concat(" H\n");
                else if (i == 4) result = result.concat(" T\n");
                else if (i == 7) result = result.concat(" I\n");
                else if (i == 8) result = result.concat(" E\n");
                else result = result.concat(" |\n");
            }
        }
        result = result.concat("  ------------------- BLACK LINE ------------------\n\n");
        return result;
    }

    private String stampWithLegend() {
        String result = "";
        String interRowStamp = "|   |   |   |   |   |   |   |   |   |   |   |   |";
        result = result.concat("      0   1   2   3   4   5   6   7   8   9   10  11  12\n\n");
        result = result.concat("      ------------------- BLACK LINE ------------------\n");
        for (int i = 0; i < this.board.getDIMENSION(); i++) {
            //left indexes and white line
            if (i == 3) result = result.concat(IntUtils.mapInputIndexToInputChar(i) + "   W ");
            else if (i == 4) result = result.concat(IntUtils.mapInputIndexToInputChar(i) + "   I ");
            else if (i == 5) result = result.concat(IntUtils.mapInputIndexToInputChar(i) + "   E ");
            else if (i == 7) result = result.concat(IntUtils.mapInputIndexToInputChar(i) + "   L ");
            else if (i == 8) result = result.concat(IntUtils.mapInputIndexToInputChar(i) + "   N ");
            else result = result.concat(IntUtils.mapInputIndexToInputChar(i) + "   | ");
            //board's matrix
            for (int j = 0; j < this.board.getDIMENSION(); j++) {
                if (j == this.board.getDIMENSION() - 1) {
                    result = result.concat(this.board.getMatrix()[i][j].getSymbol());
                }
                else {
                    result = result.concat(this.board.getMatrix()[i][j].getSymbol() + " - ");
                }
            }
            //white line and right indexes
            if (i == 3) result = result.concat(" W    " + IntUtils.mapInputIndexToInputChar(i) + "\n");
            else if (i == 4) result = result.concat(" I    " + IntUtils.mapInputIndexToInputChar(i) + "\n");
            else if (i == 5) result = result.concat(" E    " + IntUtils.mapInputIndexToInputChar(i) + "\n");
            else if (i == 7) result = result.concat(" L    " + IntUtils.mapInputIndexToInputChar(i) + "\n");
            else if (i == 8) result = result.concat(" N    " + IntUtils.mapInputIndexToInputChar(i) + "\n");
            else result = result.concat(" |    " + IntUtils.mapInputIndexToInputChar(i) +"\n");
            if (i != this.board.getDIMENSION() - 1) {
                if (i == 3) result = result.concat("    H ");
                else if (i == 4) result = result.concat("    T ");
                else if (i == 7) result = result.concat("    I ");
                else if (i == 8) result = result.concat("    E ");
                else result = result.concat("    | ");
                result = result.concat(interRowStamp);
                if (i == 3) result = result.concat(" H\n");
                else if (i == 4) result = result.concat(" T\n");
                else if (i == 7) result = result.concat(" I\n");
                else if (i == 8) result = result.concat(" E\n");
                else result = result.concat(" |\n");
            }
        }
        result = result.concat("      ------------------- BLACK LINE ------------------\n\n");
        result = result.concat("      0   1   2   3   4   5   6   7   8   9   10  11  12\n");
        return result;
    }
}
