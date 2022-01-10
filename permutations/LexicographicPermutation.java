public class Solution {
    public boolean nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }

        if (i < 0) {
            // reverse(nums, 0, nums.length - 1); // next permutation will be the first of
            // lexicographical order
            return false;
        }

        int j = nums.length - 1;
        while (nums[j] <= nums[i]) {
            j--;
        }

        swap(nums, i, j);
        reverse(nums, i + 1, nums.length - 1);
        return true;
    }

    public void nextPermutation2(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        if (i >= 0) {            
            int j = n - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        
        reverse(nums, i + 1, n - 1);
    }
    
    private void reverse(int[] nums, int lo, int hi) {
        while (lo < hi) {
            swap(nums, lo++, hi--);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}