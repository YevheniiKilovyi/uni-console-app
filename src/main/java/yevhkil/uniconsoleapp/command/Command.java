package yevhkil.uniconsoleapp.command;

import java.util.regex.Pattern;

public interface Command {
    void execute(String input);

    Pattern getCommandPattern();

    String getCommandTemplate();
}
