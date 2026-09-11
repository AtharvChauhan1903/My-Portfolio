
public class challenge51 {
   public static void main() {
        book b1=new book();
        book b2=new book();
        b1.name="My Coding Journey";
        b1.Author="Me";
        b1.Isbnnumber=1234;
        book.totalBooks=10;
        b2.name="Test book";
        b2.Author="Ai";
        b2.Isbnnumber=13456;
        book.getTotalbooks();
        b1.borrowbook();
        b2.borrowbook();
        book.getTotalbooks();
        b1.returnBook();
        b2.returnBook();
        book.getTotalbooks();
    }

}
