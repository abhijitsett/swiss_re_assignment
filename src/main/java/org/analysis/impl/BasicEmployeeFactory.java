package org.analysis.impl;

import org.analysis.interfaces.Employee;
import org.analysis.interfaces.EmployeeFactory;

/*
* Basic implementation of EmployeeFactory for creating BasicEmployee
* */
public class BasicEmployeeFactory implements EmployeeFactory {

    /*
    * Creates a Basic Employee instance
    * @param id
    * @param firstName
    * @param lastName
    * @param managerId (null if CEO)
    * @param salary
    * */
    @Override
    public Employee create(int id, String firstName, String lastName, Integer managerId, Double salary) {
        return new BasicEmployee(id,firstName,lastName,managerId,salary);
    }
}
