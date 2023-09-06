package yevhkil.uniconsoleapp.command;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.request.RequestHandler;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandInvoker {
    private final List<Command> commandList;
    private final RequestHandler requestHandler;

    public void executeCommand(String input) {
        Optional<Command> optionalCommand = commandList.stream()
                .filter(command -> requestHandler.isRequestApplicableByPattern(
                                input, command.getCommandPattern()))
                        .findFirst();

        if (optionalCommand.isPresent()) {
            optionalCommand.get().execute(input);
            return;
        }

        StringBuilder stringBuilder = new StringBuilder("\nUnknown command. Available commands:\n");
        commandList.forEach(command -> stringBuilder
                        .append(command.getCommandTemplate())
                        .append("\n"));
        log.error(stringBuilder.toString());
    }
}
