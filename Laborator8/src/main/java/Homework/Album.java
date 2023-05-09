package Homework;

public class Album {
    private int id;
    private int releaseYear;
    private String name;
    private String artistName;
    private String[] genres;

    public Album(int releaseYear, String name, String artistName, String genresString) {
        this.id = 0;
        this.releaseYear = releaseYear;
        this.name = name;
        this.artistName = artistName;
        this.genres = genresString.split("\\s*,\\s*");
    }

    public int getId() {
        return id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artistName;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }
}
