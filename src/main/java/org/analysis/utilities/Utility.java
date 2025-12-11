package org.analysis.utilities;

import org.analysis.interfaces.Employee;
import org.analysis.interfaces.EmployeeFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
* Utility class for employee is for data operations such as generating mock data and loading employee records from a csv file
*
* This class provides :
*  Methods to generate mock data and save it in csv file
* Methods to load employee data from a csv file into a map, including employee manager relationships
*
* */
public class Utility {
    private static final Logger logger = Logger.getLogger(Utility.class.getName());
    /*
    Path of the employee csv file loaded from properties or defaults
     */
    public static final String EMPLOYEE_CSV_PATH;
    /*
    * Number of employees to generate, loaded from properties default is 10
    * */
    public static final int NUM_EMPLOYEES;

    static{
        Properties prop = new Properties();
        try(InputStream input = Utility.class.getClassLoader().getResourceAsStream("application.properties")){
            prop.load(input);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading properties", e);
        }

        EMPLOYEE_CSV_PATH = prop.getProperty("employee.csv.path","employees.csv");
        NUM_EMPLOYEES = Integer.parseInt(prop.getProperty("employee.num","10"));
    }

    /*
    * Generate mock employee data and writes to csv file specified in # EMPLOYEE_CSV_PATH
    * The CEO don't have any manager , and all other employees has manager
    * Each employee has a randomly generate name and salary
    * */

    public static void mockData(){
        String[] firstNames = {"John","Jane","Alex","Emily","Chris","Katie","Mike","Sara","Tom","Anna"};
        String[] lastNames = {"Smith","Johnson","Williams","Brown","Jones","Gracia","Miller","Wilson","Cruise","Moore"};

        Random rand =   new Random();
        try(FileWriter writer = new FileWriter(EMPLOYEE_CSV_PATH)){
            writer.write("id,firstName,lastName,managerId,salary\n");
            // Predefined employees
            int[] predefinedIds = {123,124,125,300,310};
            writer.write("123,John,Doe,,60000\n");
            writer.write("124,Martin,Chekov,123,45000\n");
            writer.write("125,Bob,Ronstad,123,47000\n");
            writer.write("300,Alice,Hasacat,124,50000\n");
            writer.write("310,Brett,Hardleaf,300,34000\n");

            Set<Integer> usedIds = new HashSet<>();
            for(int id : predefinedIds)
                usedIds.add(id);
            List<Integer> allids = new ArrayList<>();
            for(int id : predefinedIds) allids.add(id);
            int totalRandomEmployees = NUM_EMPLOYEES - predefinedIds.length;
            int minId = 2;
            int maxId = 999;
            for(int i=0;i<=totalRandomEmployees;i++){
                int randId;
                do{
                    randId = rand.nextInt(maxId - minId + 1) + minId;
                }while(usedIds.contains(randId));
                usedIds.add(randId);
                // Manager can be any of the previous employees (predefined or random)
                String firstName = firstNames[rand.nextInt(firstNames.length)];
                String lastName = lastNames[rand.nextInt(lastNames.length)];
                int managerId = allids.get(rand.nextInt(allids.size()));
                double salary = 5000.0+ rand.nextDouble()*10000.0;
                writer.write(randId+","+firstName+","+lastName+","+managerId+","+String.format("%.2f",salary)+"\n");
                allids.add(randId);
            }
        }catch (IOException exception){
            logger.log(Level.SEVERE,"Error generating mock data",exception);
        }
    }

    /*
    * Loads employee data froma  csv file and construct a map of employee ID's to Employee objects
    * Also set the manager and employee relearions
    *
    * @param filePath -- path to the csv file containing employee data
    * @param factory -- factory to created employee instances
    *
    * */
    public static Map<Integer, Employee> loadEmployeeMap(String filePath, EmployeeFactory factory) {
        Map<Integer, Employee> employeeMap = new ConcurrentHashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            reader.readLine();
            String line;
            while((line  = reader.readLine()) != null){
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                Integer managerId = parts[3].isEmpty() ? null : Integer.parseInt(parts[3]);
                double salary = Double.parseDouble(parts[4]);
                Employee employee = factory.create(id,firstName,lastName,managerId,salary);
                employeeMap.put(id,employee);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error loading the employee map",e);
        }

        for(Employee employee : employeeMap.values()){
            Integer managerId = employee.getManagerId();
            if(managerId != null){
                employee.setManager(employeeMap.get(managerId));
            }
        }
        return employeeMap;
    }


}
