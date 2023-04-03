package Homework;

import Homework.Exceptions.InvalidWriteFileException;

import java.io.IOException;

public interface Command {
    public void execCommand() throws InvalidWriteFileException, IOException;
}
