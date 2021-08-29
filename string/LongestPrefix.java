// https://leetcode.com/problems/longest-happy-prefix/
class Solution {
    public String longestPrefix(String s) {        
        int n = s.length();
		int[] pi = new int[n];	
		for (int i = 1; i < n; i++) {
            char ci = s.charAt(i);
            
			int j = pi[i - 1];
			while (j > 0 && ci != s.charAt(j)) {
				j = pi[j - 1];
			}
			
			if (ci == s.charAt(j)) j++;
			pi[i] = j;            
		}
		
        return s.substring(0, pi[n - 1]);
    }
}