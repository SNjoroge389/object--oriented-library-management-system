package librarymanagement;

// Magazine extends LibraryItem (Inheritance)
public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(int id, String title, int issueNumber) {
        super(id, title);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() { return issueNumber; }
    public void setIssueNumber(int issueNumber) { this.issueNumber = issueNumber; }

    @Override
    public String getInfo() {
        return "Magazine: " + getTitle() + " [Issue: " + issueNumber + "]";
    }
}
