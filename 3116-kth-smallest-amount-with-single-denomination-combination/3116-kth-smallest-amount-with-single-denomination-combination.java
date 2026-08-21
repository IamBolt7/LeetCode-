class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long lo = 1, hi = (long) 1e15;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (countMultiplesUpTo(mid, coins) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    // Count numbers in [1, x] divisible by at least one coin (inclusion-exclusion)
    private long countMultiplesUpTo(long x, int[] coins) {
        int n = coins.length;
        long count = 0;

        // iterate over all non-empty subsets
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = Integer.bitCount(mask);
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    lcm = lcmOf(lcm, coins[i]);
                    if (lcm > x) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            // inclusion-exclusion sign
            if (bits % 2 == 1) {
                count += x / lcm;
            } else {
                count -= x / lcm;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private long lcmOf(long a, long b) {
        long g = gcd(a, b);
        long result = a / g;
        // guard against overflow when multiplying
        if (result > (long) 2e15 / b) return (long) 2e15; // sentinel large value
        return result * b;
    }
}