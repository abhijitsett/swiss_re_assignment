package org.analysis.impl;

import org.analysis.interfaces.Employee;

/*
* Basic implementation of the Employee interface
* */
public class BasicEmployee implements Employee {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final Integer managerId;
    private final Double salary;
    private Employee manager;


    /*
    * Constructs a Basic Employee instance
    * @param id
    * @param firstName
    * @param lastName
    * @param manageId (null if CEO)
    * @param salary
    *
    *
    * */
    public BasicEmployee(int id, String firstName, String lastName, Integer managerId, Double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.managerId = managerId;
        this.salary = salary;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public Integer getManagerId() {
        return managerId;
    }

    @Override
    public Double getSalary() {
        return salary;
    }

    @Override
    public Employee getManager() {
        return manager;
    }

    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "BasicEmployee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", managerId=" + managerId +
                ", salary=" + salary +
                ", manager=" + manager +
                '}';
    }
}
