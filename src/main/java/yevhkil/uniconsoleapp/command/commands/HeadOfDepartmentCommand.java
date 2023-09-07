package yevhkil.uniconsoleapp.command.commands;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.command.Command;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;
import yevhkil.uniconsoleapp.request.RequestHandler;
import yevhkil.uniconsoleapp.response.Response;
import yevhkil.uniconsoleapp.response.responses.HeadOfDepartmentResponse;
import yevhkil.uniconsoleapp.service.department.DepartmentService;
import yevhkil.uniconsoleapp.util.ArgumentsExtractor;

@Component
@RequiredArgsConstructor
@Slf4j
public class HeadOfDepartmentCommand implements Command {
    private static final Pattern PATTERN =
            Pattern.compile("Who\\s+is\\s+head\\s+of\\s+department\\s+(\\S+.*)?");
    private static final String COMMAND_TEMPLATE = "Who is head of department {department_name}";

    private final DepartmentService departmentService;
    private final RequestHandler requestHandler;
    private final ArgumentsExtractor argumentsExtractor;

    @Override
    public Response execute(String input) {
        Response response = new HeadOfDepartmentResponse();

        if (!requestHandler.isRequestApplicableByPattern(input, PATTERN)) {
            return response;
        }

        String departmentName =
                argumentsExtractor.extractArgumentFromRequestByPattern(input, PATTERN);
        LectorResponseDto lectorResponseDto = departmentService.getDepartmentHead(departmentName);

        if (lectorResponseDto != null) {
            response = new HeadOfDepartmentResponse(departmentName,
                    lectorResponseDto.getFirstName(), lectorResponseDto.getLastName());
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
