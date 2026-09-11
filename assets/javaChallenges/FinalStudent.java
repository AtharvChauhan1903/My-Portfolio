public class challenge76 {
    private final String name;
    private final int age;
    private final int AssignedRollNumber;
    private final float height;
    public challenge76(String name,int age,int assignedRollNumber,float height){
        this.age=age;
        this.height=height;
        this.AssignedRollNumber=assignedRollNumber;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Name="+name+" Age="+age+" Roll number="+AssignedRollNumber+" Height="+height;
    }

    static void main() {
     challenge76 st1=new challenge76("Atharv",20,7,(float)189.5);
        System.out.println(st1.toString());
    }
}
