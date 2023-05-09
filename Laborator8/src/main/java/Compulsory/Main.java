package Compulsory;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void insertValuesIntoDatabase(Connection connection) {
        try {
            var artists = new ArtistDAO(connection);
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            artists.create("Queen");
            var genres = new GenreDAO(connection);
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");
            genres.create("Disco");
            connection.commit();
            var albums = new AlbumDAO(connection);
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            albums.create(1982, "Thriller", "Michael Jackson", "Funk,Soul,Pop");
            albums.create(1980, "The Game", "Queen", "Rock,Disco");
            connection.commit();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            Database.rollback();
        }
    }

    public static void main(String[] args) {

        try (Connection connection = Database.getConnection()) {
            insertValuesIntoDatabase(connection);
            var artists = new ArtistDAO(connection);
            var genres = new GenreDAO(connection);
            connection.commit();
            System.out.println(artists.findById(1));
            System.out.println(artists.findByName("Michael Jackson"));
            System.out.println(genres.findById(1));
            System.out.println(genres.findByName("Soul"));
            Database.printTables(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            Database.rollback();
        }
    }
}