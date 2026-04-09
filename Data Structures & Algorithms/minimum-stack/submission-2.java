class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> mins;

    public MinStack() {
        stack = new Stack<>();
        mins = new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);
        if (mins.isEmpty() || val < mins.peek()) {
            mins.push(val);
        } else {
            mins.push(mins.peek());
        }
    }
    
    public void pop() {
        stack.pop();
        mins.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return mins.peek();
    }
}
