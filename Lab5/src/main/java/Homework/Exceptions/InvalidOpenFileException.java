package Homework.Exceptions;

import Homework.Exceptions.Exception;

import java.io.IOException;

public class InvalidOpenFileException extends Exception {
    public InvalidOpenFileException(String s) {
        super(s);
    }
}
