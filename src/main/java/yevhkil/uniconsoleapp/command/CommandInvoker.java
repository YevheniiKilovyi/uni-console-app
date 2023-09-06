package yevhkil.uniconsoleapp.command;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandInvoker {
    private final List<Command> commandList;

    public void executeCommand(String input) {
        Optional<Command> optionalCommand =
                commandList.stream().filter(command -> command.execute(input)).findFirst();
        if (optionalCommand.isEmpty()) {
            System.out.println("Invalid command. Please try again.");
        }
    }
}
