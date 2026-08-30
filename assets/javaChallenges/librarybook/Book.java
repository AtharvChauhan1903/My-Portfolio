package librarybook;

public class Book extends LibraryItems {
    private final  int isbnNumber;

    public Book(String Author,String title,int itemId,int isbnNumber){
        super(itemId,Author,title);
        this.isbnNumber=isbnNumber;
    }
}
