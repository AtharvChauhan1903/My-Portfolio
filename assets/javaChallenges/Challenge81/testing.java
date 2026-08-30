package challenge81;

public class testing {
    static void main() {
        Person p1=new Person("Mehra");
        Person p2=new Person("Mehra");
        p1.setAge(50);
        p1.setHeight(251.99);
        p1.setWeight(55);
        p2.setHeight(251.99);
        p2.setWeight(55);
        p2.setAge(50);
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        p1.setHeight(299.99);
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

    }
}
