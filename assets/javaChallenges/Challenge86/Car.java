package challenge86;

public class Car extends Vehicle {
    @Override
    public void service() {
        super.service();
        System.out.println("The car is providing service as well and is example of Overriding");
    }
}
