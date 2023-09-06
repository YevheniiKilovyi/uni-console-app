package yevhkil.uniconsoleapp.dto.response.lector;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import yevhkil.uniconsoleapp.dto.response.department.DepartmentResponseDto;
import yevhkil.uniconsoleapp.model.Lector;

@Getter
@Setter
public class LectorResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private Lector.LectorDegree lectorDegree;
    private Set<DepartmentResponseDto> departments;
}
