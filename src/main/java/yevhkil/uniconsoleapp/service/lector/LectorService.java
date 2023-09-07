package yevhkil.uniconsoleapp.service.lector;

import java.util.List;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;

public interface LectorService {
    List<LectorResponseDto> findLectorsByTemplate(String template);
}
