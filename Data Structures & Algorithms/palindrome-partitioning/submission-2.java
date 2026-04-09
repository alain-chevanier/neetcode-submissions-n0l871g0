class Solution {
    List<List<String>> answer;
    int[] indices;
    String str;
    int[][] dpIsPalindrom;

    public List<List<String>> partition(String s) {
        indices = new int[s.length() + 1];
        answer = new LinkedList<>();
        str = s;
        dpIsPalindrom = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                dpIsPalindrom[i][j] = -1;
            }
        }

        generate(0, 0);

        return answer;
    }

    void generate(int beg, int idx) {
        if (beg == str.length()) {
            answer.add(buildAnswer(idx));
            return;
        }
        for (int i = beg + 1; i <= str.length(); i++) {
            if (!isPalindrom(beg, i)) {
                continue;
            }
            indices[idx] = beg;
            indices[idx+1] = i;
            generate(i, idx+1);
        }
    }

    List<String> buildAnswer(int idx) {
        List<String> res = new LinkedList<>();
        for (int i = 1; i <= idx; i++) { 
            res.add(str.substring(indices[i-1], indices[i]));
        }
        return res;
    }

    boolean isPalindrom(int beg, int end) {
        int s = beg, e = end-1;
        end--;
        if (dpIsPalindrom[s][e] != -1) {
            return dpIsPalindrom[s][e] == 1;
        }
        while(beg < end) {
            if (str.charAt(beg++) != str.charAt(end--)) {
                dpIsPalindrom[s][e] = 0;
                return false;
            }
        }
        dpIsPalindrom[s][e] = 1;
        return true;
    }
}
