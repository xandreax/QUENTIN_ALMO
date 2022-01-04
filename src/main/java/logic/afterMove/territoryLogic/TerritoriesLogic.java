package logic.afterMove.territoryLogic;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import entities.Player;
import exceptions.InvalidCoordinateException;

import java.util.LinkedList;

import static logic.afterMove.territoryLogic.TerritorySearch.findNextEmptyNode;
import static logic.afterMove.territoryLogic.TerritorySearch.hasAtLeastTwoAdjacentPieces;

public class TerritoriesLogic {
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
    public static Board checkTerritories(Board board, Player player) throws InvalidCoordinateException {
        boolean allNodesVisited = false;
        //List that resets every time that finds a new territory
        LinkedList<BoardCoordinate> possibleTerritory = new LinkedList<>();
        int[][] visitedMatrix = new int[board.getDIMENSION()][board.getDIMENSION()];
        Pieces[][] matrixPieces = board.getMatrix();
        int row = 0, col = 0;
        boolean hasPositionBeenSaved = false;
        //This loop exits when all nodes in the board have been visited
        while (!allNodesVisited) {
            BoardCoordinate bc = checkEdgesOfBoard(row, col, board.getDIMENSION());
            row = bc.getRow();
            col = bc.getColumn();
            //This loop is used to skip nodes that has been already visited
            while (visitedMatrix[row][col] == 1) {
                col++; //next element of row
                BoardCoordinate boardCoordinate = checkEdgesOfBoard(row, col, board.getDIMENSION());
                row = boardCoordinate.getRow();
                col = boardCoordinate.getColumn();
            }
            BoardCoordinate coordinate = new BoardCoordinate(row, col);
            //Checks if the current coordinate is empty and has at least 2 adjacent pieces
            if (matrixPieces[row][col].equals(Pieces.NONE) && hasAtLeastTwoAdjacentPieces(coordinate, board)) {
                possibleTerritory.add(coordinate);
                visitedMatrix[row][col] = 1;
                BoardCoordinate savedCoordinate = new BoardCoordinate();
                //Save the position for the first node found
                if (!hasPositionBeenSaved) {
                    savedCoordinate = new BoardCoordinate(row, col);
                    hasPositionBeenSaved = true;
                }
                BoardCoordinate emptyNode = findNextEmptyNode(possibleTerritory.getLast(), possibleTerritory, board);
                //This loop is used to find the next empty node
                while (emptyNode != null){
                    if(hasAtLeastTwoAdjacentPieces(emptyNode, board)){
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
                        hasPositionBeenSaved = false;
                        break;
                    }
                }

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
    private static BoardCoordinate checkEdgesOfBoard(int row, int col, int boardDimension) throws InvalidCoordinateException {
        if (col >= boardDimension) {
            row++;
            col = 0;
            if (row >= boardDimension){
                row = 0;
            }
        }
        return new BoardCoordinate(row, col);
    }



    /**
     * Checks if the index is in a certain range
     *
     * @param index
     * @return true if the index is in range of the board dimension, false otherwise
     */
    public static boolean isNotEdge(int index, int range) {
        return (index >= 0 && index < range);
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
    private static boolean hasAllNodesBeenVisited(int[][] matrix) {
        int counter = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                counter = counter + anInt;
            }
        }
        return counter == (matrix.length * matrix.length);
    }

}
