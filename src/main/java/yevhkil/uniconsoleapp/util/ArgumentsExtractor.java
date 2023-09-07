package yevhkil.uniconsoleapp.util;

import java.util.regex.Pattern;

public interface ArgumentsExtractor {
    String extractArgumentFromRequestByPattern(String request, Pattern pattern);
}
