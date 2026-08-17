class Solution {
    public int stoneGameV(int[] stoneValue) {
                int n = stoneValue.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                int best = 0;

                for (int k = i; k < j; k++) {
                    int left = prefix[k + 1] - prefix[i];
                    int right = prefix[j + 1] - prefix[k + 1];

                    if (left < right) {
                        best = Math.max(best, left + dp[i][k]);
                    } else if (right < left) {
                        best = Math.max(best, right + dp[k + 1][j]);
                    } else {
                        best = Math.max(best, left + Math.max(dp[i][k], dp[k + 1][j]));
                    }
                }

                dp[i][j] = best;
            }
        }

        return dp[0][n - 1];
    }
}