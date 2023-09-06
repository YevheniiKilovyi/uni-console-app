package yevhkil.uniconsoleapp.dto.response.department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentResponseDto {
    private Long id;
    private String name;
    private Long headOfDepartmentId;
}
