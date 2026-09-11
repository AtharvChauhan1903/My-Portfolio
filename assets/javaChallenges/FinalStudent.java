public class FinalStudent {
    private final String name;
    private final int age;
    private final int AssignedRollNumber;
    private final float height;
    public FinalStudent(String name,int age,int assignedRollNumber,float height){
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
     FinalStudent st1=new FinalStudent("Atharv",20,7,(float)189.5);
        System.out.println(st1.toString());
    }
}
