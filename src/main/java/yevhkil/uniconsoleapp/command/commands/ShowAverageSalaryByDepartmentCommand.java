package yevhkil.uniconsoleapp.command.commands;

import java.math.BigDecimal;
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
public class ShowAverageSalaryByDepartmentCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile(
            "Show\\s+the\\s+average\\s+salary\\s+for\\s+the\\s+department\\s+(\\S+.*)?");
    private static final String COMMAND_TEMPLATE =
            "Show the average salary for the department {department_name}";

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
        BigDecimal averageSalary = departmentService.getAverageSalary(departmentName);

        if (averageSalary != null) {
            System.out.printf("The average salary of %s is %.2f%n", departmentName, averageSalary);
            return;
        }
        log.info("Department not found or no average salary available.");
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
