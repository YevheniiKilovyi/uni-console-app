package yevhkil.uniconsoleapp.command;

import java.util.regex.Pattern;
import yevhkil.uniconsoleapp.response.Response;

public interface Command {
    Response execute(String input);

    Pattern getCommandPattern();

    String getCommandTemplate();
}
