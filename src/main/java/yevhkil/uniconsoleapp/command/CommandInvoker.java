package yevhkil.uniconsoleapp.command;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.request.RequestHandler;
import yevhkil.uniconsoleapp.response.Response;
import yevhkil.uniconsoleapp.response.responses.DefaultResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandInvoker {
    private final List<Command> commandList;
    private final RequestHandler requestHandler;

    public Response executeCommand(String input) {
        Response response = new DefaultResponse(commandList);
        Optional<Command> optionalCommand = commandList.stream()
                .filter(command -> requestHandler.isRequestApplicableByPattern(
                        input, command.getCommandPattern()))
                .findFirst();

        if (optionalCommand.isPresent()) {
            response = optionalCommand.get().execute(input);
            return response;
        }

        log.error(response.getResponseBody());
        return response;
    }
}
