package librarybook;

public class dvd extends LibraryItems {
    float duration;

    public dvd(String Author, String title, int itemId, float duration) {
        super(itemId,Author, title);
        this.duration = duration;
    }
}