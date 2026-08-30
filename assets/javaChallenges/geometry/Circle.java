package geometry;

public class Circle {
    float radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    public float Circumference() {
        return (float)(this.radius * 2 * Math.PI);
    }

    public float CircleArea(){
        return (float)(Math.PI*Math.pow(this.radius,2));
}
}
