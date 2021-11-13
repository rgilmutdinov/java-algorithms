public class RollingHashes {
	private static final long MOD = 1_000_000_007L;
	private static final long BASE = 29L;

	private long[] powers; // precomputed powers of R
	private long[] hashes; // precomputed hashes of prefixes
	// private long[] backwardHashes; // precomputed backward hashes of prefixes

	public RollingHashes(String text) {
		int n = text.length();

		hashes = new long[n + 1];
		powers = new long[n + 1];
		// backwardHashes = new long[n + 1];

		powers[0] = 1;

		for (int i = 1; i <= n; i++) {
			hashes[i] = (hashes[i - 1] * BASE + text.charAt(i - 1)) % MOD;
			powers[i] = (powers[i - 1] * BASE) % MOD;

			// backwardHashes[i] = (backwardHashes[i - 1] + powers[i - 1] * text.charAt(i - 1)) % MOD;
		}
	}

	public long getHash(int l, int r) {
		return (hashes[r] - hashes[l] * powers[r - l] % MOD + MOD) % MOD;
	}
}
