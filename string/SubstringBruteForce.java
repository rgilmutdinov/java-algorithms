public class Solution {
	public int search(String pat, String txt) {
		int m = pat.length();
		int n = txt.length();
		for (int i = 0; i <= n - m; i++) {
			int j;
			for (j = 0; j < m; j++) {
				if (txt.charAt(i+j) != pat.charAt(j)) {
					break;
				}
			}
			if (j == m) return i; // found
		}
		return -1; // not found
	}
	
	public int searchEx(String pat, String txt) {
        int m = pat.length();
        if (m == 0) return 0;
        
        int n = txt.length();
        if (n == 0) return -1;                
        
		int i, j;
		for (i = 0, j = 0; i < n && j < m; i++) {
			if (txt.charAt(i) == pat.charAt(j)) j++;
			else { i -= j; j = 0; }
		}
		if (j == m) return i - m; // found
		else return -1; // not found
	}
}