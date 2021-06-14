package tictactoe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GFGTest {
    private static Stream<Arguments> provideInputPositionsForWinningRowsAndColumnsAndDiagonals() {
        return Stream.of(
                Arguments.of("Same symbol on Column 1", "1\n2\n4\n5\n7\n", "X"),
                Arguments.of("Same symbol on Column 2", "2\n1\n5\n6\n8\n", "X"),
                Arguments.of("Same symbol on Column 3", "3\n2\n6\n5\n8\n4\n9\n", "X"),
                Arguments.of("Same symbol on Column 1", "2\n1\n3\n4\n5\n7\n", "O"),
                Arguments.of("Same symbol on Column 2", "1\n2\n9\n5\n3\n8\n", "O"),
                Arguments.of("Same symbol on Column 3", "1\n3\n5\n6\n8\n7\n4\n9\n", "O"),
                Arguments.of("Same symbol on Row 1", "1\n4\n2\n5\n3\n", "X"),
                Arguments.of("Same symbol on Row 2", "4\n1\n5\n2\n6\n", "X"),
                Arguments.of("Same symbol on Row 3", "7\n2\n8\n5\n6\n4\n9\n", "X"),
                Arguments.of("Same symbol on Row 1", "1\n4\n2\n5\n3\n", "O"),
                Arguments.of("Same symbol on Row 2", "4\n1\n5\n2\n6\n", "O"),
                Arguments.of("Same symbol on Row 3", "7\n2\n8\n5\n6\n4\n9\n", "O"),
                Arguments.of("Same symbol on Diagonal \\", "7\n2\n8\n5\n6\n4\n9\n", "X"),
                Arguments.of("Same symbol on Diagonal /", "1\n4\n2\n5\n3\n", "O"),
                Arguments.of("Same symbol on Diagonal \\", "4\n1\n5\n2\n6\n", "O"),
                Arguments.of("Same symbol on Diagonal /", "7\n2\n8\n5\n6\n4\n9\n", "O")
        );
    }

    // {index} will be replaced with the invocation index. For the first execution is 1, for the second is 2, and so on.
    // {arguments} is a placeholder for the complete, comma-separated list of arguments.
    // {0}, {1}, ... are placeholders for individual arguments.
    @ParameterizedTest(name = "Winning Test #{index} => {0}, inputPositions are {1}, winner is {2}")
    @MethodSource("provideInputPositionsForWinningRowsAndColumnsAndDiagonals")
    public void aPlayerWins(String message, String inputPositions, String winner) {
        GFG.runGame(new ByteArrayInputStream(inputPositions.getBytes()));
        assertEquals(GFG.checkWinner(), winner, message);
    }

    @Test
    public void drawBetweenPlayers() {
        GFG.runGame(new ByteArrayInputStream("1\n5\n2\n3\n7\n4\n6\n8\n9\n".getBytes()));
        assertEquals(GFG.checkWinner(), "draw");
    }
}