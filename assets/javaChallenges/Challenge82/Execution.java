package challenge82;

public class Execution {
    static void main() {
     Car c=new Car("BMW","NewCompanyX");
     Car.Engine e=new Car.Engine();
     c.getEngine().setPistons(4);
     c.Drive();
     c.Drive();c.Refuel();c.Refuel();c.Drive();
        System.out.println(c.getEngine().getPistons());
        System.out.println(c.getEngine().getRings());
    }
}
