class Solution {
    int[][] dirs = new int[][] {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0},
    };
    boolean[][] visited;
    char[][] board;
    String word;

    public boolean exist(char[][] _board, String _word) {
        board = _board;
        word = _word;
        int rows = board.length;
        int cols = board[0].length;
        visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] != word.charAt(0)) {
                    continue;
                }
                visited[r][c] = true;
                
                if (explore(r, c, 1)) {
                    return true;
                }
                visited[r][c] = false;
            }
        }

        return false;
    }

    boolean explore(int r, int c, int idx) {
        if (idx == word.length()) {
            return true;
        }
        for (var d : dirs) {
            int r1 = r + d[0],
                c1 = c + d[1];
            if (isPositionInvalid(r1, c1) 
                || visited[r1][c1]
                || board[r1][c1] != word.charAt(idx)) {
                continue;
            }
            visited[r1][c1] = true;
            boolean decision = explore(r1, c1, idx+1);
            visited[r1][c1] = false;
            if (decision) {
                return true;
            }
        }
        return false;
    }

    boolean isPositionInvalid(int r, int c) {
        int rows = board.length;
        int cols = board[0].length;
        return (r<0) || (r>=rows) || (c<0) || (c>=cols);
    }
}
