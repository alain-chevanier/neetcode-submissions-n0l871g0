class Solution {
    static int[][] directions = new int[][] {
        {0,1}, // right
        {1, 0}, // down
        {-1,0}, // up
        {0,-1} // left
        
    };
    int[][] matrix;
    boolean[][] pacific;
    boolean[][] atlantic;
    int rows;
    int cols;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.rows = heights.length;
        this.cols = heights[0].length;
        this.matrix = heights;

        this.pacific = new boolean[rows][cols];
        this.atlantic = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            dfs(r, 0, this.pacific);
            dfs(r, cols-1, this.atlantic);
        }

        for (int c = 0; c < cols; c++) {
            dfs(0, c, this.pacific);
            dfs(rows-1, c, this.atlantic);
        }

        List<List<Integer>> answer = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    answer.add(List.of(r,c));
                }
            }
        }
        return answer;
    }

    void dfs(int r, int c, boolean[][] visited) {
        if (visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        for (var dir : directions) {
            int nextR = r + dir[0],
                nextC = c + dir[1];
            if (isPositionInvalid(nextR, nextC)
                || this.matrix[nextR][nextC] < this.matrix[r][c]) {
                continue;
            }
            dfs(nextR, nextC, visited);
        }
    }

    boolean isPositionInvalid(int r, int c) {
        return r < 0 || c < 0 || r >= this.rows || c >= this.cols;
    }
}