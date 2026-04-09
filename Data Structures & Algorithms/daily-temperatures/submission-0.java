class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // (t, ind)
        var stack = new Stack<Pair<Integer,Integer>>();
        var res = new int[temperatures.length];
        for (int i=0; i<temperatures.length; i++) {
            int t = temperatures[i];
            while (!stack.isEmpty() && stack.peek().getKey() < t) {
                var pair = stack.pop();
                res[pair.getValue()] = i - pair.getValue();
            }
            stack.push(new Pair(t, i));
        }
        return res;
    }
}
