package logic;

import entities.Board;
import entities.Pieces;

import java.util.Arrays;

public class VictoryExplorer {

    public VictoryExplorer() {
    }

    /**
     * This method is used to check if the game has come to a conclusion.
     * If the victory condition is satisfied, then the method return the colour
     * of the winner.
     * If the board is full and no chain exists, then the player with the
     * major number of pieces will win the game.
     * If the victory conditions are not satisfied, method return Pieces.NONE
     *
     * @param board: the board
     */
    public Pieces getPieceWinner(Board board) {
        if (board.isFull()) {
            int blacks = 0, whites = 0;
            for (int i = 0; i < board.getDIMENSION(); i++) {
                for (int j = 0; j < board.getDIMENSION(); j++) {
                    if (board.getMatrix()[i][j].equals(Pieces.BLACK)) blacks++;
                    else if (board.getMatrix()[i][j].equals(Pieces.WHITE)) whites++;
                }
            }
            if (blacks + whites == board.getDIMENSION() * board.getDIMENSION()) {
                if (blacks > whites) return Pieces.BLACK;
                else return Pieces.WHITE;
            }
        }
        int dimension = board.getDIMENSION();
        int[][] matrixTemp = new int[dimension][dimension];
        return getPieceWinnerIfExistPath(board.getMatrix(), matrixTemp);
    }

    /**
     * This method checks for each player if the victory condition has been reached.
     *
     * @param matrix: matrix
     * @param matrixTemp: temporary matrix
     * @return true if the matrix has a path from side to side, false otherwise
     */
    private Pieces getPieceWinnerIfExistPath(Pieces[][] matrix, int[][] matrixTemp) {
        for (int i = 0; i < matrixTemp[0].length; i++)
            Arrays.fill(matrixTemp[i], -1);
        for (int i = 0; i < matrix.length; i++) {
            if (existPathHelper(matrix, matrixTemp, i, 0, Pieces.WHITE))
                return Pieces.WHITE;
        }
        for (int i = 0; i < matrixTemp[0].length; i++)
            Arrays.fill(matrixTemp[i], -1);
        for (int j = 0; j < matrix[0].length; j++) {
            if (existPathHelper(matrix, matrixTemp, 0, j, Pieces.BLACK))
                return Pieces.BLACK;
        }
        return Pieces.NONE;
    }


    /**
     * Recursive algorithm Deep First Search to find possible path in a matrix
     * //https://stackoverflow.com/questions/20708659/find-if-path-exists-in-matrix
     *
     * @param matrix: matrix
     * @param matrixTemp: temporary matrix
     * @param i: index x axis
     * @param j: index y axis
     * @param playerColor: colour of the player
     * @return true if the matrix has a path of pieces from side to side, false otherwise
     */
    private boolean existPathHelper(Pieces[][] matrix, int[][] matrixTemp, int i, int j, Pieces playerColor) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrixTemp[i][j] >= 0)
            return false;
        matrixTemp[i][j] = 0;
        if (!matrix[i][j].equals(playerColor))
            return false;
        if (playerColor.equals(Pieces.WHITE)) {
            if (j == matrix[0].length - 1 ||               // Right side reached!
                    existPathHelper(matrix, matrixTemp, i + 1, j, playerColor) ||  // Check down
                    existPathHelper(matrix, matrixTemp, i - 1, j, playerColor) ||  // Check up
                    existPathHelper(matrix, matrixTemp, i, j + 1, playerColor) ||  // Check right
                    existPathHelper(matrix, matrixTemp, i, j - 1, playerColor)     // Check left
            ) matrixTemp[i][j] = 1;                           // Mark as good path
        } else {
            if (i == matrix[0].length - 1 ||               // bottom side reached!
                    existPathHelper(matrix, matrixTemp, i + 1, j, playerColor) ||  // Check down
                    existPathHelper(matrix, matrixTemp, i - 1, j, playerColor) ||  // Check up
                    existPathHelper(matrix, matrixTemp, i, j + 1, playerColor) ||  // Check right
                    existPathHelper(matrix, matrixTemp, i, j - 1, playerColor)     // Check left
            ) matrixTemp[i][j] = 1;                           // Mark as good path
        }
        return matrixTemp[i][j] == 1;
    }
}
