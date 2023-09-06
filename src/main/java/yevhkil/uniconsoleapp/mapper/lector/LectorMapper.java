package yevhkil.uniconsoleapp.mapper.lector;

import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import yevhkil.uniconsoleapp.config.MapperConfig;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;
import yevhkil.uniconsoleapp.mapper.department.DepartmentMapper;
import yevhkil.uniconsoleapp.model.Lector;

@Mapper(config = MapperConfig.class)
public abstract class LectorMapper {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Mapping(target = "departments", ignore = true)
    public abstract LectorResponseDto toDto(Lector lector);

    @AfterMapping
    public void setDepartmentsToDto(
            @MappingTarget LectorResponseDto lectorResponseDto, Lector lector) {
        lectorResponseDto.setDepartments(
                lector.getDepartments().stream()
                        .map(department ->
                                departmentMapper.toDto(department)).collect(Collectors.toSet()));
    }
}
