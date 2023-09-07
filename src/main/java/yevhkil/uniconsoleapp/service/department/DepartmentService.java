package yevhkil.uniconsoleapp.service.department;

import java.math.BigDecimal;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;

public interface DepartmentService {

    LectorResponseDto getDepartmentHead(String departmentName);

    BigDecimal getAverageSalary(String departmentName);

    Integer getEmployeeCount(String departmentName);

    Integer getDegreeCount(String departmentName, String degreeName);
}
