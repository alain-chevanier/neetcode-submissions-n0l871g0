class Solution {
    List<List<String>> answer;
    int[] indices;
    String str;

    public List<List<String>> partition(String s) {
        indices = new int[s.length() + 1];
        answer = new LinkedList<>();
        str = s;

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
        end--;
        while(beg < end) {
            if (str.charAt(beg++) != str.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
