class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;
        int sum1 = 0, cnt1 = 0;
        int sum2 = 0, cnt2 = 0;

        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);
            if (c == '?') cnt1++;
            else sum1 += c - '0';
        }

        for (int i = half; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') cnt2++;
            else sum2 += c - '0';
        }

        int totalQ = cnt1 + cnt2;
        if (totalQ % 2 == 1) return true; // odd total -> Alice always wins

        int diff = sum1 - sum2;
        // Bob wins iff diff exactly cancels out the '?' count imbalance
        if (2 * diff == 9 * (cnt2 - cnt1)) return false;
        return true;
    }
}