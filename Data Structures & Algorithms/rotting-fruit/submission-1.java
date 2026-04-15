class Solution {
    static final int[][] DIRECTIONS = new int[][] {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1} 
    };
    int[][] grid;
    int rows;
    int columns;

    public int orangesRotting(int[][] grid) {
        this.rows = grid.length;
        this.columns = grid[0].length;
        this.grid = grid;

        Queue<int[]> queue = new ArrayDeque<>();
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if (this.grid[row][col] == 2) {
                    queue.offer(new int[] {row, col, 0});
                    this.grid[row][col] = 0;
                }
            }
        }

        int time = bfs(queue);

        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if (this.grid[row][col] != 0) {
                    return -1;
                }
            }
        }

        return time;
    }

    int bfs(Queue<int[]> queue) {
        int max = 0;
        while (queue.size() > 0) {
            int[] cur = queue.poll();
            max = Math.max(max, cur[2]);
            for (int[] dir : DIRECTIONS) {
                int nextRow = dir[0] + cur[0],
                    nextCol = dir[1] + cur[1];
                if (isPositionInvalid(nextRow, nextCol) 
                    || this.grid[nextRow][nextCol] != 1) {
                    continue;
                }
                this.grid[nextRow][nextCol] = 0;
                queue.offer(new int[] {nextRow, nextCol, cur[2] + 1});
            }
        }
        return max;
    }

    boolean isPositionInvalid(int row, int col) {
        return row < 0 || col < 0 || row >= rows || col >= columns;
    }
}
