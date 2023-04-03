package Compulsory.Document;

public class Article extends Document {
    public Article() {
    }

    public Article(String id, String title) {
        super(id, title);
    }

    public Article(String id, String title, String location, String date) {
        super(id, title, location, date);
    }

    @Override
    public String toString() {
        return "Article: " + super.toString();
    }
}
