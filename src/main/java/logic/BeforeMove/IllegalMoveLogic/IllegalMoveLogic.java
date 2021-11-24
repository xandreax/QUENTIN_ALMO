package logic.BeforeMove.IllegalMoveLogic;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;

public class IllegalMoveLogic {

    public static boolean checkIfExistDiagonalIllegalPiece(CardinalCoordinates cardinals, Board board, Pieces piece) {
        if(cardinals.northEastCell != null && board.getPieceByCoordinate(cardinals.northEastCell).equals(piece)){
            return checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.northEastCell, cardinals.northCell, board)
                    && checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.northEastCell, cardinals.eastCell, board);
        }
        if(cardinals.northWestCell != null && board.getPieceByCoordinate(cardinals.northWestCell).equals(piece)){
            return checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.northWestCell, cardinals.northCell, board)
                    && checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.northWestCell, cardinals.westCell, board);
        }
        if(cardinals.southEastCell != null && board.getPieceByCoordinate(cardinals.southEastCell).equals(piece)){
            return checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.southEastCell, cardinals.southCell, board)
                    && checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.southEastCell, cardinals.eastCell, board);
        }
        if(cardinals.southWestCell != null && board.getPieceByCoordinate(cardinals.southWestCell).equals(piece)){
            return checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.southWestCell, cardinals.southCell, board)
                    && checkIfTwoPointsIsNotOccupiedBySamePiece(cardinals.southWestCell, cardinals.westCell, board);
        }
        return false;
    }


    private static boolean checkIfTwoPointsIsNotOccupiedBySamePiece(BoardCoordinate coordinate1, BoardCoordinate coordinate2, Board board){
        return !board.getPieceByCoordinate(coordinate1).equals(board.getPieceByCoordinate(coordinate2));
    }
}
