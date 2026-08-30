package challenge94;

import java.util.PriorityQueue;

public class execution {
    static void main() {
        Student s1 =new Student("Atharv",13,8);
        Student s2=new Student("Timmy",9,12);
        PriorityQueue<Student> pb =
                new PriorityQueue<>((a, b) -> a.CLass - b.CLass);
        pb.add(s1);
        pb.add(s2);
        while (!pb.isEmpty()) {
            Student s = pb.poll();
            System.out.println(s.name + " " + s.CLass);
        }
        System.out.println("look at timmy ,he is 9 and he is class 12 lah...stupid you are a disappointment,failure");
    }
}
