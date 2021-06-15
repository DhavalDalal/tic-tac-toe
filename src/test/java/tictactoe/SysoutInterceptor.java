package tictactoe;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SysoutInterceptor extends PrintStream {
    private List<String> messages = new ArrayList<>();

    public SysoutInterceptor(PrintStream original) {
        super(original, true);
    }

    @Override
    public void println(String message) {
        messages.add(message);
    }

    public boolean contains(String message) {
        return messages.stream().anyMatch(m -> m.equals(message));
    }
}
