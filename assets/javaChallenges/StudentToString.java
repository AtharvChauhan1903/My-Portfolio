public class StudentToString {
    private String Name;
    private int Age;
    private String  Caste;
    public  StudentToString(String name,int Age,String Caste){
        this.Name=name;
        this.Age=Age;
        this.Caste=Caste;
    }

    @Override
    public String toString() {
        return "Name="+Name+",Age="+Age+",Caste="+Caste;
    }

    static void main(){
        StudentToString st1=new StudentToString("Atharv",20,"General");
        StudentToString st2=new StudentToString("Aarohi",17,"General");
        System.out.println(st1);
        System.out.println(st2);

    }
}
