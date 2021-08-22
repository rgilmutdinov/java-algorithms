class Solution {
    public int[] sortArray(int[] nums) { 
        int n = nums.length;
        quickSort(nums, 0, n - 1);
        
        return nums;
    }
    
    private void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        
        int mid = partition(nums, lo, hi);
        quickSort(nums, lo, mid - 1);
        quickSort(nums, mid + 1, hi);
    }
    
    private int partition(int[] nums, int lo, int hi) {        
        int i = lo;
        int j = hi + 1;        
        int pivot = nums[lo];
        
        while (true) {
            while (i < hi && nums[++i] <= pivot) ;
            while (j > lo && nums[--j] >= pivot) ;

            if (i >= j) break;

            swap(nums, i, j);
        }
        
        swap(nums, lo, j);
        return j;
    }
    
    private void swap(int[] nums, int i, int j) {        
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;        
    }
}