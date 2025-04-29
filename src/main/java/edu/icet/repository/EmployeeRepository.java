package edu.icet.repository;

import edu.icet.entity.EmployeeEntity;
import edu.icet.util.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface EmployeeRepository extends CrudRepository<EmployeeEntity,Long> {
    List<EmployeeEntity> findByNameContaining(String name);
    EmployeeEntity findByEmail(String email);
    List<EmployeeEntity> findBySalaryBetween(Double minSalary, Double maxSalary);
    List<EmployeeEntity> findByDepartment(Department department);
}
