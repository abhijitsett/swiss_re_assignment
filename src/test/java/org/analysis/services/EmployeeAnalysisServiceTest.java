package org.analysis;

import org.analysis.impl.BasicEmployee;
import org.analysis.impl.BasicEmployeeFactory;
import org.analysis.interfaces.Employee;
import org.analysis.services.EmployeeAnalysisService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeAnalysisServiceTest {

    /**
     * Unit test for simple App.
     */
        @Test
        void testAnanlyzeManmagerSalaries() {
            Map<Integer, Employee> mp = new HashMap<>();
            BasicEmployeeFactory factory = new BasicEmployeeFactory();
            mp.put(1, factory.create(1,"John","Doe",null,60000.0));
            mp.put(2, factory.create(2,"Bob","Miller",1,90000.0));
            mp.put(3, factory.create(3,"Alex","Bruce",2,70000.0));
            mp.get(2).setManager(mp.get(1));
            mp.get(3).setManager(mp.get(2));
            List<String> results = EmployeeAnalysisService.analyzeManagerSalaries(mp);

            assertTrue(results.stream().anyMatch(s->s.contains("John Doe")));
        }

        @Test
        void testAnalyzeReportingDepth(){
            Map<Integer, Employee> mp = createDeepHierarchy(5);
            List<String> results = EmployeeAnalysisService.analyzeReportingDepth(mp);
            results.forEach(System.out::println);
            assertTrue(results.stream().anyMatch(s->s.contains("Reporting Depth : 4")));
        }

        private Map<Integer, Employee> createDeepHierarchy(int depth) {
            Map<Integer, Employee> mp = new HashMap<>();
            BasicEmployee prev = null;
            for(int i=1;i<=depth;i++){
                BasicEmployee employee = new BasicEmployee(i,"Emp"+i,"Last"+i, i==1 ?null:i-1,10000.0-i*1000);
                employee.setManager(prev);
                mp.put(i,employee);
                prev = employee;
            }
            return mp;
        }
    }
