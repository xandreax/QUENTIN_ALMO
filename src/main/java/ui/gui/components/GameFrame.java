package ui.gui.components;

import entities.Board;
import entities.BoardCoordinate;
import entities.Pieces;
import entities.Player;
import exceptions.InvalidCoordinateException;
import exceptions.UnsupportedPiecesForPlayerException;
import exceptions.UsernameTooShortException;
import logic.UIGame;
import ui.gui.components.gamepage.NoAvailableMovesDialog;
import ui.gui.components.gamepage.PanelGamePage;
import ui.gui.components.gamepage.PieRuleDialog;
import ui.gui.components.gamepage.VictoryDialog;
import ui.gui.components.welcomepage.DialogForPlayers;
import ui.gui.components.welcomepage.DiscardButtonActionListener;
import ui.gui.components.welcomepage.PanelWelcomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameFrame extends JFrame {
    public static final int HEIGHT_1 = 480;
    public static final int HEIGHT_2 = 540;
    public static final int HEIGHT_3 = 720;
    public static final int HEIGHT_4 = 980;
    public static final int HEIGHT_5 = 1200;
    public static final int WIDTH = 720;
    //FIELDS
    public static Color BOARD_COLOR = new Color(0, 105, 9);
    protected int countMovesPlayer1;
    protected int countMovesPlayer2;
    private static final Color BACKGROUND_COLOR = new Color(0, 141, 8);
    private final int screenHeight;
    private int side;
    private boolean isGameOn;
    private final UIGame uiGame;

    //CONSTRUCTORS
    public GameFrame(UIGame uiGame) {
        super();
        this.uiGame = uiGame;
        this.setTitle("Quentin");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double sw = screenSize.getWidth();
        double sh = screenSize.getHeight();
        int screenWidth = (int) sw;
        this.screenHeight = (int) sh;
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT_2));
        this.setSize(new Dimension(screenWidth, this.screenHeight));
        this.setPreferredSize(new Dimension(screenWidth, this.screenHeight));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.setResizable(true);
        ImageIcon logo = new ImageIcon("src/main/java/ui/gui/logo.png");
        this.setIconImage(logo.getImage());
        this.side = 9 * Math.min(this.getWidth(), this.getHeight()) / 10;
        this.setLayout(new GridBagLayout());
        this.isGameOn = false;
        this.initWelcomePage();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                GameFrame.this.side = 7 * Math.min(GameFrame.this.getWidth(), GameFrame.this.getHeight()) / 8;
                GameFrame.this.getContentPane().removeAll();
                if (GameFrame.this.isGameOn) {
                    GameFrame.this.refreshGamePage();
                } else {
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

    public UIGame getGame() {
        return uiGame;
    }

    public void initWelcomePage() {
        PanelWelcomePage panelWelcomePage = new PanelWelcomePage(this, uiGame);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.getContentPane().add(panelWelcomePage, gbc);
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        DialogForPlayers dialog1 = new DialogForPlayers(Pieces.BLACK, this);
        Set<DialogForPlayers> temp = new HashSet<>();
        temp.add(dialog1);
        dialog1.getButtonDiscard().addActionListener(new DiscardButtonActionListener(temp, this));
        dialog1.getButtonOk().addActionListener(e -> {
            try {
                Player player1 = new Player(dialog1.getTextField().getText(), Pieces.BLACK);
                players.add(player1);
                DialogForPlayers dialog2 = new DialogForPlayers(Pieces.WHITE, GameFrame.this);
                temp.add(dialog2);
                dialog2.getButtonDiscard().addActionListener(new DiscardButtonActionListener(temp, GameFrame.this));
                dialog2.getButtonOk().addActionListener(e1 -> {
                    try {
                        Player player2 = new Player(dialog2.getTextField().getText(), Pieces.WHITE);
                        players.add(player2);
                        GameFrame.this.countMovesPlayer1 = 0;
                        GameFrame.this.countMovesPlayer2 = 0;
                        dialog2.dispose();
                    } catch (UnsupportedPiecesForPlayerException | UsernameTooShortException exc) {
                        GameFrame.this.toggleAlertDialog(exc.getMessage());
                    }
                });
                dialog1.dispose();
                dialog2.setVisible(true);
            } catch (UnsupportedPiecesForPlayerException | UsernameTooShortException ex) {
                GameFrame.this.toggleAlertDialog(ex.getMessage());
            }
        });
        dialog1.setVisible(true);
        return players;
    }

    public void initPanelGamePage() {
        this.getContentPane().removeAll();
        PanelGamePage panelGamePage = new PanelGamePage(this);
        this.getContentPane().add(panelGamePage);
        this.validate();
        this.isGameOn = true;
        PanelGamePage p = (PanelGamePage) this.getContentPane().getComponent(0);
        p.getHeader().highlightTurn(getPlayerWithBlackPieces(uiGame.getPlayer1(), uiGame.getPlayer2()));
    }

    public void renderMove() throws InvalidCoordinateException {
        if (!this.isGameOn) {
            throw new IllegalStateException("There is not a game going on.");
        } else {
            PanelGamePage p = (PanelGamePage) this.getContentPane().getComponent(0);
            p.getBoardPanel().updateHoverButtons();
            p.getBoardPanel().repaint();
        }
    }

    public void handleMove(BoardCoordinate move) {
        PanelGamePage p = (PanelGamePage) this.getContentPane().getComponent(0);
        uiGame.handleMove(move, p);
    }


    private void refreshGamePage() {
        if (this.isGameOn) {
            PanelGamePage panelGamePage = new PanelGamePage(this);
            this.getContentPane().add(panelGamePage);
            this.validate();
            this.isGameOn = true;
        } else {
            throw new IllegalStateException("There is not a game going on.");
        }
    }

    public Board getBoard() {
        return uiGame.getBoard();
    }

    public void toggleAlertDialog(String message) {
        AlertDialog alertDialog = new AlertDialog(message, this);
        alertDialog.setPreferredSize(new Dimension(this.side / 2, this.side / 8));
        alertDialog.setVisible(true);
    }

    public void toggleVictoryDialog(Player player) {
        VictoryDialog victoryDialog = new VictoryDialog(this, player);
        victoryDialog.setPreferredSize(new Dimension(this.side / 2, this.side / 8));
        victoryDialog.setVisible(true);
    }

    public boolean togglePieRuleDialog(Player player) {
        final boolean[] pieRuleBoolean = new boolean[1];
        PieRuleDialog pieRuleDialog = new PieRuleDialog(this, player);
        pieRuleDialog.getButtonNo().addActionListener(e -> {
            pieRuleDialog.dispose();
            pieRuleBoolean[0] = false;
        });
        pieRuleDialog.getButtonYes().addActionListener(e -> {
            pieRuleDialog.dispose();
            pieRuleBoolean[0] = true;
        });
        pieRuleDialog.setPreferredSize(new Dimension(this.side / 2, this.side / 8));
        pieRuleDialog.setVisible(true);
        return pieRuleBoolean[0];
    }

    public void toggleNoAvailableMovesDialog(Player player) {
        NoAvailableMovesDialog noAvailableMovesDialog = new NoAvailableMovesDialog(this, player);
        noAvailableMovesDialog.setPreferredSize(new Dimension(this.side / 2, this.side / 8));
        noAvailableMovesDialog.setVisible(true);
    }

    private static Player getPlayerWithBlackPieces(Player player1, Player player2) {
        if (player1.isBlackPlayer()) {
            return player1;
        } else if (player2.isBlackPlayer()) {
            return player2;
        } else {
            throw new IllegalArgumentException("No player with black pieces.");
        }
    }

    public Font getApplicationFont(boolean isBold) {
        int fontStyle;
        if (isBold) {
            fontStyle = Font.BOLD;
        } else {
            fontStyle = Font.PLAIN;
        }
        if (this.getHeight() <= HEIGHT_1) {
            return new Font("Monaco", fontStyle, 8);
        } else if (this.getHeight() <= HEIGHT_2) {
            return new Font("Monaco", fontStyle, 10);
        } else if (this.getHeight() <= HEIGHT_3) {
            return new Font("Monaco", fontStyle, 13);
        } else if (this.getHeight() <= HEIGHT_4) {
            return new Font("Monaco", fontStyle, 17);
        } else if (this.getHeight() <= HEIGHT_5) {
            return new Font("Monaco", fontStyle, 21);
        } else {
            return new Font("Monaco", fontStyle, 26);
        }
    }

    public Dimension getDialogDimension() {
        if (this.screenHeight <= HEIGHT_1) {
            return new Dimension(200, 80);
        } else if (this.screenHeight <= HEIGHT_2) {
            return new Dimension(250, 100);
        } else if (this.screenHeight <= HEIGHT_3) {
            return new Dimension(280, 120);
        } else if (this.screenHeight <= HEIGHT_4) {
            return new Dimension(320, 140);
        } else if (this.screenHeight <= HEIGHT_5) {
            return new Dimension(400, 160);
        } else {
            return new Dimension(500, 180);
        }
    }

    public Dimension getAlertDialogDimension() {
        if (this.screenHeight <= HEIGHT_1) {
            return new Dimension(500, 80);
        } else if (this.screenHeight <= HEIGHT_2) {
            return new Dimension(650, 100);
        } else if (this.screenHeight <= HEIGHT_3) {
            return new Dimension(750, 120);
        } else if (this.screenHeight <= HEIGHT_4) {
            return new Dimension(850, 140);
        } else if (this.screenHeight <= HEIGHT_5) {
            return new Dimension(1000, 160);
        } else {
            return new Dimension(1200, 180);
        }
    }
}