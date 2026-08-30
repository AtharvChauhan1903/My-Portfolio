package Challenge114;

 class Employee {
 private final String name;
 private int Salary;

     public int getSalary() {
         return this.Salary;
     }

     public void setSalary(int salary) {
         if(salary>=2000) {
             this.Salary = salary;
         }else {
             this.Salary=2000;
         }
     }

     public String getName() {
         return name;
     }

     public Employee(int salary, String name) {
         if (salary >= 2000) {
             this.Salary = salary;
             this.name = name;
         }else{
             this.Salary=2000;
             this.name=name;
         }
     }

     @Override
     public String toString() {
         return   this.name +" "+
                   this.Salary ;


     }
 }
