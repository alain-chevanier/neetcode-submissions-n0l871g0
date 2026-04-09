class Solution {
    int[][] map = new int[][] {
        {}, // 0 no mapping 
        {}, // 1 no mapping
        {0, 1, 2}, // 2 mapped to {a, b, c}
        {3, 4, 5}, // 3
        {6, 7, 8}, // 4
        {9, 10, 11}, // 5
        {12, 13, 14}, // 6
        {15, 16, 17, 18}, // 7
        {19, 20, 21}, // 8
        {22, 23, 24, 25} // 9
    };
    List<String> answer;
    char[] letters;

    public List<String> letterCombinations(String digits) {
        answer = new LinkedList<>();
        letters = new char[digits.length()];
        if (digits.length() > 0) {
            generate(digits, 0);
        }
        return answer;
    }

    void generate(String digits, int idx) {
        if (idx == digits.length()) {
            answer.add(new String(letters));
            return;
        }
        int n = digits.charAt(idx) - '0';
        for (int v : map[n]) {
            int c = v + 'a';
            letters[idx] = (char) c;
            generate(digits, idx+1);
        }
    }
}
