package Compulsory.Catalog;

import Compulsory.Catalog.Exceptions.CatalogLoadException;
import Compulsory.Catalog.Exceptions.CatalogSaveException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/**
 * The class has methods for saving and loading Catalog objects to and from system
 */
public class CatalogUtil {
    /**
     * Saves the given catalog to the specified path
     * @param catalog the catalog to be saved
     * @param path    the path on the file system where catalog will be saved
     * @throws CatalogSaveException while saving catalog if an error occurs
     */
    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(path), catalog);
        } catch (IOException exception) {
            throw new CatalogSaveException("Failed to save catalog");
        }
    }

    /**
     * Loads catalog from the specified path on the file system
     * @param path the path where catalog can be found
     * @return the loaded catalog
     * @throws CatalogLoadException if an error occurs while loading
     */
    public static Catalog load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Catalog catalog = new Catalog();
        try {
            catalog = objectMapper.readValue(new File(path), Catalog.class);
        } catch (IOException exception) {
            throw new CatalogLoadException("Failed to load catalog");
        }
        return catalog;
    }

}
