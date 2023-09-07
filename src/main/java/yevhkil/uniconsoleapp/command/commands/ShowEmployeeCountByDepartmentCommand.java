package yevhkil.uniconsoleapp.command.commands;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.request.RequestHandler;
import yevhkil.uniconsoleapp.response.Response;
import yevhkil.uniconsoleapp.response.responses.ShowEmployeeCountResponse;
import yevhkil.uniconsoleapp.service.department.DepartmentService;
import yevhkil.uniconsoleapp.util.ArgumentsExtractor;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShowEmployeeCountByDepartmentCommand implements Command {
    private static final Pattern PATTERN =
            Pattern.compile("Show\\s+count\\s+of\\s+employee\\s+for\\s+(\\S+.*)?");
    private static final String COMMAND_TEMPLATE = "Show count of employee for {department_name}";

    private final DepartmentService departmentService;
    private final RequestHandler requestHandler;
    private final ArgumentsExtractor argumentsExtractor;

    @Override
    public Response execute(String input) {
        Response response = new ShowEmployeeCountResponse();

        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            return response;
        }

        String departmentName =
                argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        Integer employeeCount = departmentService.getEmployeeCount(departmentName);

        if (employeeCount != 0) {
            response = new ShowEmployeeCountResponse(departmentName, employeeCount);
            System.out.printf(response.getResponseBody());
            return response;
        }
        log.info(response.getNegativeResponseBody() + departmentName);
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
