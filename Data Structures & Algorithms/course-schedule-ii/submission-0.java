class Solution {
    int[] inDegree;
    Map<Integer,Set<Integer>> adjacencies;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        this.adjacencies = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            this.adjacencies.put(i, new HashSet<>());
        }

        this.inDegree = new int[numCourses];
        for (var edge : prerequisites) {
            this.inDegree[edge[0]]++;
            this.adjacencies.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (this.inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> visitedVertices = new ArrayList<>(numCourses);
        while (queue.size() > 0) {
            int cur = queue.poll();
            visitedVertices.add(cur);
            for (int neigh : this.adjacencies.get(cur)) {
                this.inDegree[neigh]--;
                if (this.inDegree[neigh] == 0) {
                    queue.offer(neigh);
                }
            }
        }

        if (visitedVertices.size() == numCourses) {
            int[] res = new int[numCourses];
            int idx = 0;
            for(int v : visitedVertices) {
                res[idx++] = v;
            }
            return res;
        }
        return new int[0];
        
    }
}
