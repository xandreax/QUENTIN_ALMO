public class ConsoleGameTest {/*
    //FIELDS
    public static Player player1;
    public static Player player2;
    public static Player player3;
    public static ConsoleGame consoleGame1;
    public static ConsoleGame consoleGame2;
    public static ConsoleGame consoleGame3;
    public static BoardShellPrinter printer1;
    public static BoardShellPrinter printer2;
    public static BoardShellPrinter printer3;

    //TODO: refactor these tests
    //METHODS
    @Before
    public void initGame() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                player1 = new Player("1mnbvboekmg9", Pieces.WHITE);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                player2 = new Player("239f9rugf", Pieces.BLACK);
            }
        });
        Assertions.assertThrows(UnsupportedPiecesForPlayerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                player3 = new Player("mnbcv92340", Pieces.NONE);
            }
        });
        consoleGame1 = new ConsoleGame(player1, player2);
        consoleGame2 = new ConsoleGame(player1, player3);
        consoleGame3 = new ConsoleGame(player2, player3);
        printer1 = consoleGame1.getBoard().getPrinter();
        printer2 = consoleGame2.getBoard().getPrinter();
        printer3 = consoleGame3.getBoard().getPrinter();
    }

    @Test
    public void testGameEquality() {
        Assertions.assertNotEquals(consoleGame2, consoleGame1);
        Assertions.assertNotEquals(consoleGame1, consoleGame2);
        Assertions.assertNotEquals(consoleGame1, consoleGame3);
        Assertions.assertNotEquals(consoleGame3, consoleGame1);
        Assertions.assertNotEquals(consoleGame2, consoleGame3);
        Assertions.assertNotEquals(consoleGame3, consoleGame2);
        Assertions.assertEquals(consoleGame1, consoleGame1);
        Assertions.assertEquals(consoleGame2, consoleGame2);
        Assertions.assertEquals(consoleGame3, consoleGame3);
    }*/
}
