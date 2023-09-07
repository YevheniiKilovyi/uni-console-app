package yevhkil.uniconsoleapp.service.department.impl;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yevhkil.uniconsoleapp.dto.response.lector.LectorResponseDto;
import yevhkil.uniconsoleapp.mapper.lector.LectorMapper;
import yevhkil.uniconsoleapp.repository.department.DepartmentRepository;
import yevhkil.uniconsoleapp.service.department.DepartmentService;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final LectorMapper lectorMapper;

    @Override
    public LectorResponseDto getDepartmentHead(String departmentName) {
        return lectorMapper.toDto(departmentRepository.getHeadOfDepartment(departmentName));
    }

    @Override
    public BigDecimal getAverageSalary(String departmentName) {
        return departmentRepository.getAverageSalary(departmentName);
    }

    @Override
    public Integer getEmployeeCount(String departmentName) {
        return departmentRepository.getEmployeeCount(departmentName);
    }

    @Override
    public Integer getDegreeCount(String departmentName, String degreeName) {
        return departmentRepository.getDegreeCount(departmentName, degreeName);
    }
}
