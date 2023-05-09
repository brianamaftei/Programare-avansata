CREATE TABLE artists
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE genres
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE albums
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    artist_name VARCHAR(255) NOT NULL,
    year        INTEGER      NOT NULL
);

CREATE TABLE album_genres
(
    album_id INTEGER NOT NULL REFERENCES albums (id),
    genre_id INTEGER NOT NULL REFERENCES genres (id)
);

CREATE TABLE playlists
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE playlist_albums
(
    playlist_id INT NOT NULL REFERENCES playlists (id) ON DELETE CASCADE,
    album_id    INT NOT NULL REFERENCES albums (id) ON DELETE CASCADE,
    PRIMARY KEY (playlist_id, album_id)
);

