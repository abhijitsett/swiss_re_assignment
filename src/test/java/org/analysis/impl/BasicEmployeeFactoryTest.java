package org.analysis;

import org.analysis.impl.BasicEmployeeFactory;
import org.analysis.interfaces.Employee;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BasicEmployeeFactoryTest {

    @Test
    void testCreateEmployee(){
        BasicEmployeeFactory factory = new BasicEmployeeFactory();
        Employee employee = factory.create(10,"Chris","Miller",1,75000.0);

        assertNotNull(employee);
        assertEquals(10,employee.getId());
        assertEquals("Chris",employee.getFirstName());
        assertEquals("Miller",employee.getLastName());
        assertEquals(1, employee.getManagerId());
        assertEquals(75000.0,employee.getSalary());
    }
}
