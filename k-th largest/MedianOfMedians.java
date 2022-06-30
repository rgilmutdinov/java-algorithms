class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickselect(nums, 0, nums.length - 1, k);
    }

    private int quickselect(int[] nums, int lo, int hi, int k) {
        while (lo < hi) {
            int pivot = getPivot(nums, lo, hi);
            int j = partition(nums, lo, hi, pivot);
            int top = nums.length - j;

            if (top == k) {
                return nums[j];
            }

            if (top > k) {
                lo = j + 1;
            } else {
                hi = j - 1;
            }
        }

        return nums[lo];
    }

    private int getPivot(int[] nums, int lo, int hi) {
        int n = hi - lo + 1;

        if (n <= 5) {
            Arrays.sort(nums, lo, hi + 1);
            return nums[lo + n / 2];
        }

        int groups = (n + 4) / 5;
        int[] median = new int[groups];
        for (int i = 0; i < groups; i++) {
            int s = lo + i * 5;
            int e = Math.min(s + 4, hi);
            Arrays.sort(nums, s, e + 1);
            median[i] = nums[s + (e - s) / 2];
        }

        if (groups == 1) {
            return median[0];
        }

        return quickselect(median, 0, groups - 1, groups / 2);
    }

    private int partition(int[] nums, int lo, int hi, int pivot) {
        int pivotIndex = getPivotIndex(nums, lo, hi, pivot);
        swap(nums, pivotIndex, lo);

        int i = lo + 1;
        int j = hi;
        while (true) {
            while (i < hi && nums[i] <= pivot) i++;
            while (j > lo && nums[j] >= pivot) j--;

            if (i >= j) {
                break;
            }

            swap(nums, i, j);
        }

        swap(nums, lo, j);
        return j;
    }

    private int getPivotIndex(int[] nums, int lo, int hi, int pivot) {
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == pivot) {
                return i;
            }
        }

        return lo;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}