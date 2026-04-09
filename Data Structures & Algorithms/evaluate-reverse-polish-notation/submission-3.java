
class Solution {
    static final Set<String> OPERATORS = new HashSet<>(List.of("+", "-", "*", "/"));
    static final Map<String,java.util.function.IntBinaryOperator> OPERATIONS = Map.of(
        "+", (a, b) -> a + b,
        "-", (a, b) -> a - b,
        "*", (a, b) -> a * b,
        "/", (a, b) -> a / b
    );

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack  = new Stack<>();
        for (var t : tokens) {
            if (OPERATIONS.containsKey(t)) {
                int a = stack.pop();
                int b = stack.pop();
                int res = OPERATIONS.get(t).applyAsInt(b, a);
                stack.push(res);
            } else {
                stack.push(Integer.parseInt(t));
            }

        }

        return stack.pop();
    }
}
