package Compulsory.Document;

public class Book extends Document {
    public Book() {
    }

    public Book(String id, String title) {
        super(id, title);
    }

    public Book(String id, String title, String location, String date) {
        super(id, title, location, date);
    }

    @Override
    public String toString() {
        String str = "Book: " + super.toString();
        return str;
    }
}
