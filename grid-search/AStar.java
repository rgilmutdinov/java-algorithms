/*
Given an m x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (m - 1, n - 1)) such that:
	All the visited cells of the path are 0.
	All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).

The length of a clear path is the number of visited cells of this path.

For details, see: https://leetcode.com/problems/shortest-path-in-binary-matrix/solution/
*/

class Solution {
    private static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }

        // int[]: { row, col, currDistance, totalEstimate}, order by totalEstimate
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p[3]));

        pq.add(new int[] { 0, 0, 1, estimate(grid, 0, 0) });
        boolean[][] visited = new boolean[m][n];

        while (!pq.isEmpty()) {
            int[] best = pq.poll();

            int row = best[0];
            int col = best[1];
            int dist = best[2];

            if (row == m - 1 && col == n - 1) {
                return dist;
            }

            if (visited[row][col]) {
                continue;
            }

            visited[row][col] = true;

            for (int[] dir : DIRECTIONS) {
                int neiRow = row + dir[0];
                int neiCol = col + dir[1];

                if (neiRow < 0 || neiCol < 0 || neiRow >= m || neiCol >= n) {
                    continue;
                }

                if (grid[neiRow][neiCol] == 1 || visited[neiRow][neiCol]) {
                    continue;
                }

                int newDist = dist + 1;
                int totalEstimate = newDist + estimate(grid, neiRow, neiCol);

                pq.add(new int[] { neiRow, neiCol, newDist, totalEstimate });
            }
        }

        return -1;
    }

    private int estimate(int[][] grid, int row, int col) {
        int remainingRows = grid.length - row - 1;
        int remainingCols = grid[0].length - col - 1;
        return Math.max(remainingRows, remainingCols);
    }
}