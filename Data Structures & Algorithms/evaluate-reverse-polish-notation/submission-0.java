
class Solution {
    public int evalRPN(String[] tokens) {
        Map<String,java.util.function.BinaryOperator<Integer>> funcs = Map.of(
            "+", (a, b) -> a + b, 
            "-", (a, b) -> a - b, 
            "*", (a, b) -> a * b, 
            "/", (a, b) -> a / b 
        );
        var nums = new Stack<Integer>();
        for (var token : tokens) {
            if (funcs.containsKey(token)) {
                int rightOp = nums.pop();
                int leftOp = nums.pop();
                nums.push(funcs.get(token).apply(leftOp, rightOp));
            } else {
                nums.push(Integer.parseInt(token));
            }
        }
        return nums.pop();
    }
}
