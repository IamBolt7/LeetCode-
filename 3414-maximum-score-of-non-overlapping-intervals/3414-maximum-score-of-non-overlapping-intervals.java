class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
      int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> iv = intervals.get(i);
            arr[i] = new Interval(iv.get(0), iv.get(1), iv.get(2), i);
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a.left, b.left));

        T[][] memo = new T[n][5];
        T res = dp(arr, memo, 0, 4);
        int[] ans = new int[res.selected.size()];
        for (int i = 0; i < ans.length; i++) ans[i] = res.selected.get(i);
        return ans;
    }

    private record Interval(int left, int right, int weight, int originalIndex) {}
    private record T(long weight, List<Integer> selected) {}

    private T dp(Interval[] arr, T[][] memo, int i, int quota) {
        if (i == arr.length || quota == 0) return new T(0, List.of());
        if (memo[i][quota] != null) return memo[i][quota];

        T skip = dp(arr, memo, i + 1, quota);

        Interval cur = arr[i];
        int j = findFirstGreater(arr, i + 1, cur.right());
        T nextRes = dp(arr, memo, j, quota - 1);

        List<Integer> newSelected = new ArrayList<>(nextRes.selected());
        newSelected.add(cur.originalIndex());
        Collections.sort(newSelected);
        T pick = new T(cur.weight() + nextRes.weight(), newSelected);

        T best = (pick.weight() > skip.weight()
                || (pick.weight() == skip.weight() && compareLists(pick.selected(), skip.selected()) < 0))
                ? pick : skip;
        return memo[i][quota] = best;
    }

    // First index > startFrom (inclusive) whose left > rightBoundary
    private int findFirstGreater(Interval[] arr, int startFrom, int rightBoundary) {
        int lo = startFrom, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid].left() > rightBoundary) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private int compareLists(List<Integer> a, List<Integer> b) {
        int m = Math.min(a.size(), b.size());
        for (int i = 0; i < m; i++) {
            int c = Integer.compare(a.get(i), b.get(i));
            if (c != 0) return c;
        }
        return Integer.compare(a.size(), b.size());  
    }
}