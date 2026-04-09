class Solution {
    static final int EMPTY = 0;
    static final int FRESH = 1;
    static final int ROTTEN = 2;

    static final int[][] directions = new int[][] {
        {1,0}, {-1,0}, {0,1}, {0,-1}
    };

    public int orangesRotting(int[][] grid) {
        int rows = grid.length,
            cols = grid[0].length;
        // for each fresh fruit contains the minumum amount of minutes needed
        // for it to get rotten, or zero it the fruit is rotten from the beggining
        // or the cell is empty
        int[][] rottenDist = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rottenDist[r][c] = grid[r][c] == FRESH ? Integer.MAX_VALUE : 0;
            }
        }

        // Apply BFS from each rotten fruit and check the minimum distance to each fruit
        // it can also affect
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == ROTTEN) {
                    queue.add(new int[] {r, c});
                    // bfs(r, c, grid, rottenDist);
                }
            }
        }

        bfs(queue, grid, rottenDist);

        // if any cell in rottenDist is MAX_VALUE then there is a fruit that cannot get rotten
        // other wise return the maximum from rottenDist.
        int max = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                max = Math.max(max, rottenDist[r][c]);
            }
        }

        return max == Integer.MAX_VALUE ? -1 : max;
    }

    void bfs(Queue<int[]> queue, int[][] grid, int[][] rottenDist) {
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0],
                c = cur[1];
            for (var dir : directions) {
                int nR = r + dir[0],
                    nC = c + dir[1],
                    dist = rottenDist[r][c] + 1;
                if (isPositionInvalid(nR, nC, grid) ||
                    grid[nR][nC] != FRESH ||
                    // cannot improve rotten distance
                    rottenDist[nR][nC] <= dist) {
                    continue;
                }
                rottenDist[nR][nC] = dist;
                queue.add(new int[] {nR, nC});
            }
        }
    }

    boolean isPositionInvalid(int r, int c, int[][] grid) {
        return (r < 0) ||
                (c < 0) ||
                (r >= grid.length) ||
                (c >= grid[r].length);
    }
}
