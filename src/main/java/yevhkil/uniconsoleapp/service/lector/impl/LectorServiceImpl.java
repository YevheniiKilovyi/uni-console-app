package yevhkil.uniconsoleapp.service.lector.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;
import yevhkil.uniconsoleapp.mapper.lector.LectorMapper;
import yevhkil.uniconsoleapp.model.Lector;
import yevhkil.uniconsoleapp.repository.lector.LectorRepository;
import yevhkil.uniconsoleapp.service.lector.LectorService;

@RequiredArgsConstructor
@Service
public class LectorServiceImpl implements LectorService {
    private final LectorRepository lectorRepository;
    private final LectorMapper lectorMapper;

    @Override
    public List<LectorResponseDto> findLectorsByTemplate(String template) {
        List<Lector> lectorsByTemplate = lectorRepository.findLectorsByTemplate(template);
        return lectorsByTemplate.stream()
                .map(lectorMapper::toDto)
                .toList();
    }

}
