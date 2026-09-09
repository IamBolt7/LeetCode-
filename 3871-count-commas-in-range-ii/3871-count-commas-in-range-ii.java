class Solution {
    public long countCommas(long n) {
        long total = 0;
        long low = 1;
        long high = 9;
        int digits = 1;

        while (low <= n) {
            long upper = Math.min(high, n);
            long count = upper - low + 1;
            long commasPerNumber = (digits - 1) / 3;

            total += count * commasPerNumber;

            if (high > (Long.MAX_VALUE - 9) / 10) {
                // next 'high' would overflow; since n is bounded, this only
                // happens once we've already covered all of n's digit range
                break;
            }

            low = high + 1;
            high = high * 10 + 9;
            digits++;
        }

        return total;
    }
}