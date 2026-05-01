class Solution {
    int[][] matrix;
    int[][] dp;
    static final int[][] DIRECTIONS = new int[][] {
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public int longestIncreasingPath(int[][] matrix) {
        // let the matrix be viewed as a graph, where 
        //.    for a given positions u and v, there is an
        //      edge from u to v, if u and v are neighbors in
        //      the matrix, and matrix[u] < matrix[v]
        // By definition this graph is a DAG, so we can consider
        //.   traversing the graph in topological order to calculate
        //    the longest distance to each vertex
        // Let dp[u] be the longest distance from any vertex to u.
        //    The farthest vertex from u is always going to be a
        //     vertex with in-degree 0.
        // 1. find the positions within the matrix that represent
        //    a vertex with in-degre 0.
        // 2. travers the graph in topological order from the 
        //    vertices with in-dregree 0.
        // 3. Imagine the current vertex is u, and v is a valid 
        //.   neighbor, then dp[v] = max(dp[v], 1 + dp[u]).
        this.matrix = matrix;
        
        int[][] inDegree = buildInDegree();
        Queue<int[]> queue = findStartingPoints(inDegree);
        return traverseDAG(queue, inDegree);
    }

    int[][] buildInDegree() {
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] inDegree = new int[M][N];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                for (int[] dir : DIRECTIONS) {
                    int row = r + dir[0],
                        col = c + dir[1];
                    if (isPositionInvalid(row, col)) {
                        continue;
                    }
                    if (matrix[r][c] < matrix[row][col]) {
                        inDegree[row][col]++;
                    }
                }
            }
        }
        return inDegree;
    }

    Queue<int[]> findStartingPoints(int[][] inDegree) {
        Queue<int[]> queue = new ArrayDeque<>();
        int M = matrix.length;
        int N = matrix[0].length;
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (inDegree[r][c] == 0) {
                    queue.offer(new int[] {r, c});
                }
            }
        }
        return queue;
    }

    int traverseDAG(Queue<int[]> queue, int[][] inDegree) {
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] dp = new int[M][N];

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                dp[r][c] = 1;
            }
        }

        int max = 0;
        while (queue.size() > 0) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                int[] cur = queue.poll();
                int r = cur[0],
                    c = cur[1];
                
                for (int[] dir : DIRECTIONS) {
                    int row = r + dir[0],
                        col = c + dir[1];
                    if (isPositionInvalid(row, col) || 
                        matrix[row][col] <= matrix[r][c]) {
                        continue;
                    }
                    
                    dp[row][col] = Math.max(dp[row][col], 1 + dp[r][c]);
                    inDegree[row][col]--;
                    if (inDegree[row][col] == 0) {
                        queue.offer(new int[] {row, col});
                    }
                }
            }
            max++;
        }

        return max;
    }

    boolean isPositionInvalid(int r, int c) {
        return r < 0 || c < 0 || 
            r >= matrix.length || c >= matrix[r].length;
    }
}