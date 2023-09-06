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
public class ShowStatisticsCommand implements Command {
    private static final Pattern PATTERN = Pattern.compile("Show\\s+(.*)\\s+statistics");
    private static final String ASSISTANT_DEGREE = "assistant";
    private static final String ASSOCIATE_PROFESSOR_DEGREE = "associate_professor";
    private static final String PROFESSOR_DEGREE = "professor";

    private final DepartmentService departmentService;
    private final RequestHandler requestHandler;
    private final ArgumentsExtractor argumentsExtractor;

    @Override
    public boolean execute(String input) {
        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            System.out.println("Invalid command format. Use: Show {department_name} statistics.");
            return false;
        }

        String departmentName =
                argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        Integer assistantCount = departmentService.getDegreeCount(departmentName, ASSISTANT_DEGREE);
        Integer associateProfessorsCount =
                departmentService.getDegreeCount(departmentName, ASSOCIATE_PROFESSOR_DEGREE);
        Integer professorsCount =
                departmentService.getDegreeCount(departmentName, PROFESSOR_DEGREE);

        if (assistantCount > 0 || associateProfessorsCount > 0 || professorsCount > 0) {
            System.out.printf("assistants - %d.%n", assistantCount);
            System.out.printf("associate professors - %d.%n", associateProfessorsCount);
            System.out.printf("professors - %d.%n", professorsCount);
            return true;
        }
        System.out.println("Department not found or no statistics available.");
        return false;
    }
}
