/*
Given an m x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (m - 1, n - 1)) such that:
	All the visited cells of the path are 0.
	All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).

The length of a clear path is the number of visited cells of this path.
*/

class Solution {
	private static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    private static final int ID_HEAD =  1;
    private static final int ID_TAIL = -1;

    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }

        if (m == 1 && n == 1 && grid[0][0] == 0) {
            return 1;
        }

        Queue<int[]> qHead = new ArrayDeque<>();
        Queue<int[]> qTail = new ArrayDeque<>();
        int[][] exploredBy = new int[n][n];

        qHead.add(new int[] { 0, 0 });
        qTail.add(new int[] { m - 1, n - 1 });

        exploredBy[0][0] = ID_HEAD;
        exploredBy[m - 1][n - 1] = ID_TAIL;

        int dist = 2;
        while (!qHead.isEmpty() && !qTail.isEmpty()) {
            if (explore(grid, exploredBy, qHead, ID_HEAD)) {
                return dist;
            }

            dist++;

            if (explore(grid, exploredBy, qTail, ID_TAIL)) {
                return dist;
            }

            dist++;
        }

        return -1;
    }

    private boolean explore(int[][] grid, int[][] exploredBy, Queue<int[]> queue, int explorer) {
        int m = grid.length;
        int n = grid[0].length;

        int size = queue.size();
        int otherExplorer = -explorer;
        while (size-- > 0) {
            int[] p = queue.poll();
            int row = p[0];
            int col = p[1];

            for (int[] dir : DIRECTIONS) {
                int neiRow = row + dir[0];
                int neiCol = col + dir[1];

                if (neiRow < 0 || neiCol < 0 || neiRow >= m || neiCol >= n) {
                    continue;
                }

                if (grid[neiRow][neiCol] == 1 || exploredBy[neiRow][neiCol] == explorer) {
                    continue;
                }

                if (exploredBy[neiRow][neiCol] == otherExplorer) {
                    return true;
                }

                queue.add(new int[] { neiRow, neiCol });
                exploredBy[neiRow][neiCol] = explorer;
            }
        }

        return false;
    }
}