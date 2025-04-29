package edu.icet.controller;

import icet.edu.com.dto.EmployeeDto;
import icet.edu.com.entity.EmployeeEntity;
import icet.edu.com.repository.EmployeeRepository;
import icet.edu.com.service.EmployeeService;
import icet.edu.com.util.Department;
import icet.edu.com.util.EmailCheck;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final ModelMapper mapper;
    private final EmailCheck check;

    @Override
    public Boolean saveEmployee(EmployeeDto employeeDto) {
        if(repository.findByEmail(employeeDto.getEmail()) != null && check.isValid(employeeDto.getEmail())) return false;
        repository.save(mapper.map(employeeDto, EmployeeEntity.class));
        return true;
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        if(id == null) return null;
        Optional<EmployeeEntity> byId = repository.findById(id);
        if (byId.isPresent()) {
            return mapper.map(byId.get(), EmployeeDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteEmployee(Long id) {
        if (id == null || !repository.existsById(id)) return false;
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> all=new ArrayList<>();
        repository.findAll().forEach(employeeEntity -> all.add(mapper.map(employeeEntity, EmployeeDto.class)));
        return all;
    }

    @Override
    public Boolean updateEmployee(Long id, EmployeeDto employeeDto) {
        if (id == null || employeeDto == null || !repository.existsById(id)) return false;
        repository.save(mapper.map(employeeDto, EmployeeEntity.class));
        return true;
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(Department department) {
        List<EmployeeDto> all = new ArrayList<>();
        for (EmployeeEntity employeeEntity: repository.findByDepartment(department)) {
            all.add(mapper.map(employeeEntity, EmployeeDto.class));
        }
        return all;
    }

    @Override
    public List<EmployeeDto> getEmployeesByName(String name) {
        List<EmployeeDto> all = new ArrayList<>();
        for (EmployeeEntity employeeEntity: repository.findByNameContaining(name)) {
            all.add(mapper.map(employeeEntity, EmployeeDto.class));
        }
        return all;

    }
}
