package Homework.Exceptions;

import java.io.IOException;

public class InvalidWriteFileException extends Exception {
    public InvalidWriteFileException(String message) {
        super(message);
    }
}
