public class Solution {
	public int[] prefixFunction(String s) {
		int n = s.length();
		int[] pi = new int[n];
		for (int i = 1; i < n; i++) {
			int j = pi[i - 1];
			while (j > 0 && s.charAt(i) != s.charAt(j)) {
				j = pi[j - 1];
			}

			if (s.charAt(i) == s.charAt(j))
				j++;
			pi[i] = j;
		}

		return pi;
	}

	// alternative implementation
	public int[] prefixFunction2(String s) {
		int n = s.length();
		int[] lps = new int[n];
		for (int i = 1, j = 0; i < n; i++) {
			while (j > 0 && s.charAt(i) != s.charAt(j))
				j = lps[j - 1];
			if (s.charAt(i) == s.charAt(j))
				lps[i] = ++j;
		}
	}

	// alternative implementation
	public int[] prefixFunction3(String s) {
		int n = s.length();
		int[] pi = new int[n];
		for (int i = 1; i < n; i++) {
			int j = pi[i - 1]; /* trying length j + 1 */

			while (j > 0 && s.charAt(j) != s.charAt(i)) {
				j = pi[j - 1];
			}

			if (s.charAt(j) == s.charAt(i)) {
				pi[i] = j + 1;
			}
		}

		return pi;
	}
}