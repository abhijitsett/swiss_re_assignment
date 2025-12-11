package org.analysis.interfaces;

/*
* Factory interface for employee objects
* */
public interface EmployeeFactory {

    Employee create(int id, String firstName, String lastName, Integer managerId, Double salary);
}
