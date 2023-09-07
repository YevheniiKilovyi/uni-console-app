package yevhkil.uniconsoleapp.response.responses;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.response.Response;

@Component
@NoArgsConstructor
public class HeadOfDepartmentResponse implements Response {
    private static final String RESPONSE_TEMPLATE = "Head of %s department is %s %s";
    private static final String NEGATIVE_RESPONSE = "Department not found or head not assigned.";

    private String responseBody;

    public HeadOfDepartmentResponse(String departmentName,
                                    String lectorFirstName,
                                    String lectorLastName) {
        this.responseBody = String.format(
                RESPONSE_TEMPLATE,
                departmentName,
                lectorFirstName,
                lectorLastName);
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
