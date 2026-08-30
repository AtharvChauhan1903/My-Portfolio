package employee;

public class company {
    static void main() {
        employee em=new employee("Kevin");
        em.setEmployeenumber(12);
        em.setAge(20);
        em.setSalary(100000);
        System.out.println(em.displayEmployeeDetail());
    }
}
