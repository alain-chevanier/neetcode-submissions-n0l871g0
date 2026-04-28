class Solution {
    int[][] isPalindrom;

    public int countSubstrings(String s) {
        int N = s.length();
        this.isPalindrom = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            this.isPalindrom[i][i] = 2;
        }

        int palindromCount = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = i; j < N; j++) {
                if (s.charAt(i) == s.charAt(j) 
                    && ((i+1 >= j-1) || isPalindrom[i+1][j-1] == 2)) {
                    isPalindrom[i][j] = 2;
                    palindromCount++;
                }
            }
        }
        return palindromCount;
    }
}
