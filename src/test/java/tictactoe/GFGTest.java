package tictactoe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Test was not present originally on: https://www.geeksforgeeks.org/tic-tac-toe-game-in-java/
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
                Arguments.of("Same symbol on Row 1", "7\n1\n5\n2\n9\n3\n", "O"),
                Arguments.of("Same symbol on Row 2", "1\n4\n2\n5\n9\n6\n", "O"),
                Arguments.of("Same symbol on Row 3", "2\n7\n5\n8\n3\n9\n", "O"),
                Arguments.of("Same symbol on Diagonal \\", "1\n2\n5\n4\n9\n", "X"),
                Arguments.of("Same symbol on Diagonal \\", "3\n1\n2\n5\n4\n9\n", "O"),
                Arguments.of("Same symbol on Diagonal /", "3\n4\n5\n6\n7\n", "X"),
                Arguments.of("Same symbol on Diagonal /", "1\n3\n4\n5\n6\n7\n", "O")
        );
    }

    // {index} will be replaced with the invocation index. For the first execution is 1, for the second is 2, and so on.
    // {arguments} is a placeholder for the complete, comma-separated list of arguments.
    // {0}, {1}, ... are placeholders for individual arguments.
    @ParameterizedTest(name = "aPlayerWins#{index} => {0}, inputPositions are {1}, winner is {2}")
    @MethodSource("provideInputPositionsForWinningRowsAndColumnsAndDiagonals")
    public void aPlayerWins(String message, String inputPositions, String expectedWinner) {
        SysoutCollector collector = new SysoutCollector(System.out);
        System.setOut(collector);

        GFG.runGame(new ByteArrayInputStream(inputPositions.getBytes()));
        assertTrue(collector.contains(String.format("Congratulations! %s's have won! Thanks for playing.", expectedWinner)), message);
    }

    @Test
    public void drawBetweenPlayers() {
        SysoutCollector collector = new SysoutCollector(System.out);
        System.setOut(collector);

        GFG.runGame(new ByteArrayInputStream("1\n5\n2\n3\n7\n4\n6\n8\n9\n".getBytes()));
        assertTrue(collector.contains("It's a draw! Thanks for playing."));
    }

    @Test
    public void doesNotAllowTakingASlotTwice() {
        // Given
        SysoutCollector collector = new SysoutCollector(System.out);
        System.setOut(collector);
        final ByteArrayInputStream input = new ByteArrayInputStream("1\n1\n".getBytes());
        try {
            // When
            GFG.runGame(input);
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(collector.contains("Slot already taken; re-enter slot number:"));
        }
    }

    @Test
    public void acceptsValidSlotNumbersOnly() {
        // Given
        SysoutCollector collector = new SysoutCollector(System.out);
        System.setOut(collector);
        final ByteArrayInputStream input = new ByteArrayInputStream("10\n".getBytes());

        try {
            // When
            GFG.runGame(input);
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(collector.contains("Invalid input; re-enter slot number:"));
        }
    }

    @Test
    public void doesNotAcceptNonNumericCharactersForASlot() {
        // Given
        SysoutCollector collector = new SysoutCollector(System.out);
        System.setOut(collector);
        final ByteArrayInputStream input = new ByteArrayInputStream("abc\n".getBytes());

        try {
            // When
            GFG.runGame(input);
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(collector.contains("Invalid input; re-enter slot number:"));
        }
    }
}
