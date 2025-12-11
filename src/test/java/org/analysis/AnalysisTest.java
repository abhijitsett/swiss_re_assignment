package org.analysis;

import org.analysis.impl.BasicEmployee;
import org.analysis.impl.BasicEmployeeFactory;
import org.analysis.interfaces.Employee;
import org.analysis.interfaces.EmployeeFactory;
import org.analysis.services.EmployeeAnalysisService;
import org.analysis.utilities.Utility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AnalysisTest {

    private static final String TEST_CSV = "test_employees.csv";

    @BeforeEach
    void setUp(){
        File file = new File(Utility.EMPLOYEE_CSV_PATH);
        if(file.exists())
            file.delete();
    }

    @AfterEach
    void tearDown(){
        File file = new File(TEST_CSV);
        if(file.exists())
            file.delete();
        File csvFile = new File(Utility.EMPLOYEE_CSV_PATH);
        if(csvFile.exists())
            csvFile.delete();
    }

    @Test
    void testMainWorkFlow(){
        assertDoesNotThrow(()->{
            Utility.mockData();
            EmployeeFactory factory = new BasicEmployeeFactory();
            Map<Integer,Employee> employeeMap = Utility.loadEmployeeMap(Utility.EMPLOYEE_CSV_PATH, factory);
            assertNotNull(employeeMap);
            assertFalse(employeeMap.isEmpty());
            assertEquals(Utility.NUM_EMPLOYEES,employeeMap.size()-1);

            List<String> salaryANalysis = EmployeeAnalysisService.analyzeManagerSalaries(employeeMap);
            assertNotNull(salaryANalysis);

            List<String> reportingDepths = EmployeeAnalysisService.analyzeReportingDepth(employeeMap);
            assertNotNull(reportingDepths);
        });
    }

}
