public class Solution {
    public boolean nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
		
		if (i < 0) {
			// reverse(nums, 0, nums.length - 1); // next permutation will be the first of lexicographical order
			return false;		
		}
        
		int j = nums.length - 1;
		while (nums[j] <= nums[i]) {
			j--;
		}
		
		swap(nums, i, j);        
        reverse(nums, i + 1);
		return true;
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}