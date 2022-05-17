/*
Given an m x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (m - 1, n - 1)) such that:
	All the visited cells of the path are 0.
	All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).

The length of a clear path is the number of visited cells of this path.
*/

class Solution {
    private static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];

        queue.add(new int[] { 0, 0 });
        visited[0][0] = true;

        int dist = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                int[] p = queue.poll();
                if (p[0] == m - 1 && p[1] == n - 1) {
                    return dist;
                }

                for (int[] dir : DIRECTIONS) {
                    int r = p[0] + dir[0];
                    int c = p[1] + dir[1];
                    if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c] || grid[r][c] == 1) {
                        continue;
                    }

                    visited[r][c] = true;
                    queue.add(new int[] { r, c });
                }
            }

            dist++;
        }

        return -1;
    }
}