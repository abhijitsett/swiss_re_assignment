package org.analysis.utilities;

import org.analysis.impl.BasicEmployeeFactory;
import org.analysis.interfaces.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {
    private static final String TEST_CSV = "test_employees.csv";

    @BeforeEach
    void setUp() {
        Utility.mockData();
        File original = new File(Utility.EMPLOYEE_CSV_PATH);
        if (original.exists()) {
            original.renameTo(new File(TEST_CSV));
        }
    }

    @AfterEach
    void cleanUpAndValidate() {
        File file = new File(TEST_CSV);

        assertTrue(file.exists(), "Mock data file should exist");
        assertTrue(file.length() > 0, "Mock data file should not be empty");
        file.delete();
    }

    @Test
    void testLoadEmployeeMapLoadsCorrectNumber() {

        BasicEmployeeFactory factory = new BasicEmployeeFactory();
        Map<Integer, Employee> map = Utility.loadEmployeeMap(TEST_CSV, factory);

        assertEquals(
                Utility.NUM_EMPLOYEES,
                map.size()-1,
                "Employee map size should match NUM_EMPLOYEES"
        );
    }

    @Test
    void testManagerRelationshipsAreSet() {

        BasicEmployeeFactory factory = new BasicEmployeeFactory();
        Map<Integer, Employee> mp = Utility.loadEmployeeMap(TEST_CSV, factory);

        for (Employee employee : mp.values()) {
            Integer managerId = employee.getManagerId();
            if (managerId != null) {
                assertNotNull(
                        employee.getManager(),
                        "Manager should be set for employee " + employee.getId()
                );

                assertEquals(
                        managerId,
                        employee.getManager().getId(),
                        "Manager reference mismatch for employee " + employee.getId()
                );

            } else {
                assertNull(
                        employee.getManager(),
                        "CEO should not have a manager"
                );
            }
        }
    }
}
