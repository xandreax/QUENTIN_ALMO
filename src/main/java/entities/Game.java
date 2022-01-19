package entities;

import exceptions.*;
import logic.aftermove.AfterMoveChecker;
import logic.aftermove.territories.UpdaterBoard;
import logic.beforemove.BeforeMoveChecker;

import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;

public class Game {
    //FIELDS
    private final String uuid;
    private final Date beginTime;
    private final Player player1;
    private final Player player2;
    private Board board;

    //CONSTRUCTORS
    public Game(Player player1, Player player2) {
        this.uuid = UUID.randomUUID().toString();
        this.beginTime = new Date();
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
    }

    //METHODS
    public String getUuid() {
        return this.uuid;
    }

    public Date getBeginTime() {
        return this.beginTime;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board getBoard() {
        return board;
    }

    public void move(Move move) throws PositionAlreadyOccupiedException, IllegalMoveException, VictoryException, InvalidCoordinateException {
        BeforeMoveChecker bmc = new BeforeMoveChecker(move, getBoard());
        bmc.checkIfMoveIsPossible();
        getBoard().doMove(move);
        AfterMoveChecker amc = new AfterMoveChecker(this, move.getPlayer());
        board = amc.checkAndUpdateBoardAfterMove();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Game) {
            Game param = (Game) obj;
            return (this.getUuid().equals(param.getUuid()));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    /**
     * Checks if player has available moves
     *
     * @return boolean
     */
    public boolean checkIfThereAreAvailableMoves(Board board, Player player) {
        for(int i = 0; i<board.getDIMENSION(); i++)
        {
            for(int j = 0; j<board.getDIMENSION(); j++) {
                if(board.getMatrix()[i][j].equals(Pieces.NONE))
                {
                    try {
                        Move move = new Move(player, new BoardCoordinate(i, j));
                        BeforeMoveChecker bmc = new BeforeMoveChecker(move, board);
                        bmc.checkIfMoveIsPossible();
                        return true;
                    } catch (InvalidCoordinateException | PositionAlreadyOccupiedException | IllegalMoveException e) {
                        //move not allowed
                    }
                }
            }
        }
        return false;
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

    public Board checkTerritories(Player player) throws InvalidCoordinateException {
        boolean allNodesVisited = false;
        //List that resets every time that finds a new territory
        LinkedList<BoardCoordinate> possibleTerritory = new LinkedList<>();
        int[][] visitedMatrix = new int[board.getDIMENSION()][board.getDIMENSION()];
        BoardCoordinate savedCoordinate = new BoardCoordinate();
        int row = 0, col = 0;
        boolean hasPositionBeenSaved = false;
        //This loop exits when all nodes in the board have been visited
        while (!allNodesVisited) {
            BoardCoordinate bc = board.checkEdgesOfBoard(row, col);
            row = bc.getRow();
            col = bc.getColumn();
            //This loop is used to skip nodes that has been already visited
            while (visitedMatrix[row][col] == 1) {
                col++; //next element of row
                bc = board.checkEdgesOfBoard(row, col);
                row = bc.getRow();
                col = bc.getColumn();
            }
            BoardCoordinate coordinate = new BoardCoordinate(row, col);
            //Checks if the current coordinate is empty and has at least 2 adjacent pieces
            if (board.getMatrix()[row][col].equals(Pieces.NONE) && coordinate.hasAtLeastTwoAdjacentPieces(board)) {
                possibleTerritory.add(coordinate);
                visitedMatrix[row][col] = 1;
                savedCoordinate = getSavedCoordinate(row, col, hasPositionBeenSaved, savedCoordinate);
                BoardCoordinate emptyNode = findNextEmptyNode(possibleTerritory.getLast(), possibleTerritory, board);
                emptyNode = findNextEmptyNodeDoLoop(possibleTerritory, savedCoordinate, emptyNode);

                if (emptyNode == null){
                    //Territory found
                    UpdaterBoard updaterBoard = new UpdaterBoard(board, player);
                    board = updaterBoard.updateBoardWithTerritory(possibleTerritory);
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
        return board;
    }

    private BoardCoordinate getSavedCoordinate(int row, int col, boolean hasPositionBeenSaved, BoardCoordinate savedCoordinate) throws InvalidCoordinateException {
        //Save the position for the first node found
        if (!hasPositionBeenSaved) {
            savedCoordinate = new BoardCoordinate(row, col);
            hasPositionBeenSaved = true;
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
     * This method finds the next node which is not occupied by any piece
     *
     * @param lastEmptyNode : the last empty node found
     * @param territory : the current possible territory
     * @return the first empty board coordinate found adjacent to the last empty node, null if none are found
     * @throws InvalidCoordinateException
     */
    private BoardCoordinate findNextEmptyNode(BoardCoordinate lastEmptyNode, LinkedList<BoardCoordinate> territory, Board board) throws InvalidCoordinateException {
        BoardCoordinate nextEmpty = new BoardCoordinate();
        int row = lastEmptyNode.getRow();
        int col = lastEmptyNode.getColumn();
        //Checks to the right
        if (board.isNotEdge(col + 1)) {
            if (board.getMatrix()[row][col + 1].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row, col+1))) {
                nextEmpty.setRow(row);
                nextEmpty.setColumn(col + 1);
                return nextEmpty;
            }
        }
        //Checks to the left
        if (board.isNotEdge(col - 1)) {
            if (board.getMatrix()[row][col - 1].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row, col-1))) {
                nextEmpty.setRow(row);
                nextEmpty.setColumn(col - 1);
                return nextEmpty;
            }
        }

        //Checks down
        if (board.isNotEdge(row + 1)) {
            if (board.getMatrix()[row + 1][col].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row + 1, col))) {
                nextEmpty.setRow(row + 1);
                nextEmpty.setColumn(col);
                return nextEmpty;
            }
        }
        //Checks up
        if (board.isNotEdge(row - 1)) {
            if (board.getMatrix()[row - 1][col].equals(Pieces.NONE) && !territory.contains(new BoardCoordinate(row -1, col))) {
                nextEmpty.setRow(row - 1);
                nextEmpty.setColumn(col);
                return nextEmpty;
            }
        }
        return null;
    }
}
