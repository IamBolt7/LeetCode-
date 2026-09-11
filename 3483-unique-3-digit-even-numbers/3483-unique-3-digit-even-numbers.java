class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        for (int hundreds = 1; hundreds <= 9; hundreds++) {
            for (int tens = 0; tens <= 9; tens++) {
                for (int units = 0; units <= 8; units += 2) {
                    int[] need = new int[10];
                    need[hundreds]++;
                    need[tens]++;
                    need[units]++;

                    boolean valid = true;
                    for (int d = 0; d <= 9; d++) {
                        if (need[d] > freq[d]) {
                            valid = false;
                            break;
                        }
                    }

                    if (valid) count++;
                }
            }
        }

        return count;
    }
}