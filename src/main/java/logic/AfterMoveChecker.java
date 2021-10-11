package logic;

import entities.Board;
import entities.Pieces;
import entities.Player;
import exceptions.VictoryException;

import java.util.Arrays;

public class AfterMoveChecker {
    protected Board board;
    protected Player player;

    public AfterMoveChecker(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    public void checkBoardAfterMove() throws VictoryException {
        // TODO: check territories

        checkVictoryCondition();
    }

    //TODO: check Territories -> return a board
    // sarà da cambiar il void in board in checkBoardAfterMove e poi in game sostituir la matrice

    //check Victory condition -> return exception
    private void checkVictoryCondition() throws VictoryException {
        int dimension = board.getDIMENSION();
        int[][] matrixTemp = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++)
            Arrays.fill(matrixTemp[i], -1);
        if (hasPath(board.getMatrix(), matrixTemp, player.getPieces())) {
            throw new VictoryException("Victory! " + player.getPieces().getName() + " wins the game");
        }
    }

    private static boolean hasPath(Pieces[][] matrix, int[][] matrixTemp, Pieces playerColor) {
        if (playerColor.equals(Pieces.WHITE) /* cambiare condizioni con pie rule*/) {
            for (int i = 0; i < matrix.length; i++) {
                if (hasPathHelper(matrix, matrixTemp, i, 0, playerColor))
                    return true;
            }
        } else {
            for (int j = 0; j < matrix[0].length; j++) {
                if (hasPathHelper(matrix, matrixTemp, 0, j, playerColor))
                    return true;
            }
        }
        return false;
    }

    //algoritmo Deep First Search per cercare possibili path
    //https://stackoverflow.com/questions/20708659/find-if-path-exists-in-matrix
    private static boolean hasPathHelper(Pieces[][] matrix, int[][] matrixTemp, int i, int j, Pieces playerColor) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrixTemp[i][j] >= 0)
            return false;
        matrixTemp[i][j] = 0;
        if (!matrix[i][j].equals(playerColor))
            return false;
        if(playerColor.equals(Pieces.WHITE)) {
            if (j == matrix[0].length - 1 ||               // Right side reached!
                    hasPathHelper(matrix, matrixTemp, i + 1, j, playerColor) ||  // Check down
                    hasPathHelper(matrix, matrixTemp, i - 1, j, playerColor) ||  // Check up
                    hasPathHelper(matrix, matrixTemp, i, j + 1, playerColor) ||  // Check right
                    hasPathHelper(matrix, matrixTemp, i, j - 1, playerColor)     // Check left
            ) matrixTemp[i][j] = 1;                           // Mark as good path
        }else {
            if (i == matrix[0].length - 1 ||               // bottom side reached!
                    hasPathHelper(matrix, matrixTemp, i + 1, j, playerColor) ||  // Check down
                    hasPathHelper(matrix, matrixTemp, i - 1, j, playerColor) ||  // Check up
                    hasPathHelper(matrix, matrixTemp, i, j + 1, playerColor) ||  // Check right
                    hasPathHelper(matrix, matrixTemp, i, j - 1, playerColor)     // Check left
            ) matrixTemp[i][j] = 1;                           // Mark as good path
        }
        return matrixTemp[i][j] == 1;
    }

}
