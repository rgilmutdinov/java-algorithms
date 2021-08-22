class Solution {
    private final int MAX = 1_000_000;
    
	// Useful for not sparse graphs (E << V^2)
	// Time complexity: O((V + E) * logV)
	// adjList: v -> [(u_j, weight_j),... , (u_k, weight_k)]
    public int[] dijkstra(List<int[]>[] adjList, int s) {
        int n = adjList.length;
        int distTo[] = new int[n];
		//int[] parent = new parent[n];
		
        for (int v = 0; v < n; v++)
            distTo[v] = MAX;
        distTo[s] = 0;
		//parent[s] = -1;

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((e1, e2) -> e1[1] - e2[1]);
        pq.offer(new int[] { s, 0 });

        while (!pq.isEmpty()) {            
            int v = pq.poll()[0];
            
            for (int[] edge : adjList[v]) {
                int u = edge[0];
                int w = edge[1];
                if (distTo[u] > distTo[v] + w) {
                    distTo[u] = distTo[v] + w;
					//parent[u] = v;
                    pq.add(new int[] { u, distTo[u] });
                }
            }
        }
        
        return distTo;
    }
}