package org.example.Lab11.Helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ToSave implements Serializable {
    private final List<BoardData> boards = new ArrayList<>();

    public ToSave()
    {
    }
    public void addBoards(BoardData bd)
    {
        boards.add(bd);
    }
    public List<BoardData> getBoards()
    {
        return this.boards;
    }

}
