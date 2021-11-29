class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> PQ = new PriorityQueue<>();
        for (int n : nums) {
            PQ.offer(n);
            if (PQ.size() > k) {
                PQ.poll();
            }
        }
        
        return PQ.peek();
    }
}