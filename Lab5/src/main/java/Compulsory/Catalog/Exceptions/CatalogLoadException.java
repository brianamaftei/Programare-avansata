package Compulsory.Catalog.Exceptions;

import java.io.IOException;

public class CatalogLoadException extends IOException {
    public CatalogLoadException(String message) {
        super(message);
    }
}
