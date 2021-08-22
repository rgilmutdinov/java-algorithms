class Solution {
    private final int MAX = 1_000_000;
    
	// Useful for dense graphs (E ~ V^2)
	// Time complexity: O(V^2 + E)
	// adjList: v -> [(u_j, weight_j),... , (u_k, weight_k)]
    public int[] dijkstra(List<int[]>[] adjList, int s) {
        int n = adjList.length;
        int distTo[] = new int[n];
		//int[] parent = new parent[n];
		
        for (int v = 0; v < n; v++)
            distTo[v] = MAX;
        distTo[s] = 0;
		//parent[s] = -1;
		
        boolean[] used = new boolean[n];

        for (int i = 0; i < n; i++) {
            int v = -1;
            for (int u = 0; u < n; u++)
                if (!used[u] && (v == -1 || distTo[u] < distTo[v]))
                    v = u;
            
            if (distTo[v] == MAX)
                break;
            
            used[v] = true;

            for (int[] edge : adjList[v]) {
                int u = edge[0];
                int weight = edge[1];				
                if (distTo[u] > distTo[v] + weight) {
                    distTo[u] = distTo[v] + weight;
					//parent[u] = v;
				}
            } 
        }
        
        return distTo;
    }
}