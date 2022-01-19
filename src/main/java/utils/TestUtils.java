package utils;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import exceptions.InvalidCoordinateException;

import java.util.List;

public class TestUtils {

    //TODO: controllare se è la cosa migliore da fare
    public static Board setPiecesOnBoard(List<String> coordinateList, Pieces piece, Board myBoard) throws InvalidCoordinateException {
        for (String coordinateString: coordinateList) {
            myBoard.setPieceByCoordinate(new BoardCoordinate(coordinateString), piece);
        }
        return myBoard;
    }
}
