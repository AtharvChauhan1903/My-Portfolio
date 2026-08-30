package Challenge114;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Execution {
    static void main() {
        Employee e1=new Employee(49000,"Raghav");
        Employee e2=new Employee(59000,"Raghu");
        Employee e3=new Employee(29000,"Spider-Man");
        Employee e4=new Employee(60000000," Your Cousin Timmy");
        List<Employee> list=new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);

        Comparator<Employee>comp=(a,b)->Integer.compare(a.getSalary(),b.getSalary());
        list.stream().sorted(comp).forEach(System.out::println);
    }
}
