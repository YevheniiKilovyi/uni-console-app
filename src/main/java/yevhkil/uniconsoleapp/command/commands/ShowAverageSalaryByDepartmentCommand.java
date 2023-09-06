package yevhkil.uniconsoleapp.command.commands;

import java.math.BigDecimal;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.request.RequestHandler;
import yevhkil.uniconsoleapp.service.department.DepartmentService;
import yevhkil.uniconsoleapp.util.ArgumentsExtractor;

@Component
@RequiredArgsConstructor
public class ShowAverageSalaryByDepartmentCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile(
            "Show\\s+the\\s+average\\s+salary\\s+for\\s+the\\s+department\\s+(\\S+.*)?");

    private final DepartmentService departmentService;
    private final RequestHandler requestHandler;
    private final ArgumentsExtractor argumentsExtractor;

    @Override
    public boolean execute(String input) {
        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            System.out.println("Invalid command format."
                    + " Use: Show the average salary for the department {department_name}");
            return false;
        }

        String departmentName =
                argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        BigDecimal averageSalary = departmentService.getAverageSalary(departmentName);

        if (averageSalary != null) {
            System.out.printf("The average salary of %s is %.2f%n", departmentName, averageSalary);
            return true;
        }
        System.out.println("Department not found or no average salary available.");
        return false;
    }
}
