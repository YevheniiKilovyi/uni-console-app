package yevhkil.uniconsoleapp.repository.department;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yevhkil.uniconsoleapp.model.Department;
import yevhkil.uniconsoleapp.model.Lector;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findDepartmentByName(String departmentName);

    @Query("SELECT DISTINCT d.headOfDepartment FROM Department d"
            + " LEFT JOIN FETCH d.headOfDepartment.departments WHERE d.name = ?1")
    Lector getHeadOfDepartment(String departmentName);

    @Query(value = "SELECT AVG(l.salary) FROM lectors l "
            + "JOIN lectors_departments ld ON ld.lector_id = l.id "
            + "JOIN departments d ON ld.department_id = d.id "
            + "WHERE d.name = ?1", nativeQuery = true)
    BigDecimal getAverageSalary(String departmentName);

    @Query(value = "SELECT COUNT(ld.lector_id) FROM lectors_departments ld "
            + "JOIN departments d ON ld.department_id = d.id "
            + "WHERE d.name = ?1", nativeQuery = true)
    Integer getEmployeeCount(String departmentName);

    @Query(value = "SELECT COUNT(l.degree) FROM departments d "
            + "JOIN lectors_departments ld ON d.id = ld.department_id "
            + "JOIN lectors l ON ld.lector_id = l.id "
            + "WHERE d.name = ?1 AND l.degree = ?2", nativeQuery = true)
    Integer getDegreeCount(String departmentName, String degreeName);

}
