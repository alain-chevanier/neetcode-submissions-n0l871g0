class Solution {
    public String longestPalindrome(String s) {
        int N = s.length();

        // [i,j] == 2 is a palindrom
        //       == 1 is not a palindrom
        //       == 0 undecided
        int[][] isPalindrom = new int[N][N];
        int pos = 0;
        /*int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            isPalindrom[i][i] = 2;
        }*/

        int max = 1;
        for (int i = N-1; i >= 0; i--) {
            isPalindrom[i][i] = 2;
            //System.out.printf("isPalindrom[%d][%d] => %d\n", i, i, isPalindrom[i][i]);
            char c_i = s.charAt(i);
            for (int j = i+1; j < N; j++) {
                char c_j = s.charAt(j);
                if (c_i == c_j &&
                    ((i+1 >= j-1) || isPalindrom[i+1][j-1] == 2)) {
                    isPalindrom[i][j] = 2;
                    if (j-i+1 > max) {
                        pos = i;
                        max = j-i+1;
                    }
                } else {
                    isPalindrom[i][j] = 1;
                }
                //System.out.printf("isPalindrom[%d][%d] => %d\n", i, j, isPalindrom[i][j]);
            }
        }

        return s.substring(pos, pos + max);
    }


    // let the DP(i) the longest palindrom that ends at the i-th char of S
    // LET j be the index at which the palindrom ending at i-1 begins
    //    IF j-1 >= 0 AND s[j-1] == s[i] 
    //    THEN the substring [j-1, i] is a palindrom of length DP(i-1) + 2
    // LET DP(i-1) be even AND LET assume that the palindrom that ends in i-1 starts a j
    //    if the substring (j+1, i-1) is also a palindrom AND S[j] == S[i]
    //.   then the substring [j, i] is a palindrom of length DP(i-1) + 1
    // IF S[i] == S[i-1] THEN the substring [i-1,i] is a palindrom of length 2



    // fundamentals, saber lo basico del problema
    // complejidad
    // debugging y testing
    // 
}
