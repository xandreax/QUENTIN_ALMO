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
    /**
     * This method is for console mode of the game and its functionality is to ask and read the usernames in input
     *
     * @param br
     * @return an array of the two usernames
     */
    public static String[] askForUsernames(BufferedReader br) {
        String[] result = new String[2];
        String username;
        int contatore = 1;
        while (contatore < 3) {
            System.out.print("Enter Player " + contatore + " username: ");
            try {
                username = br.readLine().trim();
                if (username.equals("")) {
                    System.out.println("Empty string is not a valid name. Must be at least 3 characters long.");
                } else if (username.length() <= 2) {
                    System.out.println("Username must be at least 3 characters long.");
                } else {
                    result[contatore - 1] = username;
                    contatore++;
                }
            } catch (IOException e) {
                System.err.println("Error while trying to read from System.in. Aborted.");
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * This method is for console mode of the game and its functionality is to ask and read the color of the pieces in input
     *
     * @param br
     * @param username1 username of player 1
     * @return an array of the two pieces: (result[0] is player 1 and result[1] is player 2)
     */
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

    /**
     * This method is for console mode of the game and its functionality is to start the game
     *
     * @param br
     * @param game
     * @param bp
     * @param player1
     * @param player2
     */
    public static void startGame(BufferedReader br, Game game, BoardShellPrinter bp, Player player1, Player player2) {
        boolean isFinished = false;
        int counter = 0;
        Player[] players = new Player[2];
        players[0] = player1;
        players[1] = player2;
        while (!isFinished) {
            bp.printOnStdOut(true);
            for (Player player : players) {
                if (!isFinished) {
                    /* If it's player2 first turn: ask the pie rule*/
                    if (counter <= 1 && player.getPieces().equals(Pieces.WHITE)) {
                        while (true) {
                            System.out.println("\nPlayer " + player.getUsername() + ", make use of the Pie rule?(0) or continue(1)?\n");
                            try {
                                String temp = br.readLine();
                                int i = Integer.parseInt(temp);
                                if (i == 0) {
                                    Pieces swapPiece1 = game.getPlayer1().getPieces();
                                    Pieces swapPiece2 = game.getPlayer2().getPieces();
                                    game.getPlayer1().setPieces(swapPiece2);
                                    game.getPlayer2().setPieces(swapPiece1);
                                    counter++;
                                    break;
                                } else if (i == 1) {
                                    break;
                                } else {
                                    System.out.println("Invalid number inserted. Please insert 0 for PieRule, 1 for continue.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number format inserted.");
                            } catch (IOException e) {
                                System.err.println("Error while trying to read from System.in. Aborted.");
                                e.printStackTrace();
                            }
                        }
                    }
                    if (game.checkIfThereAreAvailableMoves(game.getBoard(), player)) {
                        boolean hasMoved = false;
                        while (!hasMoved) {
                            System.out.print("\n" + player.getUsername() + "[" + player.getPieces().getName() + "] has to move. Insert a valid coordinate: ");
                            try {
                                String temp = br.readLine().trim();
                                BoardCoordinate newBoardCoordinate = new BoardCoordinate(temp);
                                Move newMove = new Move(player, newBoardCoordinate);
                                game.move(newMove);
                                hasMoved = true;
                                counter++;
                            } catch (IOException e) {
                                System.err.println("Error while trying to read from System.in. Aborted.");
                                e.printStackTrace();
                            } catch (InvalidCoordinateException e) {
                                System.out.println("Invalid coordinate. Format should be: a literal from a to m, and right after a number from 0 to 12.Try again.");
                            } catch (PositionAlreadyOccupiedException e) {
                                System.out.println("Invalid coordinate. This position is already occupied by another piece");
                            } catch (IllegalMoveException e) {
                                System.out.println("Invalid coordinate. Please choose another position orthogonal to any your other piece.");
                            } catch (VictoryException e) {
                                Player winner;
                                if (e.getPiece().equals(game.getPlayer1().getPieces())) winner = game.getPlayer1();
                                else winner = game.getPlayer2();
                                System.out.println("Victory! " + winner.getUsername() + " has won the game!");
                                isFinished = true;
                                break;
                            }
                        }
                    } else {
                        System.out.println("Player " + player.getUsername() + " has no available moves");
                    }
                }
            }
        }
    }
}
