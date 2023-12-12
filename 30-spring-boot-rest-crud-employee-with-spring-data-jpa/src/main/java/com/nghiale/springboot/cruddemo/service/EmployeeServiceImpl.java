package com.nghiale.springboot.cruddemo.service;

import com.nghiale.springboot.cruddemo.dao.EmployeeRepository;
import com.nghiale.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // Inject EmployeeDAO
    private EmployeeRepository employeeRepository;

    // Constructor
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee employee = null;
        if (result.isPresent()) {
            employee = result.get();
        } else {
            // we didn't find the employee
            throw new RuntimeException("Didn't find Employee ID - " + theId);
        }
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
