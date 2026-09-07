class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        int n = s.length();

        // dp = total number of distinct subsequences (including empty) using s[0..i-1]
        // last[c] = value of dp right after the most recent occurrence of character c
        int[] last = new int[26];
        java.util.Arrays.fill(last, -1);

        long dp = 1; // empty subsequence counts as 1 (removed from the final answer)

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            long newDp = (dp * 2) % MOD;

            if (last[c] != -1) {
                newDp = (newDp - last[c] + MOD) % MOD;
            }

            last[c] = (int) dp; // dp value *before* processing this character becomes the "last" for c
            dp = newDp;
        }

        // subtract the empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}