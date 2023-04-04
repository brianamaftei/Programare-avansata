package Compulsory;

import java.util.*;
import java.util.concurrent.locks.*;
public class ExplorationMap {
    private final List<List<List<Token>>> matrix;
    private boolean[][] visited;

    public ExplorationMap(int n) {
        matrix = new ArrayList<>(n);
        visited = new boolean[n][n];
        int i, j;

        for (i = 0; i < n; i++) {
            List<List<Token>> row = new ArrayList<>(n);
            for (j = 0; j < n; j++) {
                visited[i][j] = false;
                List<Token> cell = new ArrayList<>();
                row.add(cell);
            }
            matrix.add(row);
        }
    }

    public boolean isVisited(int row, int col) {
        return visited[row][col];
    }

    public synchronized boolean visit(int row, int col, Robot robot) {
        List<Token> cell = matrix.get(row).get(col);
        synchronized (cell) {
            ReadWriteLock lock = new ReentrantReadWriteLock();
            lock.writeLock().lock();
            try {
                if (!visited[row][col]) {
                    cell.addAll(robot.extractTokens(matrix.size()));
                    visited[row][col] = true;
                    return true;
                } else {
                    return false;
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    @Override
    public String toString() {
        return "ExplorationMap{" + "matrix=" + matrix + ", visited=" + Arrays.toString(visited) + '}';
    }
}