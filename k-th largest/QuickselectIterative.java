class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int pos = quickselect(nums, k, 0, n - 1);
        return nums[pos];
    }
    
    private int quickselect(int[] nums, int k, int lo, int hi) {        
        while (lo < hi) {            
            int j = partition(nums, lo, hi);        
            int top = nums.length - j;

            if (top == k) return j;        

            if (top < k) {
                hi = j - 1;
            } else {
                lo = j + 1;
            }
        }   
        
        return lo;
    }
    
    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[hi];
        int i = lo;
        int j = hi - 1;   
        
        while (true) {
            while (j > lo && nums[j] >= pivot) j--;
            while (i < hi && nums[i] <= pivot) i++;            

            if (i >= j) break;

            swap(nums, i, j);
        }
        
        swap(nums, hi, i);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}