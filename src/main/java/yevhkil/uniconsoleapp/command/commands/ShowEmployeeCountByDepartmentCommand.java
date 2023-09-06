package yevhkil.uniconsoleapp.command.commands;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.request.RequestHandler;
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
    public void execute(String input) {
        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            return;
        }

        String departmentName =
                argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        Integer employeeCount = departmentService.getEmployeeCount(departmentName);

        if (employeeCount != 0) {
            System.out.printf("Employee count for %s: %d%n", departmentName, employeeCount);
            return;
        }
        log.info("No employees found for the department: " + departmentName);
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
