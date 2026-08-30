public class challenge70 {
    private String Name;
    private int Age;
    private String  Caste;
    public  challenge70(String name,int Age,String Caste){
        this.Name=name;
        this.Age=Age;
        this.Caste=Caste;
    }

    @Override
    public String toString() {
        return "Name="+Name+",Age="+Age+",Caste="+Caste;
    }

    static void main(){
        challenge70 st1=new challenge70("Atharv",20,"General");
        challenge70 st2=new challenge70("Aarohi",17,"General");
        System.out.println(st1);
        System.out.println(st2);

    }
}
