class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int pos = quickselect(nums, k, 0, n - 1);
        return nums[pos];
    }
    
    private int quickselect(int[] nums, int k, int lo, int hi) {
        if (lo >= hi) {
            return lo;
        }
        
        int j = partition(nums, lo, hi);        
        int top = nums.length - j;
        
        if (top == k) return j;        
        
        if (top < k) {
            return quickselect(nums, k, lo, j - 1);
        }
        
        return quickselect(nums, k, j + 1, hi);
    }
    
    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        int i = lo + 1;
        int j = hi;   
        
        while (true) {
            while (i < hi && nums[i] <= pivot) i++;
            while (j > lo && nums[j] >= pivot) j--;

            if (i >= j) break;

            swap(nums, i, j);
        }
        
        swap(nums, lo, j);
        return j;
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}