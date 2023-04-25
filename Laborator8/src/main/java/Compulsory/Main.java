package Compulsory;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            var artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            var genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");
            Database.getConnection().commit();
            var albums = new AlbumDAO();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
            Database.getConnection().commit();
            System.out.println(artists.findById(1));
            System.out.println(artists.findByName("Michael Jackson"));
            System.out.println(genres.findById(1));
            System.out.println(genres.findByName("Soul"));
            Database.printTables();
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            Database.rollback();
        }
    }
}