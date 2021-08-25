class Solution {
	// Get the length of longest common subsequence
	// DP approach with matrix m*n. Time: O(m*n), Space: O(m*n)
    public int longestCommonSubsequenceLength1(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) continue;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

	// Get the length of longest common subsequence
	// DP approach with array n. Time: O(m*n), Space: O(n)
    public int longestCommonSubsequenceLength2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[] dp = new int[n + 1];
        for (int i = 0; i <= m; i++) {
            int[] temp = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) continue;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    temp[j] = dp[j - 1] + 1;
                } else {
                    temp[j] = Math.max(dp[j], temp[j - 1]);
                }
            }
            dp = temp;
        }

        return dp[n];
    }

	// Get the longest common subsequence
	// DP approach with matrix m*n. Time: O(m*n), Space: O(m*n)
	public String longestCommonSubstring(String s1, String s2){
        int m = s1.length();
        int n = s2.length();

        int max = 0;

        int[][] dp = new int[m][n];
        int endIndex = -1;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // Add 1 to the diagonal value
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }

                    if (max < dp[i][j]) {
                        max = dp[i][j];
                        endIndex = i;
                    }
                }

            }
        }

        // We want String upto endIndex, we are using endIndex+1 in substring.
        return s1.substring(endIndex - max + 1, endIndex + 1);
    }
}