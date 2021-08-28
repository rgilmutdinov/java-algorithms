class Solution {
    private int maxPalindrome(String s, int lo, int hi) {
        while (lo >= 0 && hi < s.length() && s.charAt(lo) == s.charAt(hi)) {
            lo--;
            hi++;
        }

        return hi - lo - 1;
    }

	// Expand Around Center O(n^2)
    public String longestPalindrome(String s) {
        int maxlen = 0;
        int begin = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = maxPalindrome(s, i, i);
            int len2 = maxPalindrome(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (maxlen < len) {
                maxlen = len;
                begin = i - (len - 1) / 2;
            }
        }

        return s.substring(begin, begin + maxlen);
    }

	// DP solution. Space: O(n^2), Time: O(n^2)
	public String longestPalindromeDP(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int maxlen = 0;
        int begin = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j < 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }

                if (dp[j][i] && i - j + 1 > maxlen) {
                    maxlen = i - j + 1;
                    begin = j;
                }
            }
        }

        return s.substring(begin, begin + maxlen);
    }
	
	// Polynomial hashing solution. Time: O(n^2), Space:  O(n)
	public String longestPalindromeRH(String s) {        
        int n = s.length();
        
        if (n == 0) return "";
        
        long p = 31L;
        long mod = 1_000_000_009L;
        
        long[] powers = new long[n + 1];
        long[] fh = new long[n + 1]; // forward hash
        long[] bh = new long[n + 1]; // backward hash
        
        Arrays.fill(powers, 1);
        for (int i = 0; i < n; i++) {
            long c = s.charAt(i);            
            fh[i + 1] = (fh[i] + (c * powers[i])) % mod;
            bh[i + 1] = ((p * bh[i]) + c) % mod;
            powers[i + 1] = (powers[i] * p) % mod;
        }
        
        for (int len = n; len >= 1; len--) {
            for (int offset = 0; offset <= n - len; offset++) {
                
                long hash1 = (fh[len + offset] - fh[offset] + mod) % mod;
                
                long hash2 = (powers[offset] * bh[len + offset] - powers[len + offset] * bh[offset]) % mod;                
                hash2 = (hash2 + mod) % mod;
                
                if (hash1 == hash2) {
                    return s.substring(offset, offset + len);
                }
            }
        }
        
        return "";
    }
}