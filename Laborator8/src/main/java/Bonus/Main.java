package Bonus;

import Compulsory.AlbumDAO;
import Compulsory.ArtistDAO;
import Compulsory.Database;
import Compulsory.GenreDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


    public static void main(String[] args) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            insertValuesIntoDatabase(connection);
            String playlistName = "My Favorite Albums";
            Timestamp createdAt = new Timestamp(System.currentTimeMillis());
            List<Integer> albumIds = new ArrayList<>();
            albumIds.add(1);
            albumIds.add(2);
            albumIds.add(3);

            PlaylistDAO playlistDAO = new PlaylistDAO(connection);
            playlistDAO.create(playlistName, createdAt, albumIds);
            connection.commit();
            Database.printTables(connection);
        }
    }
}