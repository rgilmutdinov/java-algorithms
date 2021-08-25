class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean exchanges = false;
            for (int j = 1; j < n; j++) {
                if (nums[j - 1] > nums[j]) {
                    exchanges = true;
                    swap(nums, j - 1, j);
                }
            }

            if (!exchanges) break;
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