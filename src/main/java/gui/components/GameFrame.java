package gui.components;

import entities.*;
import exceptions.*;
import gui.components.game.page.NoAvailableMovesDialog;
import gui.components.game.page.PanelGamePage;
import gui.components.game.page.PieRuleDialog;
import gui.components.game.page.VictoryDialog;
import gui.components.welcome.page.DialogForPlayers;
import gui.components.welcome.page.DiscardButtonActionListener;
import gui.components.welcome.page.PanelWelcomePage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class GameFrame extends JFrame {
    //FIELDS
    public static Color BACKGROUND_COLOR = new Color(0, 141, 8);
    public static Color BOARD_COLOR = new Color(0, 105, 9);
    protected int screenHeight;
    protected int screenWidth;
    protected int side;
    protected boolean isGameOn;
    protected int countMovesPlayer1;
    protected int countMovesPlayer2;
    protected Game game;
    protected boolean isPlayer1Turn;

    //CONSTRUCTORS
    public GameFrame() {
        super();
        this.setTitle("Quentin");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double sw = screenSize.getWidth();
        double sh = screenSize.getHeight();
        this.screenWidth = (int) sw;
        this.screenHeight = (int) sh;
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(720, 540));
        this.setSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.setResizable(true);
        ImageIcon logo = new ImageIcon("src/main/java/gui/logo.png");
        this.setIconImage(logo.getImage());
        this.side = 9*Math.min(this.getWidth(), this.getHeight())/10;
        this.setLayout(new GridBagLayout());
        this.isGameOn = false;
        this.initWelcomePage();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                GameFrame.this.side = 7*Math.min(GameFrame.this.getWidth(), GameFrame.this.getHeight())/8;
                GameFrame.this.getContentPane().removeAll();
                if (GameFrame.this.isGameOn) {
                    GameFrame.this.refreshGamePage();
                }
                else {
                    GameFrame.this.initWelcomePage();
                }
                GameFrame.this.validate();
            }
        });
        this.toFront();
    }

    //METHODS
    public int getSide() {
        return side;
    }

    public Game getGame() {
        return game;
    }

    public void initWelcomePage() {
        PanelWelcomePage panelWelcomePage = new PanelWelcomePage(this);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.getContentPane().add(panelWelcomePage, gbc);
    }

    public void initNewGame() {
        DialogForPlayers dialog1 = new DialogForPlayers(Pieces.WHITE, this);
        Set<DialogForPlayers> temp = new HashSet<>();
        temp.add(dialog1);
        dialog1.getButtonDiscard().addActionListener(new DiscardButtonActionListener(temp, this));
        dialog1.getButtonOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Player player1 = new Player(dialog1.getTextField().getText(), Pieces.BLACK);
                    DialogForPlayers dialog2 = new DialogForPlayers(Pieces.BLACK, GameFrame.this);
                    temp.add(dialog2);
                    dialog2.getButtonDiscard().addActionListener(new DiscardButtonActionListener(temp, GameFrame.this));
                    dialog2.getButtonOk().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Player player2 = new Player(dialog2.getTextField().getText(), Pieces.WHITE);
                                GameFrame.this.countMovesPlayer1 = 0;
                                GameFrame.this.countMovesPlayer2 = 0;
                                GameFrame.this.startGame(player1, player2);
                                dialog2.dispose();
                            }
                            catch (UnsupportedPiecesForPlayerException | UsernameTooShortException exc) {
                                GameFrame.this.toggleAlertDialog(exc.getMessage());
                            }
                        }
                    });
                    dialog1.dispose();
                    dialog2.setVisible(true);
                }
                catch (UnsupportedPiecesForPlayerException | UsernameTooShortException ex) {
                    GameFrame.this.toggleAlertDialog(ex.getMessage());
                }
            }
        });
        dialog1.setVisible(true);
    }

    public void startGame(Player player1, Player player2) {
        this.game = new Game(player1, player2);
        this.getContentPane().removeAll();
        PanelGamePage panelGamePage = new PanelGamePage(this);
        this.getContentPane().add(panelGamePage);
        this.validate();
        this.isGameOn = true;
        this.isPlayer1Turn = true;
        PanelGamePage p = (PanelGamePage) this.getContentPane().getComponent(0);
        p.getHeader().highlightTurn(getPlayerWithBlackPieces(this.getGame().getPlayer1(), this.getGame().getPlayer2()));
    }

    public void renderMove() {
        if (!this.isGameOn) {
            throw new IllegalStateException("There is not a game going on.");
        }
        else {
            PanelGamePage p = (PanelGamePage) this.getContentPane().getComponent(0);
            p.getBoardPanel().updateHoverButtons();
            p.getBoardPanel().repaint();
        }
    }

    public void handleMove(BoardCoordinate boardCoordinate) {
        PanelGamePage p = (PanelGamePage) this.getContentPane().getComponent(0);
        Player turnPlayer = null;
        if (this.isPlayer1Turn) {
            turnPlayer = this.getGame().getPlayer1();
            if (! this.game.checkIfThereAreAvailableMoves(this.game.getBoard(), this.getGame().getPlayer1())) {
                this.toggleNoAvailableMovesDialog(this.getGame().getPlayer1());
                p.getHeader().highlightTurn(this.getGame().getPlayer2());
                this.isPlayer1Turn = !this.isPlayer1Turn;
                return;
            }
        }
        else {
            turnPlayer = this.getGame().getPlayer2();
            if (! this.game.checkIfThereAreAvailableMoves(this.game.getBoard(), this.getGame().getPlayer2())) {
                this.toggleNoAvailableMovesDialog(this.getGame().getPlayer2());
                p.getHeader().highlightTurn(this.getGame().getPlayer1());
                this.isPlayer1Turn = !this.isPlayer1Turn;
                return;
            }
        }
        Move newMove = new Move(turnPlayer, boardCoordinate);
        try {
            this.game.move(newMove);
            this.renderMove();
            if (this.isPlayer1Turn) {
                this.countMovesPlayer1++;
                p.getHeader().highlightTurn(this.getGame().getPlayer2());
            }
            else {
                this.countMovesPlayer2++;
                p.getHeader().highlightTurn(this.getGame().getPlayer1());
            }
            this.isPlayer1Turn = !this.isPlayer1Turn;
        }
        catch (InvalidCoordinateException e) {
            this.toggleAlertDialog("Invalid coordinate. Format should be: a literal from a to m, and right after a number from 0 to 12.Try again.");
        }
        catch (PositionAlreadyOccupiedException e) {
            this.toggleAlertDialog("Invalid coordinate. This position is already occupied by another piece");
        }
        catch (IllegalMoveException e) {
            this.toggleAlertDialog("Invalid coordinate. Please choose another position orthogonal to any your other piece.");
        }
        catch (VictoryException e) {
            this.renderMove();
            Player winner;
            if (e.getPiece().equals(game.getPlayer1().getPieces())) winner = game.getPlayer1();
            else winner = game.getPlayer2();
            this.toggleVictoryDialog(winner);
        }
        this.controlPieRule();
    }

    private void refreshGamePage() {
        if (this.isGameOn) {
            PanelGamePage panelGamePage = new PanelGamePage(this);
            this.getContentPane().add(panelGamePage);
            this.validate();
            this.isGameOn = true;
        }
        else {
            throw new IllegalStateException("There is not a game going on.");
        }
    }

    public void doPieRule() {
        PanelGamePage p = (PanelGamePage) this.getContentPane().getComponent(0);
        if (this.getGame().getPlayer1().getPieces() == Pieces.WHITE) {
            this.getGame().getPlayer1().setPieces(Pieces.BLACK);
            this.getGame().getPlayer2().setPieces(Pieces.WHITE);
        }
        else {
            this.getGame().getPlayer1().setPieces(Pieces.WHITE);
            this.getGame().getPlayer2().setPieces(Pieces.BLACK);
        }
        if (this.isPlayer1Turn) {
            this.countMovesPlayer1++;
            p.getHeader().highlightTurn(this.getGame().getPlayer2());
        }
        else {
            this.countMovesPlayer2++;
            p.getHeader().highlightTurn(this.getGame().getPlayer1());
        }
        this.isPlayer1Turn = !this.isPlayer1Turn;
        p.getHeader().swapPieces();
        p.getBoardPanel().repaint();
    }

    //AUXILIARY METHODS
    private void controlPieRule() {
        if (this.getGame().getPlayer1().getPieces() == Pieces.BLACK) {
            if (this.countMovesPlayer2 == 0) {
                this.togglePieRuleDialog(this.getGame().getPlayer2());
            }
        }
        else {
            if (this.countMovesPlayer1 == 0) {
                this.togglePieRuleDialog(this.getGame().getPlayer1());
            }
        }
    }

    private void toggleAlertDialog(String message) {
        AlertDialog alertDialog = new AlertDialog(message, this);
        alertDialog.setPreferredSize(new Dimension(this.side /2, this.side /8));
        alertDialog.setVisible(true);
    }

    private void toggleVictoryDialog(Player player) {
        VictoryDialog victoryDialog = new VictoryDialog(this, player);
        victoryDialog.setPreferredSize(new Dimension(this.side /2, this.side /8));
        victoryDialog.setVisible(true);
    }

    private void togglePieRuleDialog(Player player) {
        PieRuleDialog pieRuleDialog = new PieRuleDialog(this, player);
        pieRuleDialog.setPreferredSize(new Dimension(this.side /2, this.side /8));
        pieRuleDialog.setVisible(true);
    }

    private void toggleNoAvailableMovesDialog(Player player) {
        NoAvailableMovesDialog noAvailableMovesDialog = new NoAvailableMovesDialog(this, player);
        noAvailableMovesDialog.setPreferredSize(new Dimension(this.side /2, this.side /8));
        noAvailableMovesDialog.setVisible(true);
    }

    private static Player getPlayerWithBlackPieces(Player player1, Player player2) {
        if (player1.getPieces() == Pieces.BLACK) {
            return player1;
        }
        else if (player2.getPieces() == Pieces.BLACK) {
            return player2;
        }
        else {
            throw new IllegalArgumentException("No player with black pieces.");
        }
    }

    public Font getApplicationFont(boolean isBold) {
        int fontStyle;
        if (isBold) {
            fontStyle = Font.BOLD;
        }
        else {
            fontStyle = Font.PLAIN;
        }
        if (this.getHeight() <= 480) {
            return new Font("Monaco", fontStyle, 8);
        }
        else if (this.getHeight() <= 540) {
            return new Font("Monaco", fontStyle, 10);
        }
        else if (this.getHeight() <= 720) {
            return new Font("Monaco", fontStyle, 13);
        }
        else if (this.getHeight() <= 980) {
            return new Font("Monaco", fontStyle, 17);
        }
        else if (this.getHeight() <= 1200) {
            return new Font("Monaco", fontStyle, 21);
        }
        else {
            return new Font("Monaco", fontStyle, 26);
        }
    }

    public Dimension getDialogDimension() {
        if (this.screenHeight <= 480) {
            return new Dimension(200, 80);
        }
        else if (this.screenHeight <= 540) {
            return new Dimension(250, 100);
        }
        else if (this.screenHeight <= 720) {
            return new Dimension(280, 120);
        }
        else if (this.screenHeight <= 980) {
            return new Dimension(320, 140);
        }
        else if (this.screenHeight <= 1200) {
            return new Dimension(400, 160);
        }
        else {
            return new Dimension(500, 180);
        }
    }

    public Dimension getAlertDialogDimension() {
        if (this.screenHeight <= 480) {
            return new Dimension(500, 80);
        }
        else if (this.screenHeight <= 540) {
            return new Dimension(650, 100);
        }
        else if (this.screenHeight <= 720) {
            return new Dimension(750, 120);
        }
        else if (this.screenHeight <= 980) {
            return new Dimension(850, 140);
        }
        else if (this.screenHeight <= 1200) {
            return new Dimension(1000, 160);
        }
        else {
            return new Dimension(1200, 180);
        }
    }
}