package Compulsory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumDAO {
    public void create(int releaseYear, String title, String artist, String genresString) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO albums (release_year, title, artist) VALUES (?, ?, ?)")) {
            pstmt.setInt(1, releaseYear);
            pstmt.setString(2, title);
            pstmt.setString(3, artist);
            pstmt.executeUpdate();
        }

        // Insert album-genre associations in the junction table
        int albumId = findAlbumIdByTitle(title);
        if (albumId != -1) {
            String[] genres = genresString.split(",");
            try (PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO album_genres (album_id, genre_id) VALUES (?, ?)")) {
                for (String genre : genres) {
                    int genreId = findOrCreateGenreIdByName(genre.trim());
                    pstmt.setInt(1, albumId);
                    pstmt.setInt(2, genreId);
                    pstmt.executeUpdate();
                }
            }
        }
    }

    public int findAlbumIdByTitle(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT id FROM albums WHERE title = ?")) {
            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt("id") : -1;
            }
        }
    }

    public int findOrCreateGenreIdByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT id FROM genres WHERE name = ?")) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    // Genre does not exist, create a new genre
                    try (PreparedStatement insertPstmt = con.prepareStatement(
                            "INSERT INTO genres (name) VALUES (?)",
                            PreparedStatement.RETURN_GENERATED_KEYS)) {
                        insertPstmt.setString(1, name);
                        insertPstmt.executeUpdate();
                        try (ResultSet generatedKeys = insertPstmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                return generatedKeys.getInt(1);
                            } else {
                                throw new SQLException("Failed to create genre, no ID obtained.");
                            }
                        }
                    }
                }
            }
        }
    }
}
