package logic.aftermove.victory;

import entities.Board;
import entities.Pieces;
import entities.Player;
import exceptions.VictoryException;

import java.util.Arrays;

public class VictoryCondition {

    public VictoryCondition(){}

    /**
     * This method is used to check if the game has come to a conclusion.
     * If the victory condition is satisfied, an exception will be thrown
     * and the game will end.
     * If the board is full and no chain exists, then the player with the
     * major number of pieces will win the game.
     *
     * @param board
     * @param player
     * @throws VictoryException
     */
    public void checkVictoryCondition(Board board, Player player) throws VictoryException{
        int dimension = board.getDIMENSION();
        int[][] matrixTemp = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++)
            Arrays.fill(matrixTemp[i], -1);
        if (hasPath(board.getMatrix(), matrixTemp, player.getPieces())) {
            throw new VictoryException(player.getPieces());
        }
        int blacks = 0, whites = 0;
        for(int i = 0; i< board.getDIMENSION(); i++) {
            for(int j = 0; j< board.getDIMENSION(); j++) {
                if (board.getMatrix()[i][j].equals(Pieces.BLACK)) blacks++;
                else if (board.getMatrix()[i][j].equals(Pieces.WHITE)) whites++;
            }
        }
        if (blacks+whites == board.getDIMENSION()* board.getDIMENSION()){
            if (blacks > whites) throw new VictoryException(Pieces.BLACK);
            else throw new VictoryException(Pieces.WHITE);
        }
    }

    /**
     * This method checks for each player if the victory condition has been reached.
     *
     * @param matrix
     * @param matrixTemp
     * @param playerColor
     * @return true if the matrix has a path from side to side, false otherwise
     */
    private boolean hasPath(Pieces[][] matrix, int[][] matrixTemp, Pieces playerColor) {
        if (playerColor.equals(Pieces.WHITE)) {
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


    /**
     * Recursive algorithm Deep First Search to find possible path in a matrix
     * //https://stackoverflow.com/questions/20708659/find-if-path-exists-in-matrix
     *
     *
     *
     * @param matrix
     * @param matrixTemp
     * @param i
     * @param j
     * @param playerColor
     * @return true if the matrix has a path of pieces from side to side, false otherwise
     */
    private boolean hasPathHelper(Pieces[][] matrix, int[][] matrixTemp, int i, int j, Pieces playerColor) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrixTemp[i][j] >= 0)
            return false;
        matrixTemp[i][j] = 0;
        if (!matrix[i][j].equals(playerColor))
            return false;
        if (playerColor.equals(Pieces.WHITE)) {
            if (j == matrix[0].length - 1 ||               // Right side reached!
                    hasPathHelper(matrix, matrixTemp, i + 1, j, playerColor) ||  // Check down
                    hasPathHelper(matrix, matrixTemp, i - 1, j, playerColor) ||  // Check up
                    hasPathHelper(matrix, matrixTemp, i, j + 1, playerColor) ||  // Check right
                    hasPathHelper(matrix, matrixTemp, i, j - 1, playerColor)     // Check left
            ) matrixTemp[i][j] = 1;                           // Mark as good path
        } else {
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
