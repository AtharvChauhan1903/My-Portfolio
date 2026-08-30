package challenge83;

public class Circle extends Shape {
     public final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI*Math.pow(radius,2);
    }
    public double Perimeter(){
        return 2*Math.PI*radius;
    }
}
