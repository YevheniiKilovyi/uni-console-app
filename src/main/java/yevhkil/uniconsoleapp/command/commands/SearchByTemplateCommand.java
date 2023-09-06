package yevhkil.uniconsoleapp.command.commands;

import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;
import yevhkil.uniconsoleapp.request.RequestHandler;
import yevhkil.uniconsoleapp.service.lector.LectorService;
import yevhkil.uniconsoleapp.util.ArgumentsExtractor;

@Component
@RequiredArgsConstructor
public class SearchByTemplateCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("Global\\s+search\\s+by\\s+(\\S+.*)?");

    private final LectorService lectorService;
    private final RequestHandler requestHandler;
    private final ArgumentsExtractor argumentsExtractor;

    @Override
    public boolean execute(String input) {
        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            System.out.println("Invalid command format. Use: Global search by {template}");

        }

        String template = argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        List<LectorResponseDto> matchingLectors = lectorService.findLectorsByTemplate(template);

        if (!matchingLectors.isEmpty()) {
            StringJoiner result = new StringJoiner(", ");
            for (LectorResponseDto lector : matchingLectors) {
                result.add(lector.getFirstName() + " " + lector.getLastName());
            }
            System.out.println(result);
            return true;
        }
        System.out.println("No matching lectors found for the template: " + template);
        return false;
    }
}
