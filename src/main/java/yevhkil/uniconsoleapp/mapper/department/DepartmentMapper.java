package yevhkil.uniconsoleapp.mapper.department;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import yevhkil.uniconsoleapp.config.MapperConfig;
import yevhkil.uniconsoleapp.dto.response.department.DepartmentResponseDto;
import yevhkil.uniconsoleapp.model.Department;

@Mapper(config = MapperConfig.class)
public abstract class DepartmentMapper {

    @Mapping(target = "headOfDepartmentId", source = "department.headOfDepartment.id")
    public abstract DepartmentResponseDto toDto(Department department);
}
