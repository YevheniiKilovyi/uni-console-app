package yevhkil.uniconsoleapp.request;

import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class RequestHandlerImpl implements RequestHandler {
    @Override
    public boolean isRequestApplicableByPattern(String request, Pattern pattern) {
        return pattern.matcher(request).matches();
    }
}
