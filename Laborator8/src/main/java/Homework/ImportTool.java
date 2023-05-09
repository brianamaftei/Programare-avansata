package Homework;

import Compulsory.AlbumDAO;
import Compulsory.ArtistDAO;
import Compulsory.GenreDAO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class ImportTool {

    public static void importData(String filePath, Connection connection) throws IOException, SQLException {
        try (FileReader fileReader = new FileReader(filePath, StandardCharsets.UTF_8)) {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().withFirstRecordAsHeader().parse(fileReader);

            ArtistDAO artistDAO = new ArtistDAO(connection);
            GenreDAO genreDAO = new GenreDAO(connection);
            AlbumDAO albumDAO = new AlbumDAO(connection);

            for (CSVRecord record : parser) {
                String artistName = record.get("Artist");
                String albumTitle = record.get("Album");
                int year = Integer.parseInt(record.get("Year"));
                String genreName = record.get("Genre");
                String subgenres = record.get("Subgenre");

                String[] allGenres = (genreName + ", " + subgenres).split(",\\s*");

                artistDAO.create(artistName);
                albumDAO.create(year, albumTitle, artistName, genreName);
                genreDAO.create(Arrays.toString(allGenres));
            }
        }
    }
}
