class Solution {
    public int findLeftmost(int[] nums, int target, int lo, int hi) {
        if (lo > hi) return -1;

        hi++;
        int end = hi;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        if (lo != end && nums[lo] == target) {
            return lo;
        }

        return -1;
    }

    public int findRightmost(int[] nums, int target, int lo, int hi) {
        if (lo > hi) return -1;

        hi++;
        int end = hi;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (target < nums[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        if (lo != end && nums[lo] == target) {
            return lo;
        }

        return -1;
    }

    public int bisectLeft(int[] nums, int target, int lo, int hi) {
        hi++;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    public int bisectRight(int[] nums, int target, int lo, int hi) {
        hi++;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (target < nums[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}