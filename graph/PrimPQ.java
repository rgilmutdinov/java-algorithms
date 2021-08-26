class Solution {
    private final int MAX = Integer.MAX_VALUE >> 1;
    
	// Time complexity: O((V + E) * log V)
    public int prim(List<int[]>[] adjList, int s) {
        int n = adjList.length;
        int[] distTo = new int[n];
		//int parent[] = new parent[n];
		
        Arrays.fill(distTo, MAX);
        //Arrays.fill(parent, -1);
        distTo[s] = 0;		

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((e1, e2) -> e1[1] - e2[1]);
        pq.offer(new int[] { s, 0 });
        boolean[] visited = new boolean[n];
        
        int weight = 0;
        int count = 0;
        while (!pq.isEmpty()) {            
            int v = pq.poll()[0];
            if (visited[v]) continue;
            
            visited[v] = true;
            weight += distTo[v];
            count++;
            
            for (int[] edge : adjList[v]) {
                int u = edge[0];
                int w = edge[1];
                if (distTo[u] > w) {
                    distTo[u] = w;
					//parent[u] = v;
                    pq.add(new int[] { u, distTo[u] });
                }
            }
        }
        
        return count != n ? -1 : weight;
    }
}