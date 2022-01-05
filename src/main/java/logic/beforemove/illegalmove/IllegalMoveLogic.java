package logic.beforemove.illegalmove;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;

public class IllegalMoveLogic {

    /**
     * This method checks for each diagonal corner if exists (points near the border doesn't have all the corner) and
     * if is occupied by a piece of the same colour of the player that doesn't share any adjacent and orthogonal
     * piece with the position of the move.
     *
     * @param cardinals
     * @param board
     * @param piece
     * @return true only if there is a piece of the same colour in a corner that doesn't share any adjacent and orthogonal
     *         piece with the position of the move.
     */
    public static boolean checkIfExistDiagonalIllegalPiece(CardinalCoordinates cardinals, Board board, Pieces piece) {
        if(cardinals.northEastCell != null && board.getPieceByCoordinate(cardinals.northEastCell).equals(piece)){
            if(checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.northEastCell, cardinals.northCell, board)
                    && checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.northEastCell, cardinals.eastCell, board))
                return true;
        }
        if(cardinals.northWestCell != null && board.getPieceByCoordinate(cardinals.northWestCell).equals(piece)){
            if(checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.northWestCell, cardinals.northCell, board)
                    && checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.northWestCell, cardinals.westCell, board))
                return true;
        }
        if(cardinals.southEastCell != null && board.getPieceByCoordinate(cardinals.southEastCell).equals(piece)){
            if(checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.southEastCell, cardinals.southCell, board)
                    && checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.southEastCell, cardinals.eastCell, board))
                return true;
        }
        if(cardinals.southWestCell != null && board.getPieceByCoordinate(cardinals.southWestCell).equals(piece)){
            if(checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.southWestCell, cardinals.southCell, board)
                    && checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.southWestCell, cardinals.westCell, board))
                return true;
        }
        return false;
    }

    /**
     * This method checks if the two points on the board aren't occupied by same piece.
     *
     * @param coordinate1
     * @param coordinate2
     * @param board
     * @return true if the two coordinates aren't occupied by same piece, false otherwise
     */
    private static boolean checkIfTwoPointsIsNotOccupiedBySamePiece(BoardCoordinate coordinate1, BoardCoordinate coordinate2, Board board) {
        return !board.getPieceByCoordinate(coordinate1).equals(board.getPieceByCoordinate(coordinate2));
    }
}
