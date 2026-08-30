package employee;

public class employee {
    private int age;
    private int salary;
    public String name;
    private int employeenumber;

    public employee(String name) {
        this.name=name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if(salary>0)
         this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age>18)
         this.age = age;
    }

    public int getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(int employeenumber) {
        if(employeenumber>0)
         this.employeenumber = employeenumber;
    }
    String displayEmployeeDetail(){
        return "Name= "+this.name+" Salary="+this.salary+" Age="+this.age+" Employee Number="+this.employeenumber;
    }
}

