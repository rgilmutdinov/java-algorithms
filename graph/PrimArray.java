class Solution {
    private final int MAX = Integer.MAX_VALUE >> 1;
    
	// Time complexity: O(V^2)
    public int prim(List<int[]>[] adjList, int s) {
        int n = adjList.length;
        int[] distTo = new int[n];
		//int[] parent = new parent[n];

         Arrays.fill(distTo, MAX);
        //Arrays.fill(parent, -1);
        distTo[s] = 0;	

        boolean[] used = new boolean[n];

		int weight = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int v = -1;
            for (int u = 0; u < n; u++)
                if (!used[u] && (v == -1 || distTo[u] < distTo[v]))
                    v = u;

            if (distTo[v] == MAX)
                break;

            used[v] = true;
			weight += distTo[v];
            count++;
			
            for (int[] edge : adjList[v]) {
                int u = edge[0];
                int w = edge[1];
                if (distTo[u] > w) {
                    distTo[u] = w;
					//parent[u] = v;
                }
            }
        }

        return count != n ? -1 : weight;
    }
}