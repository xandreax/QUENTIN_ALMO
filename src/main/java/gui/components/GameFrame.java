package gui.components;

import entities.*;
import exceptions.*;
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
    protected int shorterDimension;
    protected boolean isGameOn;
    protected Board board;
    protected Player player1;
    protected Player player2;
    protected int countMovesPlayer1;
    protected int countMovesPlayer2;
    protected Game game;
    protected HeaderGamePage header;
    protected boolean isPlayer1Turn;
    protected BoardPanel boardPanel;

    //CONSTRUCTORS
    public GameFrame() {
        super();
        //printAllAvailableFonts();
        //JFrame setup
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
        //this.getContentPane().setBackground(BACKGROUND_COLOR);
        ImageIcon logo = new ImageIcon("src/main/java/gui/logo.png");
        this.setIconImage(logo.getImage());
        this.shorterDimension = 9*Math.min(this.getWidth(), this.getHeight())/10;
        this.setLayout(new GridBagLayout());
        this.isGameOn = false;
        this.initWelcomePage();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                GameFrame.this.shorterDimension = 7*Math.min(GameFrame.this.getWidth(), GameFrame.this.getHeight())/8;
                GameFrame.this.getContentPane().removeAll();
                if (GameFrame.this.isGameOn) {
                    GameFrame.this.refreshGamePage(GameFrame.this.game, GameFrame.this.header, GameFrame.this.player1, GameFrame.this.player2);
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
    public boolean isGameOn() {
        return isGameOn;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void initWelcomePage() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(this.shorterDimension, this.shorterDimension));
        mainPanel.setSize(new Dimension(this.shorterDimension, this.shorterDimension));
        mainPanel.setOpaque(true);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
        mainPanel.setBackground(BOARD_COLOR);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.getContentPane().add(mainPanel, gbc);

        JLabel top = new JLabel();
        ImageIcon logo = new ImageIcon("src/main/java/gui/logo_big.png");

        top.setIcon(logo);
        //top.setIcon(scaleImageIcon(logo, this.mainPanel.getWidth()/4, this.mainPanel.getHeight()/5));

        top.setText("Quentin");
        top.setFont(new Font("Purisa", Font.BOLD, 34));
        top.setForeground(Color.WHITE);
        top.setOpaque(false);
        top.setHorizontalAlignment(SwingConstants.CENTER);
        top.setVerticalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbcTop = new GridBagConstraints();
        gbcTop.gridx = 0;
        gbcTop.gridy = 0;
        gbcTop.gridwidth = 1;
        gbcTop.gridheight = 1;
        gbcTop.weightx = 1;
        gbcTop.weighty = 0.5f;
        gbcTop.anchor = GridBagConstraints.CENTER;
        gbcTop.fill = GridBagConstraints.BOTH;
        mainPanel.add(top, gbcTop);

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 1;
        gbcBottom.gridwidth = 1;
        gbcBottom.gridheight = 1;
        gbcBottom.weightx = 1;
        gbcBottom.weighty = 0.5f;
        gbcBottom.anchor = GridBagConstraints.CENTER;
        gbcBottom.fill = GridBagConstraints.BOTH;
        GenericButton buttonStart = new GenericButton("Start game", this);
        buttonStart.setPreferredSize(new Dimension(mainPanel.getWidth()/2, 50));
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame.this.initNewGame();
            }
        });
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        gbcButton.gridwidth = 1;
        gbcButton.gridheight = 1;
        gbcButton.weightx = 0;
        gbcButton.weighty = 0;
        gbcButton.anchor = GridBagConstraints.CENTER;
        gbcButton.fill = GridBagConstraints.NONE;
        bottom.add(buttonStart, gbcButton);
        mainPanel.add(bottom, gbcBottom);
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
                                GameFrame.this.player1 = player1;
                                GameFrame.this.player2 = player2;
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

        JPanel panel = new JPanel();
        panel.setBackground(BOARD_COLOR);
        panel.setLayout(new GridBagLayout());

        this.header = new HeaderGamePage(this, player1, player2);
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;
        gbcHeader.weightx = 1;
        gbcHeader.weighty = 0.2;
        gbcHeader.anchor = GridBagConstraints.PAGE_START;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;
        gbcHeader.insets = new Insets(0, 0, 20, 0);
        panel.add(this.header, gbcHeader);

        this.boardPanel = new BoardPanel(this, this.game.getBoard(), 9*this.shorterDimension/10);
        GridBagConstraints gbcBoardPanel = new GridBagConstraints();
        gbcBoardPanel.gridx = 0;
        gbcBoardPanel.gridy = 1;
        gbcBoardPanel.weightx = 1;
        gbcBoardPanel.weighty = 0.8;
        gbcBoardPanel.anchor = GridBagConstraints.PAGE_END;

        this.getContentPane().removeAll();

        panel.add(this.boardPanel, gbcBoardPanel);
        this.getContentPane().add(panel);

        this.validate();
        this.isGameOn = true;
        this.isPlayer1Turn = true;

        this.header.highlightTurn(getPlayerWithBlackPieces(this.player1, this.player2));
    }

    public void closeGame() {
        if (this.isGameOn) {
            this.getContentPane().removeAll();
            this.isGameOn = false;
            this.initWelcomePage();
            this.validate();
        }
    }

    public void renderMove(Move move) {
        if (!this.isGameOn) {
            throw new IllegalStateException("There is not a game going on.");
        }
        else {
            this.boardPanel.updateHoverButtons();
            this.boardPanel.repaint();
        }
    }

    public void handleMove(BoardCoordinate boardCoordinate) {
        Player turnPlayer = null;
        if (this.isPlayer1Turn) {
            turnPlayer = this.player1;
            if (! this.game.getAvailableMoves(this.game.getBoard(), this.player1)) {
                this.toggleNoAvailableMovesDialog(this.player1);
                return;
            }
        }
        else {
            turnPlayer = this.player2;
            if (! this.game.getAvailableMoves(this.game.getBoard(), this.player2)) {
                this.toggleNoAvailableMovesDialog(this.player2);
                return;
            }
        }
        Move newMove = new Move(turnPlayer, boardCoordinate);
        try {
            this.game.move(newMove);
            this.renderMove(newMove);
            if (this.isPlayer1Turn) {
                this.countMovesPlayer1++;
                this.header.highlightTurn(this.player2);
            }
            else {
                this.countMovesPlayer2++;
                this.header.highlightTurn(this.player1);
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
        catch (VictoryException e){
            this.toggleVictoryDialog(turnPlayer);
        }
        this.controlPieRule();
    }

    private void refreshGamePage(Game game, HeaderGamePage header, Player player1, Player player2) {
        if (this.isGameOn) {

            JPanel panel = new JPanel();
            panel.setBackground(BOARD_COLOR);
            panel.setLayout(new GridBagLayout());

            GridBagConstraints gbcHeader = new GridBagConstraints();
            gbcHeader.gridx = 0;
            gbcHeader.gridy = 0;
            gbcHeader.weightx = 1;
            gbcHeader.weighty = 0.2;
            gbcHeader.anchor = GridBagConstraints.PAGE_START;
            gbcHeader.fill = GridBagConstraints.HORIZONTAL;
            gbcHeader.insets = new Insets(0, 0, 20, 0);
            panel.add(header, gbcHeader);

            this.boardPanel = new BoardPanel(this, game.getBoard(), 9*this.shorterDimension/10);
            GridBagConstraints gbcBoardPanel = new GridBagConstraints();
            gbcBoardPanel.gridx = 0;
            gbcBoardPanel.gridy = 1;
            gbcBoardPanel.weightx = 1;
            gbcBoardPanel.weighty = 0.8;
            gbcBoardPanel.anchor = GridBagConstraints.PAGE_END;

            panel.add(this.boardPanel, gbcBoardPanel);
            this.getContentPane().add(panel);

            this.validate();
            this.isGameOn = true;
        }
        else {
            throw new IllegalStateException("There is not a game going on.");
        }
    }

    public void doPieRule() {
        if (this.player1.getPieces() == Pieces.WHITE) {
            this.player1.setPieces(Pieces.BLACK);
            this.player2.setPieces(Pieces.WHITE);
        }
        else {
            this.player1.setPieces(Pieces.WHITE);
            this.player2.setPieces(Pieces.BLACK);
        }
        for (int i = 0; i < this.game.getBoard().getDIMENSION(); i++) {
            for (int j = 0; j < this.game.getBoard().getDIMENSION(); j++) {
                if (this.game.getBoard().getMatrix()[i][j] == Pieces.BLACK) {
                    this.game.getBoard().getMatrix()[i][j] = Pieces.WHITE;
                }
                else if (this.game.getBoard().getMatrix()[i][j] == Pieces.WHITE) {
                    this.game.getBoard().getMatrix()[i][j] = Pieces.BLACK;
                }
                else {
                    //
                }
            }
        }
        if (this.isPlayer1Turn) {
            this.countMovesPlayer1++;
            this.header.highlightTurn(this.player2);
        }
        else {
            this.countMovesPlayer2++;
            this.header.highlightTurn(this.player1);
        }
        this.isPlayer1Turn = !this.isPlayer1Turn;
        this.header.swapPieces();
        this.boardPanel.repaint();
    }

    //AUXILIARY METHODS
    private void controlPieRule() {
        if (this.player1.getPieces() == Pieces.BLACK) {
            if (this.countMovesPlayer2 == 0) {
                this.togglePieRuleDialog(this.player2);
            }
        }
        else {
            if (this.countMovesPlayer1 == 0) {
                this.togglePieRuleDialog(this.player1);
            }
        }
    }

    private void toggleAlertDialog(String message) {
        AlertDialog alertDialog = new AlertDialog(message, this);
        alertDialog.setPreferredSize(new Dimension(this.shorterDimension/2, this.shorterDimension/8));
        alertDialog.setVisible(true);
    }

    private void toggleVictoryDialog(Player player) {
        VictoryDialog victoryDialog = new VictoryDialog(this, player);
        victoryDialog.setPreferredSize(new Dimension(this.shorterDimension/2, this.shorterDimension/8));
        victoryDialog.setVisible(true);
    }

    private void togglePieRuleDialog(Player player) {
        PieRuleDialog pieRuleDialog = new PieRuleDialog(this, player);
        pieRuleDialog.setPreferredSize(new Dimension(this.shorterDimension/2, this.shorterDimension/8));
        pieRuleDialog.setVisible(true);
    }

    private void toggleNoAvailableMovesDialog(Player player) {
        NoAvailableMovesDialog noAvailableMovesDialog = new NoAvailableMovesDialog(this, player);
        noAvailableMovesDialog.setPreferredSize(new Dimension(this.shorterDimension/2, this.shorterDimension/8));
        noAvailableMovesDialog.setVisible(true);
    }

    private static void printAllAvailableFonts() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = ge.getAvailableFontFamilyNames();
        for (String s: fontFamilies) System.out.println(s);
    }

    private static ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
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

    public Font getApplicationFont() {
        if (this.getHeight() <= 480) {
            return new Font("Monaco", Font.PLAIN, 8);
        }
        else if (this.getHeight() <= 540) {
            return new Font("Monaco", Font.PLAIN, 10);
        }
        else if (this.getHeight() <= 720) {
            return new Font("Monaco", Font.PLAIN, 13);
        }
        else if (this.getHeight() <= 980) {
            return new Font("Monaco", Font.PLAIN, 17);
        }
        else if (this.getHeight() <= 1200) {
            return new Font("Monaco", Font.PLAIN, 21);
        }
        else {
            return new Font("Monaco", Font.PLAIN, 26);
        }
    }

    public Font getBoldApplicationFont() {
        if (this.getHeight() <= 480) {
            return new Font("Monaco", Font.BOLD, 8);
        }
        else if (this.getHeight() <= 540) {
            return new Font("Monaco", Font.BOLD, 10);
        }
        else if (this.getHeight() <= 720) {
            return new Font("Monaco", Font.BOLD, 13);
        }
        else if (this.getHeight() <= 980) {
            return new Font("Monaco", Font.BOLD, 17);
        }
        else if (this.getHeight() <= 1200) {
            return new Font("Monaco", Font.BOLD, 21);
        }
        else {
            return new Font("Monaco", Font.BOLD, 26);
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
            return new Dimension(450, 180);
        }
    }

    public Dimension getAlertDialogDimension() {
        if (this.screenHeight <= 480) {
            return new Dimension(200, 80);
        }
        else if (this.screenHeight <= 540) {
            return new Dimension(300, 100);
        }
        else if (this.screenHeight <= 720) {
            return new Dimension(400, 120);
        }
        else if (this.screenHeight <= 980) {
            return new Dimension(500, 140);
        }
        else if (this.screenHeight <= 1200) {
            return new Dimension(600, 160);
        }
        else {
            return new Dimension(700, 180);
        }
    }
}