package yevhkil.uniconsoleapp.command.commands;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;
import yevhkil.uniconsoleapp.request.RequestHandler;
import yevhkil.uniconsoleapp.service.department.DepartmentService;
import yevhkil.uniconsoleapp.util.ArgumentsExtractor;

@Component
@RequiredArgsConstructor
public class HeadOfDepartmentCommand implements Command {
    private static final Pattern PATTERN =
            Pattern.compile("Who\\s+is\\s+head\\s+of\\s+department\\s+(\\S+.*)?");

    private final DepartmentService departmentService;
    private final RequestHandler requestHandler;
    private final ArgumentsExtractor argumentsExtractor;

    @Override
    public boolean execute(String input) {
        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            System.out.println(
                    "Invalid command format. Use: Who is head of department {department_name}");
            return false;
        }

        String departmentName =
                argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        LectorResponseDto lectorResponseDto = departmentService.getDepartmentHead(departmentName);

        if (lectorResponseDto != null) {
            String headOfDepartment = String.format(
                    "Head of %s department is %s %s",
                    departmentName,
                    lectorResponseDto.getFirstName(),
                    lectorResponseDto.getLastName()
            );
            System.out.println(headOfDepartment);
            return true;
        }
        System.out.println("Department not found or head not assigned.");
        return false;
    }
}
