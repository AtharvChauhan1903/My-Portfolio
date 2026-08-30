package librarybook;

public class library {
    static void main() {
        Book b1=new Book("RB","My life",12,23);
        b1.returnItem();
        b1.checkout();
        b1.returnItem();
        magzine m1= new magzine("Style","KKK",11,14);
        m1.returnItem();
        m1.checkout();
        m1.returnItem();
        dvd d1=new dvd("G.O.A.T","Timmy is an asian and mmy parents copare me to him nonstop and Timmy can't Stop one upping me",99,44);
        d1.returnItem();
        d1.checkout();
        d1.returnItem();
    }
}
