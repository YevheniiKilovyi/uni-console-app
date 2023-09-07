package yevhkil.uniconsoleapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class ArgumentsExtractorImpl implements ArgumentsExtractor {
    @Override
    public String extractArgumentFromRequestByPattern(String request, Pattern pattern) {
        Matcher matcher = pattern.matcher(request);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
}
