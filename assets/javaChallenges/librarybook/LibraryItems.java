package librarybook;

public class LibraryItems {
    public int itemId;
    public String title;
    public String Author;
    public int count=10;

    public LibraryItems(int itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.Author = author;
    }

    public void checkout(){
        if(count>0) {
            System.out.println("Item succesfully bought");
            count--;
        }else
            System.out.println("Sorry item is out of stock");
    }
    public void returnItem(){
        if(count<10){
            System.out.println("Item returned successfully");
            count++;
        }else
            System.out.println("Item was never borrowed");
    }
}
