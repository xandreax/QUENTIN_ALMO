package logic;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import entities.Player;
import exceptions.InvalidCoordinateException;
import exceptions.VictoryException;

import java.util.*;

public class AfterMoveChecker {
    protected Board board;
    protected Player player;
    private List<BoardCoordinate> countedPieces = new ArrayList<>();

    public AfterMoveChecker(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    /**
     * This method calls 2 other methods: one checks if new territories have been formed,
     * the other one if the victory condition has been satisfied.
     *
     * @return the updated board if a new territory has been found, the current board otherwise
     * @throws VictoryException
     * @throws InvalidCoordinateException
     */
    public Board checkAndUpdateBoardAfterMove() throws VictoryException, InvalidCoordinateException {
        Board boardChecked = board;
        //Se ci sono state almeno 3 mosse ha senso controllare i territori
        if (board.getMovesHistory().size() > 2) {
            boardChecked = checkTerritories();
        }
        checkVictoryCondition();
        return boardChecked;
    }

    /**
     * This method checks if new territories has been formed after a move has been made.
     * The algorithm works this way: if an empty node has at least two adjacent pieces,
     * this node is provisionally saved in a list, then the algorithm checks again for a
     * node of the same type. If it finds a closed region, meaning that the node borders
     * only with edges or pieces, then it starts again from the position initially saved.
     * If here it finds another closed region, then it is a territory and gets called the
     * method to fill the territory with the right type of pieces.
     *
     * @return the updated board if a new territory has been found, the current board otherwise
     * @throws InvalidCoordinateException
     */
    private Board checkTerritories() throws InvalidCoordinateException {
        boolean allNodesVisited = false;
        //List that resets every time that finds a new territory
        LinkedList<BoardCoordinate> possibleTerritory = new LinkedList<>();
        int[][] visitedMatrix = new int[board.getDIMENSION()][board.getDIMENSION()];
        Pieces[][] matrixPieces = board.getMatrix();
        int row = 0, col = 0;
        boolean hasPositionBeenSaved = false;
        //This loop exits when all nodes in the board have been visited
        while (!allNodesVisited) {
            BoardCoordinate bc = checkEdgesOfBoard(row, col);
            row = bc.getRow();
            col = bc.getColumn();
            //This loop is used to skip nodes that has been already visited
            while (visitedMatrix[row][col] == 1) {
                col++; //next element of row
                BoardCoordinate boardCoordinate = checkEdgesOfBoard(row, col);
                row = boardCoordinate.getRow();
                col = boardCoordinate.getColumn();
            }
            BoardCoordinate coordinate = new BoardCoordinate(row, col);
            //Checks if the current coordinate is empty and has at least 2 adjacent pieces
            if (matrixPieces[row][col].equals(Pieces.NONE) && hasAtLeastTwoAdjacentPieces(coordinate)) {
                possibleTerritory.add(coordinate);
                visitedMatrix[row][col] = 1;
                BoardCoordinate savedCoordinate = new BoardCoordinate();
                //Save the position for the first node found
                if (!hasPositionBeenSaved) {
                    savedCoordinate = new BoardCoordinate(row, col);
                    hasPositionBeenSaved = true;
                }
                BoardCoordinate emptyNode = findNextEmptyNode(possibleTerritory.getLast(), possibleTerritory);
                //This loop is used to find the next empty node
                while (emptyNode != null){
                    if(hasAtLeastTwoAdjacentPieces(emptyNode)){
                        possibleTerritory.add(emptyNode); //Add the node to the list
                        emptyNode = findNextEmptyNode(emptyNode, possibleTerritory);
                        if (emptyNode == null){
                            //If the region is closed start again from the saved position
                            emptyNode = findNextEmptyNode(savedCoordinate, possibleTerritory);
                        }
                    }
                    else{
                        //clear the list since it was not a territory
                        possibleTerritory.clear();
                        hasPositionBeenSaved = false;
                        break;
                    }
                }

                if (emptyNode == null){
                    //Territory found
                    board = updateBoard(possibleTerritory);
                    row = savedCoordinate.getRow();
                    col = savedCoordinate.getColumn() + 1;
                    hasPositionBeenSaved = false;
                    possibleTerritory.clear();
                }
                else{
                    //Set the node as visited, go to next position and reset the saved position
                    visitedMatrix[emptyNode.getRow()][emptyNode.getColumn()] = 1;
                    row = savedCoordinate.getRow();
                    col = savedCoordinate.getColumn() + 1;
                    hasPositionBeenSaved = false;
                }

            }
            //Go to next node
            else {
                visitedMatrix[row][col] = 1;
                col++; //next element of row
            }
            //checks if all nodes has been visited
            allNodesVisited = hasAllNodesBeenVisited(visitedMatrix);
        }
        return  board;
    }

    /**
     * This method checks if the coordinates are in range of the board dimension
     *
     * @param row
     * @param col
     * @return the coordinate in the right range if the indexes are out of bounds, the old coordinates otherwise
     * @throws InvalidCoordinateException
     */
    private BoardCoordinate checkEdgesOfBoard(int row, int col) throws InvalidCoordinateException {
        if (col >= board.getDIMENSION()) {
            row++;
            col = 0;
            if (row >= board.getDIMENSION()){
                row = 0;
            }
        }
        return new BoardCoordinate(row, col);
    }

    /**
     * This method counts the number of pieces adjacent to the territory
     * and then fills the territory with th right kind. If the number of
     * pieces are the same, the territory is filled with the opposite color
     * of the last player move.
     *
     * @param territory
     * @return the board updated with the pieces of the right colour
     */
    private Board updateBoard(LinkedList<BoardCoordinate> territory) throws InvalidCoordinateException {
        int counterBlack = 0, counterWhite = 0;
        //Counts the total number of white and black pieces adjacent to the territory
        for (BoardCoordinate coordinate : territory){
            Map<Pieces, Integer> adjacentPiecesNumber = countAdjacentPieces(coordinate);
            Integer nBlack = adjacentPiecesNumber.get(Pieces.BLACK);
            Integer nWhite = adjacentPiecesNumber.get(Pieces.WHITE);
            counterBlack = counterBlack + nBlack;
            counterWhite = counterWhite + nWhite;
        }
        if (counterBlack > counterWhite){
            fillTerritory(territory, Pieces.BLACK);
        }
        else if (counterBlack < counterWhite){
            fillTerritory(territory, Pieces.WHITE);
        }
        else{
            if (player.getPieces().equals(Pieces.BLACK)){
                fillTerritory(territory, Pieces.WHITE);
            }
            else if (player.getPieces().equals(Pieces.WHITE)) {
                fillTerritory(territory, Pieces.BLACK);
            }
        }
        return board;
    }

    /**
     * This method fills the territory with the specified type of piece as argument
     *
     * @param territory :list of BoardCoordinate
     * @param blackOrWhite is the type of piece
     */
    private void fillTerritory(LinkedList<BoardCoordinate> territory, Pieces blackOrWhite) {
        for (BoardCoordinate coordinate : territory){
            board.getMatrix()[coordinate.getRow()][coordinate.getColumn()] = blackOrWhite;
        }
    }

    /**
     * This method counts how many pieces and of which type are adjacent to
     * the coordinate passed as input
     *
     * @param coordinate
     * @return a map with the type of the piece and its respective number
     */
    private Map<Pieces, Integer> countAdjacentPieces(BoardCoordinate coordinate) throws InvalidCoordinateException {
        Map<Pieces, Integer> piecesIntegerMap = new HashMap<>();
        int countWhite, countBlack;
        int row = coordinate.getRow();
        int col = coordinate.getColumn();

        BlackAndWhite bn1 = countBnW(row -1, col);
        BlackAndWhite bn2 = countBnW(row +1, col);
        BlackAndWhite bn3 = countBnW(row, col-1);
        BlackAndWhite bn4 = countBnW(row, col+1);
        countBlack = bn1.getnBlack() + bn2.getnBlack() + bn3.getnBlack() + bn4.getnBlack();
        countWhite = bn1.getnWhite() + bn2.getnWhite() + bn3.getnWhite() + bn4.getnWhite();
        piecesIntegerMap.put(Pieces.BLACK, countBlack);
        piecesIntegerMap.put(Pieces.WHITE, countWhite);
        return piecesIntegerMap;
    }

    /**
     * This method is called by "countAdjacentPieces" and it returns a structure
     * that keeps count of the number of black and white pieces in the given coordinate
     *
     * @param row
     * @param col
     * @return a structure with the number of white and black pieces for the given coordinate
     */
    private BlackAndWhite countBnW(int row, int col) throws InvalidCoordinateException {
        int countWhite = 0, countBlack = 0;
        if (isNotEdge(row) && isNotEdge(col)){
            BoardCoordinate coordinate = new BoardCoordinate(row,col);
            if (board.getMatrix()[row][col].equals(Pieces.WHITE) && !countedPieces.contains(coordinate)){
                countWhite++;
                countedPieces.add(coordinate);
            }
            else if (board.getMatrix()[row][col].equals(Pieces.BLACK) && !countedPieces.contains(coordinate)) {
                countBlack++;
                countedPieces.add(coordinate);
            }
        }
        return new BlackAndWhite(countBlack, countWhite);
    }

    /**
     * This method finds the next node which is not occupied by any piece
     *
     * @param emptyNode : the last empty node found
     * @param territory : the current possible territory
     * @return the first empty board coordinate found adjacent to the last empty node, null if none are found
     * @throws InvalidCoordinateException
     */
    private BoardCoordinate findNextEmptyNode(BoardCoordinate emptyNode, LinkedList<BoardCoordinate> territory) throws InvalidCoordinateException {
        BoardCoordinate nextEmpty = new BoardCoordinate();
        int row = emptyNode.getRow();
        int col = emptyNode.getColumn();
        //Checks to the right
        if (isNotEdge(col + 1)) {
            if (board.getMatrix()[row][col + 1].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row, col+1))) {
                nextEmpty.setRow(row);
                nextEmpty.setColumn(col + 1);
                return nextEmpty;
            }
        }
        //Checks to the left
        if (isNotEdge(col - 1)) {
            if (board.getMatrix()[row][col - 1].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row, col-1))) {
                nextEmpty.setRow(row);
                nextEmpty.setColumn(col - 1);
                return nextEmpty;
            }
        }

        //Checks down
        if (isNotEdge(row + 1)) {
            if (board.getMatrix()[row + 1][col].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row + 1, col))) {
                nextEmpty.setRow(row + 1);
                nextEmpty.setColumn(col);
                return nextEmpty;
            }
        }
        //Checks up
        if (isNotEdge(row - 1)) {
            if (board.getMatrix()[row - 1][col].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row -1, col))) {
                nextEmpty.setRow(row - 1);
                nextEmpty.setColumn(col);
                return nextEmpty;
            }
        }
        return null;
    }

    /**
     * Checks if the index is in a certain range
     *
     * @param index
     * @return true if the index is in range of the board dimension, false otherwise
     */
    private boolean isNotEdge(int index) {
        return (index >= 0 && index < board.getDIMENSION());
    }

    /**
     * This method checks if a coordinate has at least two adjacent pieces
     *
     * @param coordinate
     * @return true if the coordinate has at least two adjacent pieces, false otherwise
     */
    private boolean hasAtLeastTwoAdjacentPieces(BoardCoordinate coordinate) {
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

    /**
     * This method checks if the sums of all the elements in the matrix
     * is equals to the dimension of the matrix.
     * This is used in the method "checkTerritories" because when a node is visited
     * in the matrix for checking the territories, the visited node is set to 1.
     *
     * @param matrix
     * @return true if all nodes has been visited, false otherwise.
     */
    private boolean hasAllNodesBeenVisited(int[][] matrix) {
        int counter = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                counter = counter + anInt;
            }
        }
        return counter == (matrix.length * matrix.length);
    }

    /**
     * This method is used to check if the game has came to a conclusion.
     * If the victory condition is satisfied, an exception will be thrown
     * and the game will end.
     *
     * @throws VictoryException
     */
    private void checkVictoryCondition() throws VictoryException {
        int dimension = board.getDIMENSION();
        int[][] matrixTemp = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++)
            Arrays.fill(matrixTemp[i], -1);
        if (hasPath(board.getMatrix(), matrixTemp, player.getPieces())) {
            throw new VictoryException("Victory! " + player.getPieces().getName() + " wins the game");
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


    /**
     * Recursive algorithm Deep First Search to find possible path in a matrix
     * //https://stackoverflow.com/questions/20708659/find-if-path-exists-in-matrix
     *
     * @param matrix
     * @param matrixTemp
     * @param i
     * @param j
     * @param playerColor
     * @return true if the matrix has a path of pieces from side to side, false otherwise
     */
    private static boolean hasPathHelper(Pieces[][] matrix, int[][] matrixTemp, int i, int j, Pieces playerColor) {
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

final class BlackAndWhite {
    private final int nBlack;
    private final int nWhite;

    public BlackAndWhite(int nBlack, int nWhite) {
        this.nBlack = nBlack;
        this.nWhite = nWhite;
    }

    public int getnBlack() {
        return nBlack;
    }

    public int getnWhite() {
        return nWhite;
    }
}


