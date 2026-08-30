package challenge82;

public class Car {
    public String model;
    public String companyName;
    public int noOfSeats;
    public float fuel;
    private Engine engine;
      static class Engine{
      private int pistons;
      private  int rings;

         public void setPistons(int pistons) {
             this.pistons = pistons;
         }

         public int getPistons() {
             return pistons;
         }

         public int getRings() {
             return 4*pistons;
         }

     }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Car(String model, String companyName) {
        this.model = model;
        this.companyName = companyName;
        this.fuel=5;
        this.engine = new Engine();
    }

    public Car(String model, String companyName, float fuel) {
        this.model = model;
        this.companyName = companyName;
        this.fuel = fuel;
       this.engine=new Engine();
    }

    public void Drive(){
        if(fuel>10) {
            System.out.println("The car is driving");
            fuel--;
        }
        else if (fuel<=10&&fuel>0) {
            System.out.println("The car is driving in reserve mode but has low fuel get it refueled");
            fuel--;
        }else
            System.out.println("No fuel the car cannot drive");
    }
    public void Refuel(){
        System.out.println("The car has been refueled");
        fuel+=5;
    }

    public Engine getEngine() {
        return engine;
    }
}
