class Solution {
    private final int MAX = 1_000_000;

    // Useful for not sparse graphs (E << V^2)
    // Time complexity: O((V + E) * logV)
    // adjList: v -> [(u_j, weight_j),... , (u_k, weight_k)]
    public int[] dijkstra(List<int[]>[] adjList, int s) {
        int n = adjList.length;
        int[] distTo = new int[n];
        // int[] parent = new parent[n];

        Arrays.fill(distTo, MAX);
        distTo[s] = 0;

        // Arrays.fill(parent, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((e1, e2) -> e1[1] - e2[1]);
        pq.add(new int[] { s, 0 });

        while (!pq.isEmpty()) {
            int v = pq.poll()[0];

            for (int[] edge : adjList[v]) {
                int u = edge[0];
                int w = edge[1];
                if (distTo[u] > distTo[v] + w) {
                    distTo[u] = distTo[v] + w;
                    // parent[u] = v;
                    pq.add(new int[] { u, distTo[u] });
                }
            }
        }

        return distTo;
    }

    public int dijkstra(List<int[]>[] adjList, int s, int d) {
        if (s == d)
            return 0;

        int n = adjList.length;
        int[] distTo = new int[n];
        // int[] parent = new parent[n];

        Arrays.fill(distTo, MAX);
        distTo[s] = 0;

        // Arrays.fill(parent, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((e1, e2) -> e1[1] - e2[1]);
        pq.add(new int[] { s, 0 });

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int v = p[0];
            if (v == d)
                return p[1];
            for (int[] edge : adjList[v]) {
                int u = edge[0];
                int w = edge[1];
                if (distTo[u] > distTo[v] + w) {
                    distTo[u] = distTo[v] + w;
                    // parent[u] = v;
                    pq.add(new int[] { u, distTo[u] });
                }
            }
        }

        return distTo[d];
    }
}