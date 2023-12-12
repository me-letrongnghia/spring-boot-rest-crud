package com.nghiale.springboot.cruddemo.dao;

import com.nghiale.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute query ang get result list
        List<Employee> theResult = theQuery.getResultList();

        // return the result
        return theResult;
    }

    @Override
    public Employee findById(int theId) {

        // get employee
        Employee employee = entityManager.find(Employee.class, theId);

        // return employee
        return employee;
    }

    @Override
    public Employee save(Employee employee) {

        // save employee
        Employee dbEmployee = entityManager.merge(employee);

        // return the dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        // find Employee by ID
        Employee employee = entityManager.find(Employee.class, theId);

        // remove the Employee
        entityManager.remove(employee);
    }
}
