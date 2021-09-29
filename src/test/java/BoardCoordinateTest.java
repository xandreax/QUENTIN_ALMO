import entities.BoardCoordinate;
import exceptions.InvalidCoordinateException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class BoardCoordinateTest {
    //METHODS
    @Test
    public void testFirstConstructor() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(1, 2);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(0, 0);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(9, 8);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(11, 12);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(12, 12);
            }
        });
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(-1, 12);
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(-1, 13);
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(9, 99);
            }
        });
    }

    @Test
    public void testSecondConstructor() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('a', 'C');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('E', 'F');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('l', 'k');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('I', 'i');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('g', 'E');
            }
        });
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('A', 'x');
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('&', '!');
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('w', 'f');
            }
        });
    }

    @Test
    public void testThirdConstructor() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('a', 3);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('E', 10);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('l', 7);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('I', 8);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('l', 12);
            }
        });
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('A', -5);
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('&', 1001);
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate('w', 4);
            }
        });
    }

    @Test
    public void testFourthConstructor() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(3, 'b');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(0, 'L');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(7, 'K');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(12, 'M');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(11, 'h');
            }
        });
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(9087, 'D');
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(9, 'q');
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(14, 'J');
            }
        });
    }

    @Test
    public void testFifthConstructor() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("a9");
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("c9");
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("h7");
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("B10");
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("m8");
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("M12");
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("l11");
            }
        });
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("11n");
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("Pr");
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("errore");
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("99JHp");
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("5h");
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("4Zsd");
            }
        });
    }
}
