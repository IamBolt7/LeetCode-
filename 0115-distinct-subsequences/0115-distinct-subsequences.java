class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // dp[i][j] = number of distinct subsequences of s[0..i-1] that equal t[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // An empty t can always be formed (in exactly one way: delete everything)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Option 1: don't use s[i-1] to match t[j-1] — carry forward
                dp[i][j] = dp[i - 1][j];

                // Option 2: if characters match, we can also use s[i-1] to match t[j-1]
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}