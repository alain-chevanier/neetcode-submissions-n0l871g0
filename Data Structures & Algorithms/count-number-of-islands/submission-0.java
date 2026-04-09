class Solution {
    public int numIslands(char[][] grid) {
        var isVisited = new boolean[grid.length][grid[0].length];
        int connectedComponents = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (!isVisited[row][column]
                    && grid[row][column] == '1') {
                    bfs(row, column, grid, isVisited);
                    connectedComponents++;
                }
            }    
        }
        return connectedComponents;
    }

    void bfs(int row, int column, char[][] grid, boolean[][] isVisited) {
        var queue = new LinkedList<int[]>();
        queue.add(new int[]{row, column});
        while(!queue.isEmpty()) {
            var position = queue.remove();
            int r = position[0];
            int c = position[1];
            addEntry(r, c-1, grid, isVisited, queue);
            addEntry(r, c+1, grid, isVisited, queue);
            addEntry(r-1, c, grid, isVisited, queue);
            addEntry(r+1, c, grid, isVisited, queue);
        }
    }

    void addEntry(int row, int column, 
        char[][] grid, boolean[][] isVisited,
        List<int[]> queue) {
        if (row >= 0 && row < grid.length 
            && column >= 0 && column < grid[row].length 
            && grid[row][column] == '1' && !isVisited[row][column]) {
            queue.add(new int[] {row, column});
            isVisited[row][column] = true;
        }
    }
}
