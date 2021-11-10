package utils;

import entities.*;
import exceptions.IllegalMoveException;
import exceptions.InvalidCoordinateException;
import exceptions.PositionAlreadyOccupiedException;
import exceptions.VictoryException;
import gui.BoardShellPrinter;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleApplicationRunnerUtils {
    //METHODS
    public static String[] askForUsernames(BufferedReader br) {
        String[] result = new String[2];
        String username1 = "";
        String username2 = "";
        boolean done = false;
        while (!done) {
            System.out.print("Enter Player 1 username: ");
            try {
                username1 = br.readLine().trim();
                if (username1.equals("")) {
                    System.out.println("Empty string is not a valid name. Must be at least 3 characters long.");
                } else if (username1.length() <= 2) {
                    System.out.println("Username must be at least 3 characters long.");
                } else {
                    done = true;
                }
            } catch (IOException e) {
                System.err.println("Error while trying to read from System.in. Aborted.");
                e.printStackTrace();
            }
        }
        done = false;
        while (!done) {
            System.out.print("Enter Player 2 username: ");
            try {
                username2 = br.readLine().trim();
                if (username2.equals("")) {
                    System.out.println("Empty string is not a valid name. Must be at least 3 characters long.");
                }
                if (username2.length() <= 2) {
                    System.out.println("Username must be at least 3 characters long.");
                } else {
                    done = true;
                }
            } catch (IOException e) {
                System.err.println("Error while trying to read from System.in. Aborted.");
                e.printStackTrace();
            }
        }
        result[0] = username1;
        result[1] = username2;
        return result;
    }

    public static Pieces[] askForPieces(BufferedReader br, String username1) {
        Pieces[] result = new Pieces[2];
        boolean done = false;
        while (!done) {
            System.out.print("Player '" + username1 + "' please select a color: 0 for BLACK, 1 for WHITE: ");
            try {
                String temp = br.readLine();
                int i = Integer.parseInt(temp);
                if (i == 0) {
                    result[0] = Pieces.BLACK;
                    done = true;
                } else if (i == 1) {
                    result[0] = Pieces.WHITE;
                    done = true;
                } else {
                    System.out.println("Invalid number inserted. Please insert 0 for BLACK, 1 for WHITE.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format inserted.");
            } catch (IOException e) {
                System.err.println("Error while trying to read from System.in. Aborted.");
                e.printStackTrace();
            }
        }
        if (result[0] == Pieces.BLACK) result[1] = Pieces.WHITE;
        else result[1] = Pieces.BLACK;
        return result;
    }

    public static void startGame(BufferedReader br, Game game, BoardShellPrinter bp, Player player1, Player player2) {
        boolean isFinished = false;
        String coords = "";
        int counter = 0;
        int counterMoveForPlayer1 = 0;
        int counterMoveForPlayer2 = 0;
        while (!isFinished) {
            bp.printOnStdOut(true);

            boolean hasMoved = false;
            while (!hasMoved) {
                System.out.print("\n1: "+player1.getUsername() + "[" + player1.getPieces().getName() + ", move #" + counterMoveForPlayer1 + "] has to move. Insert a valid coordinate: ");
                try {
                    System.out.println("--------------------");
                    String temp = br.readLine().trim();
                    coords = temp;
                    BoardCoordinate newBoardCoordinate = new BoardCoordinate(temp);
                    Move newMove = new Move(player1, newBoardCoordinate);
                    game.move(newMove);
                    counterMoveForPlayer1++;
                    hasMoved = true;
                } catch (IOException e) {
                    System.err.println("Error while trying to read from System.in. Aborted.");
                    e.printStackTrace();
                } catch (InvalidCoordinateException e) {
                    System.out.println("Invalid coordinate. Format should be: a literal from a to m, and right after a number from 0 to 12.Try again.");
                } catch (PositionAlreadyOccupiedException e) {
                    System.out.println("Invalid coordinate. This position is already occupied by another piece");
                } catch (IllegalMoveException e) {
                    System.out.println("Invalid coordinate. Please choose another position orthogonal to any your other piece.");
                }
                catch (VictoryException e){
                    System.out.println("Victory! "+player1.getUsername()+" has won the game!");
                    hasMoved = true;
                    isFinished = true;
                }
            }
            hasMoved = false;
            while (!hasMoved && !isFinished) {
                System.out.print("\n 2: " + player2.getUsername() + "[" + player2.getPieces().getName() + ", move #" + counterMoveForPlayer2 + "] has to move. Insert a valid coordinate: ");
                try {

                    /* Verifica se è la prima mossa del player2. Se sì, implementa pie rule */
                    if(counter == 0){
                        /* handle condition */
                        System.out.println("\nAvvalersi del Pie Rule(0) o Proseguire(1)?\n");
                        String result = br.readLine().trim();

                        if (result.equals("0")) {
                            System.out.println("\nCambio prima pedina in "+ coords +"\n");
                            BoardCoordinate updateBoardCoordinate = new BoardCoordinate(coords);
                            Move newMove = new Move(player2, updateBoardCoordinate);
                            game.move(newMove, true);
                            counterMoveForPlayer1++;
                            counter++;
                            hasMoved=true;
                            continue;
                        }
                        counter++;

                    } else {

                        String temp = br.readLine().trim();
                        BoardCoordinate newBoardCoordinate = new BoardCoordinate(temp);
                        Move newMove = new Move(player2, newBoardCoordinate);
                        game.move(newMove);
                        counterMoveForPlayer2++;
                        hasMoved = true;
                    }
                } catch (IOException e) {
                    System.err.println("Error while trying to read from System.in. Aborted.");
                    e.printStackTrace();
                } catch (InvalidCoordinateException e) {
                    System.out.println("Invalid coordinate. Try again.");
                } catch (PositionAlreadyOccupiedException e) {
                    System.out.println("Invalid coordinate. This position is already occupied by another piece. Try again");
                } catch (IllegalMoveException e) {
                    System.out.println("Invalid coordinate. Please choose another position orthogonal to any your other piece.");
                }
                catch (VictoryException e){
                    System.out.println("Victory! "+player1.getUsername()+" has won the game!");
                    hasMoved = true;
                    isFinished = true;
                }
            }
        }
    }
}
