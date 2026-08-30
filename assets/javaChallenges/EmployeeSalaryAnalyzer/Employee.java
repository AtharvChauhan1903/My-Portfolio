package EmployeeSalaryAnalyzer;
 class Employee {
 final String name;
 private int salary;
 public String Department;

     public Employee(String name, int salary, String department) {
         if (salary >= 20000) {
             this.name = name;
             this.salary = salary;
             Department = department;
         }else{
             this.name = name;
             this.salary = 20000;
             Department = department;
         }
     }

     public String getDepartment() {
         return Department;
     }

     public String getName() {
         return name;
     }

     public int getSalary() {
         return salary;
     }

     public void setSalary(int salary) {
         if (salary >= 20000) {
             this.salary = salary;
         }else
             this.salary=20000;
     }
 }
