package Compulsory;

import java.util.*;
import java.util.concurrent.locks.*;

public class Exploration {
    private final int n;
    private final ExplorationMap map;
    private final SharedMemory memo;
    private final List<Robot> robots;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock lock = new ReentrantLock();

    public Exploration(int n) {
        this.n = n;
        this.map = new ExplorationMap(n);
        this.memo = new SharedMemory(n);
        this.robots = new ArrayList<>();
    }

    public void start() {
        for (Robot robot : robots) {
            Thread thread = new Thread(robot);
            thread.start();
        }
    }

    public void addRobot(Robot robot) {
        if (robot != null) {
            robot.setExplore(this);
            robots.add(robot);
        }
    }

    public boolean isVisited(int row, int col) {
        readWriteLock.readLock().lock();
        try {
            return map.isVisited(row, col);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public int getN() {
        return n;
    }

    public ExplorationMap getMap() {
        return map;
    }

    public SharedMemory getMemo() {
        return memo;
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    public Lock getLock() {
        return lock;
    }

    public static void main(String args[]) {
        int n = 4;
        var explore = new Exploration(n);
        explore.addRobot(new Robot("R1"));
        explore.addRobot(new Robot("R2"));
        explore.addRobot(new Robot("R3"));
        explore.start();
    }
}