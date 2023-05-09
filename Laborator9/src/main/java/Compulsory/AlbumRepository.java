package Compulsory;

import Homework.AbstractRepository;
// AlbumDAO == AlbumRepository
public class AlbumRepository extends AbstractRepository<Album> {
    public AlbumRepository() {
        super(Album.class);
    }
}
