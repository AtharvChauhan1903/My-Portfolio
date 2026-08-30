package geometry;

public class Rectangle {
    int length;
    int breadth;
    public Rectangle(int length,int breadth){
        this.breadth=breadth;
        this.length=length;
    }
    public int Perimeter() {
        return 2 * (this.length + this.breadth);
    }
    public int RectangleArea() {
        return this.length * this.breadth;
    }
}
