class Solution {
    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int islands = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (visited[row][col] || 
                    grid[row][col] == '0') {
                    continue;
                }
                bfs(new int[]{row, col}, grid, visited);
                islands++;
            }
        }
        return islands;
    }

    void bfs(int[] pos, char[][] grid, boolean[][] visited) {
        var queue = new LinkedList<int[]>();
        queue.add(pos);
        visited[pos[0]][pos[1]] = true;

        //System.out.println("Starting at: " + Arrays.toString(pos));
        // System.out.println("Already visited: " + Arrays.toString(visited));

        while (queue.size() > 0) {
            int[] curPos = queue.remove();
            //System.out.println("Exploring: " + Arrays.toString(pos));
            List<int[]> neighbors = 
                List.of(new int[] {curPos[0]-1, curPos[1]},
                        new int[] {curPos[0]+1, curPos[1]},
                        new int[] {curPos[0], curPos[1]+1},
                        new int[] {curPos[0], curPos[1]-1});
            for (var n : neighbors) {
                if (!validPosition(n, grid) || 
                    visited[n[0]][n[1]] ||
                    grid[n[0]][n[1]] == '0') {
                    continue;
                }
                queue.add(n);
                visited[n[0]][n[1]] = true;
            }

        }

    }

    boolean validPosition(int[] pos, char[][] grid) {
        int row = pos[0];
        int col = pos[1];
        return (row >= 0 && row < grid.length) &&
                (col >= 0 && col < grid[row].length);
    }
}
