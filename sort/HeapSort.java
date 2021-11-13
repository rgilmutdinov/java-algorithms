class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            sink(nums, i, n - 1);
        }

        for (int i = n - 1; i >= 1; i--) {
            swap(nums, 0, i);
            sink(nums, 0, i - 1);
        }

        return nums;
    }

    private void sink(int[] nums, int k, int hi) {
        while (2 * k + 1 <= hi) {
            int j = 2 * k + 1;
            if (j < hi && nums[j] < nums[j + 1])
                j++;
            if (nums[k] >= nums[j])
                break;
            swap(nums, k, j);
            k = j;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}