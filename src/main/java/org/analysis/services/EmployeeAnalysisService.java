package org.analysis.services;

import org.analysis.interfaces.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeAnalysisService {
    public static List<String> analyzeManagerSalaries(Map<Integer, Employee> employeeMap){
        List<String> results = new ArrayList<>();
        Map<Integer, List<Double>> managerSalaries = new HashMap<>();
        for(Employee employee : employeeMap.values()){
            Integer managerId = employee.getManagerId();
            if(managerId != null){
                managerSalaries.computeIfAbsent(managerId,k->new ArrayList<>()).add(employee.getSalary());
            }
        }

        for(Map.Entry<Integer,List<Double>> entry : managerSalaries.entrySet()){
            int managerId = entry.getKey();
            List<Double> salaries = entry.getValue();
            double avg = salaries.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            Employee manager = employeeMap.get(managerId);
            double minRequired = avg*1.20;
            double maxAllowed = avg*1.50;
            double managerSalary = manager.getSalary();
            if(managerSalary < minRequired){
                double shortFall = minRequired - managerSalary;
                results.add(String.format(
                        "%s %s earns %.2f, which is %.2f less than the required minimum of %.2f",
                        manager.getFirstName(),
                        manager.getLastName(),
                        managerSalary,
                        shortFall,
                        minRequired
                ));

            }else if(managerSalary > maxAllowed){
                double excess = managerSalary - maxAllowed;
                results.add(String.format(
                        "%s %s earns %.2f, which is %.2f more than the allowed maximum of %.2f",
                        manager.getFirstName(),
                        manager.getLastName(),
                        managerSalary,
                        excess,
                        maxAllowed
                ));

            }
        }
        return results;
    }

    public static List<String> analyzeReportingDepth(Map<Integer,Employee> employeeMap){
        List<String> results = new ArrayList<>();
        for(Employee employee : employeeMap.values()){
            int depth = 0;
            Integer managerId = employee.getManagerId();

            while(managerId != null){
                depth++;
                Employee manager = employeeMap.get(managerId);
                if(manager == null)
                    break;
                managerId = manager.getManagerId();
            }
            results.add("Employee: "+employee.getFirstName() + " "+employee.getLastName()+" (ID :" +employee.getId()+ ") - Reporting Depth : "+depth );
        }
        return results;
    }
}
