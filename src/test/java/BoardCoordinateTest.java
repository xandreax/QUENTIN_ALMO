import entities.BoardCoordinate;
import exceptions.InvalidCoordinateException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class BoardCoordinateTest {

    //METHODS
    @Test
    public void testFirstConstructor1() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(1, 2);
            }
        });
    }

    @Test
    public void testFirstConstructor2() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(0, 0);
            }
        });
    }

    @Test
    public void testFirstConstructor3() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(9, 8);
            }
        });
    }

    @Test
    public void testFirstConstructor4() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(11, 12);
            }
        });
    }

    @Test
    public void testFirstConstructor5() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(12, 12);
            }
        });
    }

    @Test
    public void testFirstConstructor6() {
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(-1, 12);
            }
        });
    }

    @Test
    public void testFirstConstructor7() {
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate(-1, 13);
            }
        });
    }

    @Test
    public void testFirstConstructor8() {
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
                BoardCoordinate boardCoordinate = new BoardCoordinate("a9");
            }
        });
    }

    @Test
    public void testSecondConstructor2() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("c9");
            }
        });
    }

    @Test
    public void testSecondConstructor3() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("h7");
            }
        });
    }
    @Test
    public void testSecondConstructor4() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("B10");
            }
        });
    }
    @Test
    public void testSecondConstructor5() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("m8");
            }
        });
    }
    @Test
    public void testSecondConstructor6() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("M12");
            }
        });
    }
    @Test
    public void testSecondConstructor7() {
        Assertions.assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("l11");
            }
        });
    }
    @Test
    public void testSecondConstructor8() {
        //Intentionally wrong
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("11n");
            }
        });
    }
    @Test
    public void testSecondConstructor9() {
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("Pr");
            }
        });
    }
    @Test
    public void testSecondConstructor10() {
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("errore");
            }
        });
    }

    @Test
    public void testSecondConstructor11() {
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("99JHp");
            }
        });
    }

    @Test
    public void testSecondConstructor12() {
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("5h");
            }
        });
    }

    @Test
    public void testSecondConstructor13() {
        Assertions.assertThrows(InvalidCoordinateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BoardCoordinate boardCoordinate = new BoardCoordinate("4Zsd");
            }
        });
    }

}
