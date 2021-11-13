class Solution {
    public int[] sortArray(int[] nums) {
        mergeBUSort(nums);
        return nums;
    }

    private void mergeBUSort(int[] nums) {
        int n = nums.length;
        int[] aux = new int[n];
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n - len; lo += 2 * len) {
                int mid = lo + len - 1;
                int hi = Math.min(mid + len, n - 1);
                merge(nums, aux, lo, mid, hi);
            }
        }
    }

    private void merge(int[] nums, int[] aux, int lo, int mid, int hi) {
        System.arraycopy(nums, lo, aux, lo, hi - lo + 1);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)              nums[k] = aux[j++];
            else if (j > hi)          nums[k] = aux[i++];
            else if (aux[i] < aux[j]) nums[k] = aux[i++];
            else                      nums[k] = aux[j++];
        }
    }
}