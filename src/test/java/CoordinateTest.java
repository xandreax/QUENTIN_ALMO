import entities.Coordinate;
import exceptions.InvalidCoordinateException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class CoordinateTest {
    //METHODS
    @Test
    public void testFirstConstructor() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(1, 2);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(0, 0);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(9, 8);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(11, 12);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(12, 12);
            }
        });
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(-1, 12);
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(-1, 13);
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(9, 99);
            }
        });
    }

    @Test
    public void testSecondConstructor() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('a', 'C');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('E', 'F');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('l', 'k');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('I', 'i');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('g', 'E');
            }
        });
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('A', 'x');
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('&', '!');
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('w', 'f');
            }
        });
    }

    @Test
    public void testThirdConstructor() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('a', 3);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('E', 6);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('l', 7);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('I', 8);
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('l', 12);
            }
        });
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('A', -5);
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('&', 1001);
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate('w', 4);
            }
        });
    }

    @Test
    public void testFourthConstructor() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(3, 'b');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(0, 'L');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(7, 'K');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(12, 'M');
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(11, 'h');
            }
        });
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(9087, 'D');
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(9, 'q');
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate(14, 'J');
            }
        });
    }

    @Test
    public void testFifthConstructor() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate("a9");
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate("89");
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate("7h");
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate("BD");
            }
        });
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate("mM");
            }
        });
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate("Pr");
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate("errore");
            }
        });
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Coordinate coordinate = new Coordinate("99JHp");
            }
        });
    }
}
