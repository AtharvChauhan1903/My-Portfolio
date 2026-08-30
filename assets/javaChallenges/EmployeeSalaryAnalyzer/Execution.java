package EmployeeSalaryAnalyzer;

import java.util.*;

public class Execution {
    static void main() {
        List<Employee> list=new ArrayList<>();
        Employee e1=new Employee("Raghav",49000,"I.T.");
        Employee e2=new Employee("Raghu",59000,"H.R.");
        Employee e3=new Employee("Spider-Man",29000,"Security");
        Employee e4=new Employee("Timmy",6000000,"I.T.");
        Employee e5=new Employee("Rahul",75000,"H.R.");
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        System.out.println(list.stream().max(Comparator.comparingInt(Employee::getSalary)));
        HashSet<String>departments=new HashSet<>();
        list.stream().map(Employee::getDepartment).forEach(departments::add);
        System.out.println(departments);
        list.stream().sorted(Comparator.comparingInt(Employee::getSalary)).forEach(System.out::println);
        double average=list.stream().mapToInt(Employee::getSalary).average().orElse(0.0);
        list.stream().filter(Employee->Employee.getSalary()>average).forEach(System.out::println);
    }
}
