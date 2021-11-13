public class Solution {
	public int[] zFunction(String s) {
		int n = s.length();
		int[] z = new int[n];
		for (int i = 1, l = 0, r = 0; i < n; i++) {
			if (i <= r) {
				z[i] = Math.min(r - i + 1, z[i - l]);
			}

			while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
				z[i]++;
			}

			if (i + z[i] - 1 > r) {
				l = i;
				r = i + z[i] - 1;
			}
		}
		return z;
	}

	public int search(String str, String pattern) {
		int n = pattern.length();
		if (n == 0) {
			return 0;
		}

		int m = str.length();
		if (m < n) {
			return -1;
		}

		String concat = pattern + "#" + str;
		int[] z = zFunction(concat);
		for (int i = 0; i < m; i++) {
			if (z[i + n + 1] == n) {
				return i;
			}
		}
		return -1;
	}
}