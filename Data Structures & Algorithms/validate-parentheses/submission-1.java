class Solution {
    public boolean isValid(String s) {
        var map = Map.of(')', '(',
                        '}', '{',
                        ']', '[');
        var openings = Set.of('(', '[', '{');
        var stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (openings.contains(c)) {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            char o = stack.pop();
            if (map.get(c) != o) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
