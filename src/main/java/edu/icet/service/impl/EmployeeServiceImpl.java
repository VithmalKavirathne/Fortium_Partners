package edu.icet.service.impl;

import edu.icet.repository.EmployeeRepository;
import edu.icet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl<Employee> implements EmployeeService {

    private Object employeeRepository;

    @Autowired
    public void EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);

        return employee;
    }

        public void deleteEmployee(Long id) {
            Employee employee = getEmployeeById(id);
            employeeRepository.delete(employee);
        }

}
