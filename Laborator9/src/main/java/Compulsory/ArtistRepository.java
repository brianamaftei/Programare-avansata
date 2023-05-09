package Compulsory;

import Homework.AbstractRepository;

public class ArtistRepository extends AbstractRepository<Artist> {
    public ArtistRepository() {
        super(Artist.class);
    }
}
