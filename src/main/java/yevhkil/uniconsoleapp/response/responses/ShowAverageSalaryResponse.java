package yevhkil.uniconsoleapp.response.responses;

import java.math.BigDecimal;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.response.Response;

@Component
@NoArgsConstructor
public class ShowAverageSalaryResponse implements Response {
    private static final String RESPONSE_TEMPLATE = "The average salary of %s is %.2f%n";
    private static final String NEGATIVE_RESPONSE =
            "Department not found or no average salary available.";

    private String responseBody;

    public ShowAverageSalaryResponse(String departmentName, BigDecimal averageSalary) {
        this.responseBody = String.format(
                RESPONSE_TEMPLATE,
                departmentName,
                averageSalary
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
