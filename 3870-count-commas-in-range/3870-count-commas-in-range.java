class Solution {
    public int countCommas(int n) {
        long total = 0;
        long low = 1, high = 9;
        int digits = 1;

        while (low <= n) {
            long upper = Math.min(high, n);
            long count = upper - low + 1;
            long commasPerNumber = (digits - 1) / 3;

            total += count * commasPerNumber;

            low = high + 1;
            high = high * 10 + 9;
            digits++;
        }

        return (int) total;
    }
}