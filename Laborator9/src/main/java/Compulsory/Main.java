package Compulsory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Get the EntityManagerFactory from the singleton
        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory();

        // Create repositories
        ArtistRepository artistRepository = new ArtistRepository();
        AlbumRepository albumRepository = new AlbumRepository();
        GenreRepository genreRepository = new GenreRepository();
        PlaylistRepository playlistRepository = new PlaylistRepository();

        // Inject the EntityManager into the repositories
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        artistRepository.entityManager = entityManager;
        albumRepository.entityManager = entityManager;
        genreRepository.entityManager = entityManager;
        playlistRepository.entityManager = entityManager;

        // Create an artist
        Artist queen = new Artist();
        queen.setName("Queen");

        entityManager.getTransaction().begin();
        artistRepository.save(queen);
        entityManager.getTransaction().commit();

        // Create a genre
        Genre rock = new Genre();
        rock.setName("Rock");

        entityManager.getTransaction().begin();
        genreRepository.save(rock);
        entityManager.getTransaction().commit();

        // Create an album with the rock genre and Queen as the artist
        Album album = new Album();
        album.setTitle("Greatest Hits");
        album.setArtistName("Queen");
        album.setYear(1981);
        album.getGenres().add(rock);

        entityManager.getTransaction().begin();
        albumRepository.save(album);
        entityManager.getTransaction().commit();

        // Create a playlist with the Queen album
        Playlist playlist = new Playlist();
        playlist.setName("My Playlist");
        playlist.getAlbums().add(album);

        entityManager.getTransaction().begin();
        playlistRepository.save(playlist);
        entityManager.getTransaction().commit();

        // Fetch and print all playlists
        entityManager.getTransaction().begin();
        List<Playlist> playlists = playlistRepository.findAll();
        entityManager.getTransaction().commit();

        for (Playlist p : playlists) {
            System.out.println(p);
            for (Album a : p.getAlbums()) {
                System.out.println(a);
                System.out.println(artistRepository.findByName(a.getArtistName()));
                for (Genre g : a.getGenres()) {
                    System.out.println(g);
                }
            }
        }
        // Show named queries
        entityManager.getTransaction().begin();
        List<Artist> artists = artistRepository.findByName("Queen");
        List<Album> albums = albumRepository.findByName("Greatest Hits");
        List<Genre> genres = genreRepository.findByName("Rock");
        playlists = playlistRepository.findByName("My Playlist");
        entityManager.getTransaction().commit();

        System.out.println("Artists: " + artists.size());
        System.out.println("Albums: " + albums.size());
        System.out.println("Genres: " + genres.size());
        System.out.println("Playlists: " + playlists.size());

        // Delete all information from the database
        entityManager.getTransaction().begin();
        playlistRepository.deleteAll();
        albumRepository.deleteAll();
        genreRepository.deleteAll();
        artistRepository.deleteAll();
        entityManager.getTransaction().commit();

        // Close the EntityManager and the EntityManagerFactory
        entityManager.close();
        EntityManagerFactorySingleton.getInstance().close();
    }
}

