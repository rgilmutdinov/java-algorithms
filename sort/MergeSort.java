class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        int[] aux = new int[n];
        mergeSort(nums, aux, 0, n - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int[] aux, int lo, int hi) {
        if (lo >= hi) return;

        int mid = lo + (hi - lo) / 2;
        mergeSort(nums, aux, lo, mid);
        mergeSort(nums, aux, mid + 1, hi);
        merge(nums, aux, lo, mid, hi);
    }

    private void merge(int[] nums, int[] aux, int lo, int mid, int hi) {
        System.arraycopy(nums, lo, aux, lo, hi - lo + 1);
        // for (int k = lo; k <= hi; k++) {
        //     aux[k] = nums[k];
        // }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)         nums[k] = aux[j++];
            else if (j > hi)          nums[k] = aux[i++];
            else if (aux[i] < aux[j]) nums[k] = aux[i++];
            else                      nums[k] = aux[j++];
        }
    }
}