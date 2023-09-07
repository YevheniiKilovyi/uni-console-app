package yevhkil.uniconsoleapp.response.responses;

import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.response.Response;

@Component
@NoArgsConstructor
public class DefaultResponse implements Response {
    private static final StringBuilder RESPONSE_TEMPLATE =
            new StringBuilder("\nUnknown command. Available commands:\n");

    private String responseBody;

    public DefaultResponse(List<Command> commandList) {
        commandList.forEach(command -> RESPONSE_TEMPLATE
                .append(command.getCommandTemplate())
                .append("\n"));
        this.responseBody = RESPONSE_TEMPLATE.toString();
    }

    @Override
    public String getResponseBody() {
        return this.responseBody;
    }

    @Override
    public String getNegativeResponseBody() {
        return this.responseBody;
    }
}
