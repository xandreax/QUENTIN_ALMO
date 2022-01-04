package utils.consoleApplicationRunnerUtils;

import entities.*;
import exceptions.VictoryException;
import gui.BoardShellPrinter;

import java.io.BufferedReader;

import static utils.consoleApplicationRunnerUtils.ConsoleAskPieRule.askForPieRule;
import static utils.consoleApplicationRunnerUtils.ConsoleMoveLoop.playerMoves;

public class ConsoleStartGame {
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
                        if (askForPieRule(player.getUsername(), br)) {
                            Pieces swapPiece1 = game.getPlayer1().getPieces();
                            Pieces swapPiece2 = game.getPlayer2().getPieces();
                            game.getPlayer1().setPieces(swapPiece2);
                            game.getPlayer2().setPieces(swapPiece1);
                            counter++;
                            break;
                        }
                    }
                    if (game.getAvailableMoves(game.getBoard(), player)) {
                        boolean hasMoved = false;
                        while (!hasMoved) {
                            try {
                                if (playerMoves(game, br, player)) {
                                    hasMoved = true;
                                    counter++;
                                }
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
