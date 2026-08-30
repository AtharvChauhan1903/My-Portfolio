package challenge94;

public class Student {
    public String name;
    public int age;
    public int CLass;
    private double height;
    private double weight;

    public Student(String name, int age, int CLass) {
        this.name = name;
        this.age = age;
        this.CLass = CLass;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
