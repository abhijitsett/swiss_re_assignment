package org.analysis;

import org.analysis.impl.BasicEmployeeFactory;
import org.analysis.interfaces.Employee;
import org.analysis.interfaces.EmployeeFactory;
import org.analysis.services.EmployeeAnalysisService;
import org.analysis.utilities.Utility;

import java.util.List;
import java.util.Map;

/**
 * Analysis class for employee data operations and reporting
 */
public class Analysis {
    public static void main(String[] args) {
        String filePath = Utility.EMPLOYEE_CSV_PATH;
        EmployeeFactory factory = new BasicEmployeeFactory();
        Utility.mockData();
        Map<Integer, Employee> employeeMap = Utility.loadEmployeeMap(filePath,factory);
        List<String> salaryAnalysis = EmployeeAnalysisService.analyzeManagerSalaries(employeeMap);
        salaryAnalysis.forEach(System.out::println);
        List<String> reportingDepths =  EmployeeAnalysisService.analyzeReportingDepth(employeeMap);
        reportingDepths.forEach(System.out::println);
    }
}
