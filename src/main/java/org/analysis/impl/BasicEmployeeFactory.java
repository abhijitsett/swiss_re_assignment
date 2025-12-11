package org.analysis.impl;

import org.analysis.interfaces.Employee;
import org.analysis.interfaces.EmployeeFactory;

public class BasicEmployeeFactory implements EmployeeFactory {

    @Override
    public Employee create(int id, String firstName, String lastName, Integer managerId, Double salary) {
        return new BasicEmployee(id,firstName,lastName,managerId,salary);
    }
}
