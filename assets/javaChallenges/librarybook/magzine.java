package librarybook;

public class magzine extends LibraryItems {
    private final int issueNumber;

    public magzine(String title,String Author,int itemId,int issueNumber) {
        super(itemId,title,Author);
        this.issueNumber = issueNumber;
    }
}
