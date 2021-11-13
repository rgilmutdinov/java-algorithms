// Given a string s, return the number of palindromic substrings in it.
// https://leetcode.com/problems/palindromic-substrings/

class Solution1 {
    public class RollingHashes {
        private static final long MOD = 1_000_000_007L;
        private static final long BASE = 29L;

        private long[] powers; // precomputed powers of R
        private long[] hashes; // precomputed hashes of prefixes

        public RollingHashes(String text) {
            int n = text.length();

            hashes = new long[n + 1];
            powers = new long[n + 1];

            powers[0] = 1;

            for (int i = 1; i <= n; i++) {
                hashes[i] = (hashes[i - 1] * BASE + text.charAt(i - 1)) % MOD;
                powers[i] = (powers[i - 1] * BASE) % MOD;
            }
        }

        public long getHash(int l, int r) {
            return (hashes[r] - hashes[l] * powers[r - l] % MOD + MOD) % MOD;
        }
    }

    public int countSubstrings(String s) {
        RollingHashes fgen = new RollingHashes(s);
        RollingHashes bgen = new RollingHashes(new StringBuilder(s).reverse().toString());

        int count = 0;
        int n = s.length();
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                long fhash = fgen.getHash(i, i + len);
                long bhash = bgen.getHash(n - i - len, n - i);
                if (fhash == bhash) {
                    count++;
                }
            }
        }

        return count;
    }
}

class Solution2 {
    private int countPalindroms(String s, int lo, int hi) {
        int n = s.length();
        while (lo >= 0 && hi < n && s.charAt(lo) == s.charAt(hi)) {
            lo--;
            hi++;
        }

        return (hi - lo) / 2;
    }

    // Expand around possible centers
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += countPalindroms(s, i, i + 1);
            count += countPalindroms(s, i, i);
        }
        return count;
    }
}