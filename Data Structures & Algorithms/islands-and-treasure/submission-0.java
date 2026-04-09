class Solution {
    static final int WATER = -1;
    static final int TREASURE = 0;
    static final int LAND = 2147483647;
    static final int[][] directions = new int[][] {{1, 0}, {-1, 0},
                                                   {0, 1}, {0, -1}};

    public void islandsAndTreasure(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                // perform bfs from each treasure to each land cell
                if (grid[row][col] == TREASURE) {
                    bfs(row, col, grid);
                }
                
            }
        }
    }

    void bfs(int row, int col, int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {row, col, 0});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0],
                c = cur[1],
                dist = cur[2];
            for (var dir : directions) {
                int nR = r + dir[0],
                    nC = c + dir[1];
                if (isPositionInvalid(r + dir[0], c + dir[1], grid) ||
                    distanceCannotBeImproved(grid[nR][nC], dist + 1)) {
                    continue;
                }
                queue.add(new int[] {nR, nC, dist+1});
                grid[nR][nC] = dist+1;
            }
        }
    }

    boolean isPositionInvalid(int row, int col, int[][] grid) {
        return (row < 0) ||
                (col < 0) ||
                (row >= grid.length) ||
                (col >= grid[row].length);
    }

    boolean distanceCannotBeImproved(int cellValue, int newDist) {
        return (cellValue < 1) ||
                (newDist >= cellValue);
    }
}
