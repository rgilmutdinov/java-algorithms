public class Solution {
    public void printPermutations(int[] nums) {
		int n = nums.length;
        int[] c = new int[n];

        System.out.println(Arrays.toString(nums));

        int i = 0;
        while (i < n) {
            if (c[i] < i) {
                if ((i & 1) == 0) { 					
                    swap(nums,0,i);
                } else {
                    swap(nums, c[i], i);
                }
				
                System.out.println(Arrays.toString(nums));
                c[i] += 1;
                i = 0;
            } else {
                c[i] = 0;
                i += 1;
            }
        }
    }
}