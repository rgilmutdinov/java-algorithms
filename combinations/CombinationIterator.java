class CombinationIterator1 {
    private Deque<String> combinations;
    public CombinationIterator(String characters, int combinationLength) {
        int n = characters.length();
        int k = combinationLength;
        combinations = new ArrayDeque<>();
        
        int[] nums = new int[k + 1];
        for (int i = 0; i < k; i++) {
            nums[i] = i;
        }
        
        nums[k] = n;
        
        int j = 0;
        while (j < k) {
            // add current combination
            StringBuilder sb = new StringBuilder();
            for (int i = k - 1; i >= 0; i--) {
                sb.append(characters.charAt(n - nums[i] - 1));
            }
            combinations.push(sb.toString());
            
            // generate next combination.
            // Find the first j such that nums[j] + 1 != nums[j + 1].
            // Increase nums[j] by one.
            j = 0;
            while ((j < k) && (nums[j + 1] == nums[j] + 1)) {
                nums[j] = j;
                j++;
            }
            nums[j]++;
        }
    }
    
    public String next() {
        return combinations.pop();
    }
    
    public boolean hasNext() {
        return !combinations.isEmpty();
    }
}

class CombinationIterator2 {
    private int[] nums;
    private boolean hasNext;
    private int n, k;
    private String chars;

    public CombinationIterator(String characters, int combinationLength) {
        n = characters.length();
        k = combinationLength;
        chars = characters;

        // init the first combination
        hasNext = true;
        nums = new int[k];
        for (int i = 0; i < k; ++i) {
            nums[i] = i;
        }
    }

    public String next() {
        StringBuilder curr = new StringBuilder();
        for (int j: nums) {
            curr.append(chars.charAt(j));
        }

        // Generate next combination.
        // Find the first j such that nums[j] != n - k + j.
        // Increase nums[j] by one.
        int j = k - 1;
        while (j >= 0 && nums[j] == n - k + j) {
            j--;
        }

        if (j >= 0) {
            nums[j]++;
            for (int i = j + 1; i < k; i++) {
                nums[i] = nums[j] + i - j;
            }
        } else {
            hasNext = false;
        }

        return curr.toString();
    }

    public boolean hasNext() {
        return hasNext;
    }
}