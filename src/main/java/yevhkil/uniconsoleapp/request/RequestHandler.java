package yevhkil.uniconsoleapp.request;

import java.util.regex.Pattern;

public interface RequestHandler {
    boolean isRequestApplicableByPattern(String request, Pattern pattern);
}
