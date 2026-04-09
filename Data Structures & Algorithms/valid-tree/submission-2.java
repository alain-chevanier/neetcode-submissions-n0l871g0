class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n-1) {
            return false;
        }
        // a grpah is a tree if it is a dag, and if the inDegree is at most one for any vertex
        UnionFind uf = new UnionFind(n);
        for (var edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return false;
            }
        }
        return uf.sets == 1;
    }

    // boolean bfs()
}

class UnionFind {
    int[] parent;
    int[] rank;
    int sets;

    UnionFind(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            this.rank[i] = 1;
        }
        this.sets = n;
    }

    int find(int i) {
        if (i == this.parent[i]) {
            return i;
        }
        this.parent[i] = find(this.parent[i]);
        return this.parent[i];
    }

    boolean union(int i, int j) {
        int parentI = find(i);
        int parentJ = find(j);

        if (parentI == parentJ) {
            // already in the same set
            return false;
        }

        if (this.rank[parentI] > this.rank[parentJ]) {
            this.parent[parentJ] = parentI;
        } else if (this.rank[parentI] < this.rank[parentJ]) {
            this.parent[parentI] = parentJ;
        } else {
            this.parent[parentJ] = parentI;
            this.rank[parentI]++;
        }
        this.sets--;
        return true;
    }
}
