
public class book {
    String name;
    int Isbnnumber;
    String Author;
    static int totalBooks;

   void borrowbook( ){
        if(book.totalBooks<1){
            System.out.println("Sorry the book is out of stock");

        }
        else  {
            System.out.println("Book is borrowed succesfully");
            book.totalBooks--;

        }
    }
    void returnBook(){
        System.out.println("The book is succesfully returned");
        book.totalBooks++;
    }
    static void getTotalbooks(){
        System.out.println(book.totalBooks);
    }
}


