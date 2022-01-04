package logic.aftermove.territories;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import exceptions.InvalidCoordinateException;

import java.util.LinkedList;

import static logic.aftermove.territories.TerritoriesLogic.isNotEdge;

public class TerritorySearch {

    /**
     * This method finds the next node which is not occupied by any piece
     *
     * @param emptyNode : the last empty node found
     * @param territory : the current possible territory
     * @return the first empty board coordinate found adjacent to the last empty node, null if none are found
     * @throws InvalidCoordinateException
     */
    public static BoardCoordinate findNextEmptyNode(BoardCoordinate emptyNode, LinkedList<BoardCoordinate> territory, Board board) throws InvalidCoordinateException {
        BoardCoordinate nextEmpty = new BoardCoordinate();
        int row = emptyNode.getRow();
        int col = emptyNode.getColumn();
        //Checks to the right
        if (isNotEdge(col + 1, board.getDIMENSION())) {
            if (board.getMatrix()[row][col + 1].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row, col+1))) {
                nextEmpty.setRow(row);
                nextEmpty.setColumn(col + 1);
                return nextEmpty;
            }
        }
        //Checks to the left
        if (isNotEdge(col - 1, board.getDIMENSION())) {
            if (board.getMatrix()[row][col - 1].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row, col-1))) {
                nextEmpty.setRow(row);
                nextEmpty.setColumn(col - 1);
                return nextEmpty;
            }
        }

        //Checks down
        if (isNotEdge(row + 1, board.getDIMENSION())) {
            if (board.getMatrix()[row + 1][col].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row + 1, col))) {
                nextEmpty.setRow(row + 1);
                nextEmpty.setColumn(col);
                return nextEmpty;
            }
        }
        //Checks up
        if (isNotEdge(row - 1, board.getDIMENSION())) {
            if (board.getMatrix()[row - 1][col].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row -1, col))) {
                nextEmpty.setRow(row - 1);
                nextEmpty.setColumn(col);
                return nextEmpty;
            }
        }
        return null;
    }

    /**
     * This method checks if a coordinate has at least two adjacent pieces
     *
     * @param coordinate
     * @return true if the coordinate has at least two adjacent pieces, false otherwise
     */
    public static boolean hasAtLeastTwoAdjacentPieces(BoardCoordinate coordinate, Board board) {
        boolean down, up, right, left;
        int row = coordinate.getRow();
        int col = coordinate.getColumn();
        //First checks the limits of the boards, then if row and col are not edges checks all 4 directions
        if (row == 0 && col == 0){
            down = !board.getMatrix()[row + 1][col].equals(Pieces.NONE);
            right = !board.getMatrix()[row][col + 1].equals(Pieces.NONE);
            return down && right;
        }
        if (row == board.getDIMENSION()-1 && col == 0){
            up = !board.getMatrix()[row - 1][col].equals(Pieces.NONE);
            right = !board.getMatrix()[row][col + 1].equals(Pieces.NONE);
            return up && right;
        }
        if (col == board.getDIMENSION()-1 && row == 0){
            down = !board.getMatrix()[row + 1][col].equals(Pieces.NONE);
            left = !board.getMatrix()[row][col - 1].equals(Pieces.NONE);
            return left && down;
        }
        if (row == board.getDIMENSION()-1 && col == board.getDIMENSION() -1){
            up = !board.getMatrix()[row - 1][col].equals(Pieces.NONE);
            left = !board.getMatrix()[row][col - 1].equals(Pieces.NONE);
            return up && left;
        }
        if (row == board.getDIMENSION() - 1) {
            up = !board.getMatrix()[row - 1][col].equals(Pieces.NONE);
            right = !board.getMatrix()[row][col + 1].equals(Pieces.NONE);
            left = !board.getMatrix()[row][col - 1].equals(Pieces.NONE);
            return (up && right) || (up && left) || (right && left);
        }
        if (row == 0) {
            down = !board.getMatrix()[row + 1][col].equals(Pieces.NONE);
            right = !board.getMatrix()[row][col + 1].equals(Pieces.NONE);
            left = !board.getMatrix()[row][col - 1].equals(Pieces.NONE);
            return (down && right) || (down && left) || (right && left);
        }
        if (col == 0) {
            down = !board.getMatrix()[row + 1][col].equals(Pieces.NONE);
            up = !board.getMatrix()[row - 1][col].equals(Pieces.NONE);
            right = !board.getMatrix()[row][col + 1].equals(Pieces.NONE);
            return (down && up) || (down && right) || (right && up);
        }
        if (col == board.getDIMENSION() - 1) {
            down = !board.getMatrix()[row + 1][col].equals(Pieces.NONE);
            up = !board.getMatrix()[row - 1][col].equals(Pieces.NONE);
            left = !board.getMatrix()[row][col - 1].equals(Pieces.NONE);
            return (down && up) || (down && left) || (left && up);
        }

        down = !board.getMatrix()[row + 1][col].equals(Pieces.NONE);
        up = !board.getMatrix()[row - 1][col].equals(Pieces.NONE);
        right = !board.getMatrix()[row][col + 1].equals(Pieces.NONE);
        left = !board.getMatrix()[row][col - 1].equals(Pieces.NONE);
        return (down && up) || (down && right) || (down && left) || (up && right) || (left && up) || (left && right);
    }
}
