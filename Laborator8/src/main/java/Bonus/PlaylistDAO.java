package Bonus;

import java.sql.*;
import java.util.List;

public class PlaylistDAO {
    private final Connection connection;

    public PlaylistDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(String name, Timestamp timestamp, List<Integer> albumIds) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("insert into playlists (name, created_at) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setTimestamp(2, timestamp);
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int playlistId = generatedKeys.getInt(1);
                    for (int albumId : albumIds) {
                        try (PreparedStatement playlistAlbumsStmt = connection.prepareStatement("insert into playlist_albums (playlist_id, album_id) values (?, ?)")) {
                            playlistAlbumsStmt.setInt(1, playlistId);
                            playlistAlbumsStmt.setInt(2, albumId);
                            playlistAlbumsStmt.executeUpdate();
                        }
                    }
                } else {
                    throw new SQLException("Failed to create playlist, no ID obtained.");
                }
            }
        }
    }
}

