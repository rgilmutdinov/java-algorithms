class Solution {
    private final int MAX = 1_000_000;
	
	/*
		graph[u][v] is the weight of the edge from vertex u to vertex v
		graph[u][v] = MAX if graph doesn't have edge u->v
	*/
    public int[][] floydWarshall(int[][] graph) {
        int n = graph.length;
        int dist[][] = new int[n][n];        
        
        for (int v = 0; v < n; v++) {
            for (int u = 0; u < n; u++) {
                dist[v][u] = graph[v][u];
            }
        }

        for (int w = 0; w < n; w++) {
            for (int v = 0; v < n; v++) {
                if (dist[w][v] == MAX) {
                    continue;
                }
                for (int u = 0; u < n; u++) {
                    dist[v][u] = Math.min(dist[v][u], dist[v][w] + dist[w][u]);                    
                }
				
				// check for negative cycle
                // if (dist[v][v] < 0) {
                //    hasNegativeCycle = true;
                //    return;
                //}
            }
        }
        
        return dist;
    }
}