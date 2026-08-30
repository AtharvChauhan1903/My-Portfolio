package challenge83;

public class Square extends Shape {
    public final int sideValue;

    public Square(int sideValue) {
        this.sideValue = sideValue;
    }

    @Override
    public double area() {
        return Math.pow(sideValue,2);
    }
    public int Perimeter(){
        return 4*sideValue;
    }
}
