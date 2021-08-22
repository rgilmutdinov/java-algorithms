class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int min = findMin(nums, i, n - 1);
            if (min != i) {
                int tmp = nums[i];
                nums[i] = nums[min];
                nums[min] = tmp;
            }
        }
        
        return nums;
    }
    
    private int findMin(int[] nums, int lo, int hi) {        
        int min = lo;
        for (int i = lo + 1; i <= hi; i++) {
            if (nums[i] < nums[min]) {
                min = i;
            }
        }
        
        return min;
    }
}