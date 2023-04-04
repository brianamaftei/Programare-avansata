package Compulsory;

import java.util.*;
import java.util.concurrent.locks.*;

public class Robot implements Runnable {
    private String name;
    private boolean running;
    private Exploration explore;
    private int row;
    private int col;
    private final Lock lock = new ReentrantLock();

    public void setExplore(Exploration explore) {
        this.explore = explore;
    }

    public Robot(String name) {
        this.name = name;
    }

    public List<Token> extractTokens(int n) {
        List<Token> extractedTokens = new ArrayList<>(n);
        lock.lock();
        try {
            extractedTokens = explore.getMemo().extractTokens(n);
        } finally {
            lock.unlock();
        }
        return extractedTokens;
    }

    @Override
    public void run() {
        Random randomValue = new Random();
        lock.lock();
        try {
            row = randomValue.nextInt(explore.getN());
            col = randomValue.nextInt(explore.getN());
            explore.getMap().visit(row, col, this);
            running = true;
        } finally {
            lock.unlock();
        }
        int[][] directions = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        boolean allVisited;
        while (running) {
            allVisited = true;
            for (int i = 0; i < directions.length; i++) {
                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];
                if (newRow >= 0 && newRow < explore.getN() && newCol >= 0 && newCol < explore.getN() && !explore.isVisited(newRow, newCol)) {
                    allVisited = false;
                    lock.lock();
                    try {
                        row = newRow;
                        col = newCol;
                        explore.getMap().visit(row, col, this);
                        System.out.println(name + " has visited cell " + row + " " + col);
                    } finally {
                        lock.unlock();
                    }
                    break;
                }
            }
            if (allVisited) {
                running = false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}