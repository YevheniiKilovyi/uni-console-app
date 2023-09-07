package yevhkil.uniconsoleapp.response.responses;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.response.Response;

@Component
@NoArgsConstructor
public class ShowStatisticsResponse implements Response {
    private static final String RESPONSE_TEMPLATE = """
            assistants - %d.%n
            associate professors - %d.%n
            professors - %d.%n
            """;
    private static final String NEGATIVE_RESPONSE =
            "Department not found or no statistics available.";

    private String responseBody;

    public ShowStatisticsResponse(Integer assistantCount,
                                  Integer associateProfessorsCount,
                                  Integer professorsCount) {
        this.responseBody = String.format(
                RESPONSE_TEMPLATE,
                assistantCount,
                associateProfessorsCount,
                professorsCount
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
