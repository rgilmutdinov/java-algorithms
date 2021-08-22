class Solution {
    public int[] sortArray(int[] nums) { 
        int n = nums.length;
        for (int i = 1; i < n; i++) {            
            for (int j = i; j > 0 && nums[j - 1] > nums[j]; j--) {
                swap(nums, j - 1, j);                
            }
        }
        
        return nums;
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}