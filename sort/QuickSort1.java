class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        quickSort(nums, 0, n - 1);

        return nums;
    }

    private void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;

        int mid = partition(nums, lo, hi);
        quickSort(nums, lo, mid);
        quickSort(nums, mid + 1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        int p = lo;
        int pivot = nums[lo];
        while (lo < hi) {
            while (lo < hi && nums[hi] >= pivot) hi--;
            while (lo < hi && nums[lo] <= pivot) lo++;

            swap(nums, lo, hi);
        }
        swap(nums, lo, p);
        return lo;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}