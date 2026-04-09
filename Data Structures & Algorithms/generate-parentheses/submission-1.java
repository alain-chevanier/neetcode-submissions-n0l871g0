class Solution {
    public static Map<Integer,List<String>> memoization = 
        new HashMap<>() {{
            put(0, List.of(""));
            put(1, List.of("()"));
        }};

    public List<String> generateParenthesis(int n) {
        // n = 1 -> ()
        // n = 2 -> (()), ()()
        // n = 3 - () + f_2, (f_2)
        var answer = memoization.get(n);
        if (answer != null) {
            return answer;
        }
        if (n == 1) {
            memoization.put(1, List.of("()"));
            return memoization.get(n);
        }
        var set = new HashSet<String>();
        for (int i = 1; i <= n-1; i++) {
            var f_i = generateParenthesis(i);
            var f_n_min_i = generateParenthesis(n-i);
            for (var expr_left : f_i) {
                if (i == n-1) {
                    set.add("(" + expr_left + ")");
                }
                for (var expr_right: f_n_min_i) {
                    set.add(expr_left + expr_right);
                }
            }
            
        }
        memoization.put(n, new ArrayList<>(set));
        return memoization.get(n);
    }
}

/*
[,,,,,,,,,,,]
[,,,,,,,"(())(())",,,,,]
*/
