package Bonus;

import Homework.Album;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Playlist {
    private final String name;
    private Timestamp createTime;
    private final ArrayList<Album> albums;

    public Playlist(String name) {
        this.name = name;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.albums = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
}