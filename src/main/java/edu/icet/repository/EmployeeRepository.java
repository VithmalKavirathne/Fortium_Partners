package edu.icet.repository;

import edu.icet.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDto,Integer> {

}
