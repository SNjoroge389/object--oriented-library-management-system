package librarymanagement;

// Book extends LibraryItem (Inheritance)
public class Book extends LibraryItem {
    private String author;
    private int year;
    private String isbn;

    public Book(int id, String title, String author, int year, String isbn) {
        super(id, title);
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    @Override
    public String getInfo() {
        return "Book: " + getTitle() + " by " + author + " (" + year + "), ISBN: " + isbn;
    }
}
