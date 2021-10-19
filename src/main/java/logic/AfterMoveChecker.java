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

    public AfterMoveChecker(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    public Board checkAndUpdateBoardAfterMove() throws VictoryException, InvalidCoordinateException {
        Board boardChecked = board;
        //Se ci sono state almeno 3 mosse ha senso controllare i territori
        if (board.getMovesHistory().size() > 2) {
            boardChecked = checkTerritories();
        }
        checkVictoryCondition();
        return boardChecked;
    }

    private Board checkTerritories() throws InvalidCoordinateException {
        boolean allNodesVisited = false;
        LinkedList<BoardCoordinate> possibleTerritory = new LinkedList<>();  //List that reset every time that finds a new territory
        int[][] visitedMatrix = new int[board.getDIMENSION()][board.getDIMENSION()];
        Pieces[][] matrixPieces = board.getMatrix();
        int row = 0, col = 0;
        boolean hasPositionBeenSaved = false;
        while (!allNodesVisited) {
            BoardCoordinate bc = checkEdgesOfBoard(row, col);
            row = bc.getX();
            col = bc.getY();
            while (visitedMatrix[row][col] == 1) { //Node already visited
                col++; //next element of row
                BoardCoordinate boardCoordinate = checkEdgesOfBoard(row, col);
                row = boardCoordinate.getX();
                col = boardCoordinate.getY();
            }
            BoardCoordinate coordinate = new BoardCoordinate(row, col);
            if (matrixPieces[row][col].equals(Pieces.NONE) && hasAtLeastTwoAdjacentPieces(coordinate)) {
                possibleTerritory.add(coordinate);
                visitedMatrix[row][col] = 1;
                BoardCoordinate savedCoordinate = new BoardCoordinate();
                if (!hasPositionBeenSaved) {
                    savedCoordinate = new BoardCoordinate(row, col);
                    hasPositionBeenSaved = true;
                }
                BoardCoordinate emptyNode = findNextEmptyNode(possibleTerritory.getLast(), possibleTerritory);
                while (emptyNode != null){
                    if(hasAtLeastTwoAdjacentPieces(emptyNode)){
                        possibleTerritory.add(emptyNode);
                        emptyNode = findNextEmptyNode(emptyNode, possibleTerritory);
                        if (emptyNode == null){
                            emptyNode = findNextEmptyNode(savedCoordinate, possibleTerritory);;
                        }
                    }
                    else{
                        possibleTerritory.clear();
                        hasPositionBeenSaved = false;
                        break;
                    }
                }

                if (emptyNode == null){
                    //territorio trovato
                    board = updateBoard(possibleTerritory);
                    row = savedCoordinate.getX();
                    col = savedCoordinate.getY() + 1;
                    hasPositionBeenSaved = false;
                    possibleTerritory.clear();
                }
                else{
                    visitedMatrix[emptyNode.getX()][emptyNode.getY()] = 1;
                    row = savedCoordinate.getX();
                    col = savedCoordinate.getY() + 1;
                    hasPositionBeenSaved = false;
                }

            } else {
                visitedMatrix[row][col] = 1;
                col++; //next element of row
            }

            allNodesVisited = hasAllNodesBeenVisited(visitedMatrix);
        }
        return  board;
    }

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

    private Board updateBoard(LinkedList<BoardCoordinate> territory) {
        int counterBlack = 0, counterWhite = 0;
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

    private void fillTerritory(LinkedList<BoardCoordinate> territory, Pieces blackOrWhite) {
        for (BoardCoordinate coordinate : territory){
            board.getMatrix()[coordinate.getX()][coordinate.getY()] = blackOrWhite;
        }
    }

    private Map<Pieces, Integer> countAdjacentPieces(BoardCoordinate coordinate) {
        Map<Pieces, Integer> piecesIntegerMap = new HashMap<>();
        int countWhite, countBlack;
        int row = coordinate.getX();
        int col = coordinate.getY();

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

    private BlackAndWhite countBnW(int row, int col){
        int countWhite = 0, countBlack = 0;
        if (isNotEdge(row) && isNotEdge(col)){
            if (board.getMatrix()[row][col].equals(Pieces.WHITE)){
                countWhite++;
            }
            else if (board.getMatrix()[row][col].equals(Pieces.BLACK)) {
                countBlack++;
            }
        }
        return new BlackAndWhite(countBlack, countWhite);
    }

    private BoardCoordinate findNextEmptyNode(BoardCoordinate emptyNode, LinkedList<BoardCoordinate> territory) throws InvalidCoordinateException {
        BoardCoordinate nextEmpty = new BoardCoordinate();
        int row = emptyNode.getX();
        int col = emptyNode.getY();
        if (isNotEdge(col + 1)) {
            if (board.getMatrix()[row][col + 1].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row, col+1))) {
                nextEmpty.setX(row);
                nextEmpty.setY(col + 1);
                return nextEmpty;
            }
        }

        if (isNotEdge(col - 1)) {
            if (board.getMatrix()[row][col - 1].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row, col-1))) {
                nextEmpty.setX(row);
                nextEmpty.setY(col - 1);
                return nextEmpty;
            }
        }

        if (isNotEdge(row + 1)) {
            if (board.getMatrix()[row + 1][col].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row + 1, col))) {
                nextEmpty.setX(row + 1);
                nextEmpty.setY(col);
                return nextEmpty;
            }
        }

        if (isNotEdge(row - 1)) {
            if (board.getMatrix()[row - 1][col].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row -1, col))) {
                nextEmpty.setX(row - 1);
                nextEmpty.setY(col);
                return nextEmpty;
            }
        }
        return null;
    }

    private boolean isNotEdge(int index) {
        return (index >= 0 && index < board.getDIMENSION());
    }


    private boolean hasAtLeastTwoAdjacentPieces(BoardCoordinate coordinate) {
        boolean down, up, right, left;
        int row = coordinate.getX();
        int col = coordinate.getY();
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

    private boolean hasAllNodesBeenVisited(int[][] matrix) {
        int counter = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                counter = counter + anInt;
            }
        }
        return counter == (matrix.length * matrix.length);
    }

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


