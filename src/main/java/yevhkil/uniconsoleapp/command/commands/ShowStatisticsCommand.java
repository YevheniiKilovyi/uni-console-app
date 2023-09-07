package yevhkil.uniconsoleapp.command.commands;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.request.RequestHandler;
import yevhkil.uniconsoleapp.response.Response;
import yevhkil.uniconsoleapp.response.responses.ShowStatisticsResponse;
import yevhkil.uniconsoleapp.service.department.DepartmentService;
import yevhkil.uniconsoleapp.util.ArgumentsExtractor;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShowStatisticsCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("Show\\s+(.*)\\s+statistics");
    private static final String COMMAND_TEMPLATE = "Show {department_name} statistics";
    private static final String ASSISTANT_DEGREE = "assistant";
    private static final String ASSOCIATE_PROFESSOR_DEGREE = "associate_professor";
    private static final String PROFESSOR_DEGREE = "professor";

    private final DepartmentService departmentService;
    private final RequestHandler requestHandler;
    private final ArgumentsExtractor argumentsExtractor;

    @Override
    public Response execute(String input) {
        Response response = new ShowStatisticsResponse();

        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            return response;
        }

        String departmentName =
                argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        Integer assistantCount = departmentService.getDegreeCount(departmentName, ASSISTANT_DEGREE);
        Integer associateProfessorsCount =
                departmentService.getDegreeCount(departmentName, ASSOCIATE_PROFESSOR_DEGREE);
        Integer professorsCount =
                departmentService.getDegreeCount(departmentName, PROFESSOR_DEGREE);

        if (assistantCount > 0 || associateProfessorsCount > 0 || professorsCount > 0) {
            response = new ShowStatisticsResponse(assistantCount, associateProfessorsCount,
                    professorsCount);
            log.info(response.getResponseBody());
            return response;
        }
        log.warn(response.getNegativeResponseBody());
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
