package yevhkil.uniconsoleapp.command.commands;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.request.RequestHandler;
import yevhkil.uniconsoleapp.service.department.DepartmentService;
import yevhkil.uniconsoleapp.util.ArgumentsExtractor;

@Component
@RequiredArgsConstructor
public class ShowEmployeeCountByDepartmentCommand implements Command {
    private static final Pattern PATTERN =
            Pattern.compile("Show\\s+count\\s+of\\s+employee\\s+for\\s+(\\S+.*)?");

    private final DepartmentService departmentService;
    private final RequestHandler requestHandler;
    private final ArgumentsExtractor argumentsExtractor;

    @Override
    public boolean execute(String input) {
        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            System.out.println(
                    "Invalid command format. Use: Show count of employee for {department_name}");
            return false;
        }

        String departmentName =
                argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        Integer employeeCount = departmentService.getEmployeeCount(departmentName);

        if (employeeCount != 0) {
            System.out.printf("Employee count for %s: %d%n", departmentName, employeeCount);
            return true;
        }
        System.out.println("No employees found for the department: " + departmentName);
        return false;
    }
}
