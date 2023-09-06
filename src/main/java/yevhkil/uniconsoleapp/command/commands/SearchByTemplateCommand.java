package yevhkil.uniconsoleapp.command.commands;

import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;
import yevhkil.uniconsoleapp.request.RequestHandler;
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
    public void execute(String input) {
        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            return;
        }

        String template = argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        List<LectorResponseDto> matchingLectors = lectorService.findLectorsByTemplate(template);

        if (!matchingLectors.isEmpty()) {
            StringJoiner result = new StringJoiner(", ");
            for (LectorResponseDto lector : matchingLectors) {
                result.add(lector.getFirstName() + " " + lector.getLastName());
            }
            System.out.println(result.toString());
            return;
        }
        log.info("No matching lectors found for the template: " + template);
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
