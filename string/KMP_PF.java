/**
 * Knuth-Morris-Pratt Algorithm for Pattern Matching uses prefix-function
 * implementation
 */
public class KMP {
	public int[] prefix(String s) {
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

	public int search(String str, String pattern) {
		int n = pattern.length();
		if (n == 0) {
			return 0;
		}

		int m = str.length();
		if (m == 0 || m < n) {
			return -1;
		}

		int[] pf = prefix(pattern);
		int j = 0;

		for (int i = 0; i < m; i++) {
			while (j > 0 && pattern.charAt(j) != str.charAt(i)) {
				j = pf[j - 1];
			}

			if (pattern.charAt(j) == str.charAt(i)) {
				j++;
			}

			if (j == n) {
				return i - n + 1;
			}
		}

		return -1;
	}

	// Returns the index of the first occurrrence of the pattern string in the text
	// string.
	// makes usage of prefix function values: pattern + "#" + str
	public int search2(String str, String pattern) {
		int n = pattern.length();
		if (n == 0) {
			return 0;
		}

		if (str.length() < n) {
			return -1;
		}

		String concat = pattern + "#" + str;
		int[] pf = prefix(concat);
		for (int i = n + 1; i < pf.length; i++) {
			if (pf[i] == n) {
				return i - 2 * n;
			}
		}
		return -1;
	}
}