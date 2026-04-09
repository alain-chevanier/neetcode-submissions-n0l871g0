class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // 0: temp, 1: idx
        Deque<int[]> deque = new LinkedList<>();

        int[] res = new int[temperatures.length];
        for (int idx = 0; idx < temperatures.length; idx++) {
            int temp = temperatures[idx];
            while (deque.size() > 0
            && deque.getLast()[0] < temp) {
                int[] cur = deque.removeLast();
                res[cur[1]] = idx - cur[1];
            }
            deque.addLast(new int[] {temp, idx});
        }  
        return res;
    }
}
