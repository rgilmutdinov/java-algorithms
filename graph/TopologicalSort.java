class Solution {
   
    private final int WHITE = 0;
    private final int GRAY = 1;
    private final int BLACK = 2;
    
    public boolean hasCycle(List<Integer>[] adjList, int[] colors, int v, LinkedList<Integer> order) {
        colors[v] = GRAY;
        for (int u : adjList[v]) {
            if (colors[u] == GRAY) {
                return true;
            }
            
            if (colors[u] == WHITE && hasCycle(adjList, colors, u, order)) {
                return true;
            }
        }
        
        colors[v] = BLACK;
        order.addFirst(v);
        return false;
    }
	
	public List<Integer> topsortDfs(List<Integer>[] adjList) {
		int n = adjList.length;
				
		LinkedList<Integer> toporder = new LinkedList<>();
        int[] colors = new int[n];
        Arrays.fill(colors, WHITE);
        for (int v = 0; v < n; v++) {
            if (colors[v] != WHITE) continue;
            if (hasCycle(adjList, colors, v, toporder)) {
                return new int[0];
            }
        }
		
		return toporder;
	}
	
	public int[] topsortIndegree(List<Integer>[] adjList) {
        int n = adjList.length;
        
        int[] indegree = new int[n];
        for (int v = 0; v < n; v++) {
            for (int u : adjList[v]) {
                indegree[u]++;
            }
        } 
        
        int[] order = new int[n];
        int k = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int v = 0; v < n; v++) {
            if (indegree[v] == 0) {
                queue.add(v);
            }
        }
        
        while (!queue.isEmpty()) {
            int v = queue.poll();
            order[k++] = v;
            for (int u : adjList[v]) {
                indegree[u]--;
                if (indegree[u] == 0) {
                    queue.add(u);
                }
            }
        }
        
        if (k == n) {
            return order;
        }
        
        return new int[0];
    }
}