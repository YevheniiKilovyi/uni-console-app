package yevhkil.uniconsoleapp.response.responses;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.response.Response;

@Component
@NoArgsConstructor
public class ShowEmployeeCountResponse implements Response {
    private static final String RESPONSE_TEMPLATE = "Employee count for %s: %d%n";
    private static final String NEGATIVE_RESPONSE = "No employees found for the department: ";

    private String responseBody;

    public ShowEmployeeCountResponse(String departmentName, Integer employeeCount) {
        this.responseBody = String.format(
                RESPONSE_TEMPLATE,
                departmentName,
                employeeCount
        );
    }

    @Override
    public String getResponseBody() {
        return this.responseBody;
    }

    @Override
    public String getNegativeResponseBody() {
        return this.responseBody = NEGATIVE_RESPONSE;
    }
}
