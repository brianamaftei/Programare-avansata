package Homework;

import Compulsory.*;

import java.util.Random;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {

        EntityManager em = EntityManagerFactorySingleton.getInstance().getEntityManagerFactory().createEntityManager();
        ArtistRepository artistRepo = new ArtistRepository();
        AlbumRepository albumRepo = new AlbumRepository();
        artistRepo.entityManager = em;
        albumRepo.entityManager = em;

        Random random = new Random();
        int numberOfRecords = 10000;

        // Insert artists
        long startTime = System.currentTimeMillis();
        em.getTransaction().begin();
        for (int i = 1; i <= numberOfRecords; i++) {
            Artist artist = new Artist();
            artist.setName("Fake Artist " + i);
            artistRepo.save(artist);
            if (i % 50 == 0) { // to avoid out-of-memory error
                em.flush();
                em.clear();
            }
        }
        em.getTransaction().commit();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to insert " + numberOfRecords + " artists: " + (endTime - startTime) + "ms");

        // Insert albums
        startTime = System.currentTimeMillis();
        em.getTransaction().begin();
        for (int i = 1; i <= numberOfRecords; i++) {
            Album album = new Album();
            album.setTitle("Fake Album " + i);
            album.setYear(2000 + random.nextInt(22));
            album.setArtistName("Fake Artist " + i);
            albumRepo.save(album);
            // to avoid out-of-memory error
            if (i % 50 == 0) {
                em.flush();
                em.clear();
            }
        }
        em.getTransaction().commit();
        endTime = System.currentTimeMillis();
        System.out.println("Time taken to insert " + numberOfRecords + " albums: " + (endTime - startTime) + "ms");

        em.getTransaction().begin();
        albumRepo.deleteAll();
        artistRepo.deleteAll();
        em.getTransaction().commit();
    }
}
