package org.analysis;

import org.analysis.impl.BasicEmployee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BasicEmployeeTest {

    @Test
    void testEmployeeGettersAndSetters(){
        BasicEmployee ceo = new BasicEmployee(1,"John","Doe",null,60000.0);
        BasicEmployee employee = new BasicEmployee(2,"Bob","Miller",1,45000.0);

        employee.setManager(ceo);

        assertEquals(1,ceo.getId());
        assertEquals("John",ceo.getFirstName());
        assertEquals("Doe",ceo.getLastName());
        assertNull(ceo.getManagerId());
        assertEquals(60000.0,ceo.getSalary());
        assertNull(ceo.getManager());

        assertEquals(2,employee.getId());
        assertEquals("Bob",employee.getFirstName());
        assertEquals("Miller",employee.getLastName());
        assertEquals(1,employee.getManagerId());
        assertEquals(45000.0,employee.getSalary());
        assertEquals(ceo,employee.getManager());

    }

    @Test
    void testToStringFormat(){
        BasicEmployee employee = new BasicEmployee(3,"Alex","Brown",1,34000.0);
        String str = employee.toString();
        assertTrue(str.contains("id=3"));
        assertTrue(str.contains("firstName='Alex'"));
        assertTrue(str.contains("lastName='Brown'"));
        assertTrue(str.contains("managerId=1"));
        assertTrue(str.contains("salary=34000.0"));
    }

}
