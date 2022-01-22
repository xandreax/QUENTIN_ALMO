package logic;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import entities.Player;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;

import java.util.Arrays;
import java.util.LinkedList;

public class Controller {
    private Board board;
    private Player currentPlayer;
    private Pieces winnerColour;
    private final Player[] players;
    VictoryExplorer victoryExplorer = new VictoryExplorer();

    public Controller(Board board, Player[] players) {
        this.board = board;
        this.players = players;
        init();
    }

    private void init() {
        if(players[0].isBlackPlayer())
            currentPlayer = players[0];
        else
            currentPlayer = players[1];
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * Checks if player has available moves
     *
     * @return boolean
     */
    public boolean checkIfThereAreAvailableMoves(){
        for(int i = 0; i<board.getDIMENSION(); i++)
        {
            for(int j = 0; j<board.getDIMENSION(); j++) {
                try {
                    BoardCoordinate bc = new BoardCoordinate(i, j);
                    if(board.isCoordinateEmpty(bc)) {
                        checkIfMoveIsPossible(bc);
                        return true;
                    }
                }
                catch (InvalidCoordinateException | PositionAlreadyOccupiedException | IllegalMoveException ignored) {
                    //move not allowed
                }
            }
        }
        return false;
    }

    public void makeMove(BoardCoordinate move) throws InvalidCoordinateException {
        board.setPieceByCoordinate(move, currentPlayer.getPieces());
        board = checkTerritories();
        changeTurn();
    }

    public void changeTurn(){
        currentPlayer = currentPlayer.invertPlayer(players);
    }

    public boolean endOfGame(){
        winnerColour = victoryExplorer.getPieceWinner(board);
        return !winnerColour.equals(Pieces.NONE);
    }

    public Player getWinnerPlayer(){
        if(players[0].getPieces().equals(winnerColour))
            return players[0];
        else
            return players[1];
    }

    public void applyPieRule(){
        Pieces swapPiece1 = players[0].getPieces();
        Pieces swapPiece2 = players[1].getPieces();
        players[0].setPieces(swapPiece2);
        players[1].setPieces(swapPiece1);
        changeTurn();
    }

    public Board getBoard(){
        return board;
    }

    public boolean checkIfIsTimeToPieRule() {
        return currentPlayer.isWhitePlayer() && board.hasNoWhitePieces();
    }

    public void checkIfMoveIsPossible(BoardCoordinate move) throws PositionAlreadyOccupiedException, InvalidCoordinateException, IllegalMoveException {
        BeforeMoveChecker bmc = new BeforeMoveChecker(move, board, currentPlayer);
        bmc.checkIfMoveIsPossible();
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
     * @throws InvalidCoordinateException: coordinate not valid
     */

    private Board checkTerritories() throws InvalidCoordinateException {
        boolean allNodesVisited = false;
        //List that resets every time that finds a new territory
        LinkedList<BoardCoordinate> possibleTerritory = new LinkedList<>();
        int[][] visitedMatrix = new int[board.getDIMENSION()][board.getDIMENSION()];
        BoardCoordinate savedCoordinate = new BoardCoordinate();
        int row = 0, col = 0;
        boolean hasPositionBeenSaved = false;
        //This loop exits when all nodes in the board have been visited
        while (!allNodesVisited) {
            BoardCoordinate bc = board.getCheckedCoordinate(row, col);
            row = bc.getRow();
            col = bc.getColumn();
            //This loop is used to skip nodes that has been already visited
            while (visitedMatrix[row][col] == 1) {
                col++; //next element of row
                bc = board.getCheckedCoordinate(row, col);
                row = bc.getRow();
                col = bc.getColumn();
            }
            BoardCoordinate coordinate = new BoardCoordinate(row, col);
            //Checks if the current coordinate is empty and has at least 2 adjacent pieces
            if (board.isCoordinateEmpty(coordinate) && coordinate.hasAtLeastTwoAdjacentPieces(board)) {
                possibleTerritory.add(coordinate);
                visitedMatrix[row][col] = 1;
                savedCoordinate = getSavedCoordinate(row, col, hasPositionBeenSaved, savedCoordinate);
                BoardCoordinate emptyNode = findNextEmptyNode(possibleTerritory.getLast(), possibleTerritory, board);
                emptyNode = findNextEmptyNodeDoLoop(possibleTerritory, savedCoordinate, emptyNode);

                if (emptyNode == null){
                    //Territory found
                    BoardUpdater boardUpdater = new BoardUpdater(board, currentPlayer);
                    board = boardUpdater.updateBoardWithTerritory(possibleTerritory);
                    possibleTerritory.clear();
                }
                else visitedMatrix[emptyNode.getRow()][emptyNode.getColumn()] = 1;
                row = savedCoordinate.getRow();
                col = savedCoordinate.getColumn() + 1;
                hasPositionBeenSaved = false;
            }
            //Go to next node
            else {
                visitedMatrix[row][col] = 1;
                col++; //next element of row
            }
            //checks if all nodes has been visited
            allNodesVisited = hasAllNodesBeenVisited(visitedMatrix);
        }
        return board;
    }

    private BoardCoordinate getSavedCoordinate(int row, int col, boolean hasPositionBeenSaved, BoardCoordinate savedCoordinate) throws InvalidCoordinateException {
        //Save the position for the first node found
        if (!hasPositionBeenSaved) {
            savedCoordinate = new BoardCoordinate(row, col);
        }
        return savedCoordinate;
    }

    private BoardCoordinate findNextEmptyNodeDoLoop(LinkedList<BoardCoordinate> possibleTerritory, BoardCoordinate savedCoordinate, BoardCoordinate emptyNode) throws InvalidCoordinateException {
        //This loop is used to find the next empty node
        while (emptyNode != null){
            if(emptyNode.hasAtLeastTwoAdjacentPieces(board)){
                possibleTerritory.add(emptyNode); //Add the node to the list
                emptyNode = findNextEmptyNode(emptyNode, possibleTerritory, board);
                if (emptyNode == null){
                    //If the region is closed start again from the saved position
                    emptyNode = findNextEmptyNode(savedCoordinate, possibleTerritory, board);
                }
            }
            else{
                //clear the list since it was not a territory
                possibleTerritory.clear();
                break;
            }
        }
        return emptyNode;
    }

    /**
     * This method checks if the sums of all the elements in the matrix
     * is equals to the dimension of the matrix.
     * This is used in the method "checkTerritories" because when a node is visited
     * in the matrix for checking the territories, the visited node is set to 1.
     *
     * @param matrix: 2d matrix
     * @return true if all nodes has been visited, false otherwise.
     */
    private boolean hasAllNodesBeenVisited(int[][] matrix) {
        int counter = Arrays.stream(matrix).flatMapToInt(Arrays::stream).reduce(0, (x, y) -> x+y);
        return counter == (matrix.length * matrix.length);
    }


    /**
     * This method finds the next node which is not occupied by any piece
     *
     * @param lastEmptyNode : the last empty node found
     * @param territory : the current possible territory
     * @return the first empty board coordinate found adjacent to the last empty node, null if none are found
     * @throws InvalidCoordinateException: coordinate not valid
     */
    private BoardCoordinate findNextEmptyNode(BoardCoordinate lastEmptyNode, LinkedList<BoardCoordinate> territory, Board board) throws InvalidCoordinateException {
        int row = lastEmptyNode.getRow();
        int col = lastEmptyNode.getColumn();
        BoardCoordinate bc = new BoardCoordinate(row,col);
        //Checks to the right
        if (board.isNotEdge(col + 1)) {
            if (board.isCoordinateEmpty(bc.getRight()) && !territory.contains(bc.getRight())) {
                return bc.getRight();
            }
        }
        //Checks to the left
        if (board.isNotEdge(col - 1)) {
            if (board.isCoordinateEmpty(bc.getLeft()) && !territory.contains(bc.getLeft())) {
                return bc.getLeft();
            }
        }
        //Checks down
        if (board.isNotEdge(row + 1)) {
            if (board.isCoordinateEmpty(bc.getDown()) && !territory.contains(bc.getDown())) {
                return bc.getDown();
            }
        }
        //Checks up
        if (board.isNotEdge(row - 1)) {
            if (board.isCoordinateEmpty(bc.getUp()) && !territory.contains(bc.getUp())) {
                return bc.getUp();
            }
        }
        return null;
    }


}
