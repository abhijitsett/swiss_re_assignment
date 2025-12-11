package org.analysis.interfaces;
/*
* Represnt employee in the organisation
* */
public interface Employee {
    int getId();
    String getFirstName();
    String getLastName();
    Integer getManagerId();
    Double getSalary();
    Employee getManager();
    void setManager(Employee manager);
}
