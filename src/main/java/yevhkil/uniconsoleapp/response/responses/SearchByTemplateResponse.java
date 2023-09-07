package yevhkil.uniconsoleapp.response.responses;

import java.util.List;
import java.util.StringJoiner;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;
import yevhkil.uniconsoleapp.response.Response;

@Component
@NoArgsConstructor
public class SearchByTemplateResponse implements Response {
    private static final StringJoiner RESPONSE_TEMPLATE = new StringJoiner(", ");
    private static final String NEGATIVE_RESPONSE =
            ("No matching lectors found for the template: ");

    private String responseBody;

    public SearchByTemplateResponse(List<LectorResponseDto> lectorList) {
        StringJoiner responseBody = RESPONSE_TEMPLATE;
        for (LectorResponseDto lector : lectorList) {
            responseBody.add(lector.getFirstName() + " " + lector.getLastName());
        }
        this.responseBody = responseBody.toString();
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
