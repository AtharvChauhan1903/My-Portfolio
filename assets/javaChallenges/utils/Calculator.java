package utils;
import geometry.Circle;
import geometry.Rectangle;
public class Calculator {
    static void main(String[] args) {
        Circle c=new Circle(2.7f);
        Rectangle r=new Rectangle(3,4);
        System.out.println(c.Circumference());
        System.out.println(c.CircleArea());
        System.out.println(r.Perimeter());
        System.out.println(r.RectangleArea());
    }
}
