public class Solution {
	public String shortestPalindrome1(String s) {
		int n = s.length();
		
		int pos = -1;

		long B = 29;
		long MOD = 1000000007;
		long POW = 1;
		long hash1 = 0, hash2 = 0;
		
		for (int i = 0; i < n; i++, POW = POW * B % MOD) {
			hash1 = (hash1 * B + s.charAt(i) - 'a' + 1) % MOD;
			hash2 = (hash2 + (s.charAt(i) - 'a' + 1) * POW) % MOD;
			if (hash1 == hash2) pos = i;
		}
		return new StringBuilder().append(s.substring(pos + 1, n)).reverse().append(s).toString();
	}
    
    public String shortestPalindrome2(String s) {
        String rev = new StringBuilder(s).reverse().toString();
		String combined = s + "#" + rev;
		int[] pf = prefixFunction(combined);

		return new StringBuilder(s.substring(pf[pf.length - 1])).reverse().toString() + s;
	}
	
	public int[] prefixFunction(String s) {
		int n = s.length();
		int[] pi = new int[n];	
		for (int i = 1; i < n; i++) {
			int j = pi[i - 1];
			while (j > 0 && s.charAt(i) != s.charAt(j)) {
				j = pi[j - 1];
			}
			
			if (s.charAt(i) == s.charAt(j)) j++;
			pi[i] = j;
		}
		
		return pi;
	}
}