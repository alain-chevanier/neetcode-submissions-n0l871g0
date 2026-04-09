class Solution {
    List<String> answer;
    char[] pAnswer;

    public List<String> generateParenthesis(int n) {
        answer = new LinkedList<>();
        pAnswer = new char[n*2];
        generate(0, 0, 0);

        return answer;    
    }

    void generate(int open, int close, int idx) {
        if (idx == pAnswer.length && open == close) {
            answer.add(new String(pAnswer));
            return;
        }

        if (open < pAnswer.length/2) {
            pAnswer[idx] = '(';
            generate(open+1, close, idx+1);
        }
        

        if (close + 1 <= open) {
            pAnswer[idx] = ')';
            generate(open, close+1, idx+1);
        }

    }
}
