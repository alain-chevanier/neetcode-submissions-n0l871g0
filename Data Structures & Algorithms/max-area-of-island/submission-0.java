class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (visited[row][col] || grid[row][col] == 0) {
                    continue;
                }
                int area = calculateAreaFrom(row, col, grid, visited);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }

    int calculateAreaFrom(int row, int col, int[][] grid, boolean[][] visited) {
        var queue = new LinkedList<int[]>();
        visited[row][col] = true;
        queue.add(new int[] {row, col});
        
        int size = 1;
        while (queue.size() > 0) {
            int[] cur = queue.remove();
            int curRow = cur[0], curCol = cur[1];
            var neighbors = List.of(new int[]{curRow, curCol+1},
                                new int[]{curRow, curCol-1},
                                new int[]{curRow+1, curCol},
                                new int[]{curRow-1, curCol});
            for (var n : neighbors) {
                int r = n[0], c = n[1];
                if (isPositionInvalid(r, c, grid) ||
                    visited[r][c] ||
                    grid[r][c] == 0) {
                    continue;
                }
                visited[r][c] = true;
                queue.add(n);
                size++;
            }
        }
        return size;
        
    }

    boolean isPositionInvalid(int row, int col, int[][] grid) {
        return (row < 0) || 
            (col < 0) || 
            (row >= grid.length) || 
            (col >= grid[row].length);
    }
}
