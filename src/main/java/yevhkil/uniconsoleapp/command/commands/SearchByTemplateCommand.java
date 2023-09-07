package yevhkil.uniconsoleapp.command.commands;

import java.util.List;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;
import yevhkil.uniconsoleapp.request.RequestHandler;
import yevhkil.uniconsoleapp.response.Response;
import yevhkil.uniconsoleapp.response.responses.SearchByTemplateResponse;
import yevhkil.uniconsoleapp.service.lector.LectorService;
import yevhkil.uniconsoleapp.util.ArgumentsExtractor;

@Component
@RequiredArgsConstructor
@Slf4j
public class SearchByTemplateCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("Global\\s+search\\s+by\\s+(\\S+.*)?");
    private static final String COMMAND_TEMPLATE = "Global search by {template}";
    private final LectorService lectorService;
    private final RequestHandler requestHandler;
    private final ArgumentsExtractor argumentsExtractor;

    @Override
    public Response execute(String input) {
        Response response = new SearchByTemplateResponse();

        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            return response;
        }

        String template = argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        List<LectorResponseDto> matchingLectors = lectorService.findLectorsByTemplate(template);

        if (!matchingLectors.isEmpty()) {
            response = new SearchByTemplateResponse(matchingLectors);
            log.info(response.getResponseBody());
            return response;
        }
        log.warn(new SearchByTemplateResponse().getNegativeResponseBody() + template);
        return response;
    }

    @Override
    public Pattern getCommandPattern() {
        return PATTERN;
    }

    @Override
    public String getCommandTemplate() {
        return COMMAND_TEMPLATE;
    }

}
