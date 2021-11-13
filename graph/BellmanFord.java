class Solution {
    private final int MAX = 1_000_000;

    // n - number of vertices
    // edges[v][u] - weight of edge v->u
    // s - starting vertex
    public int[] bellman(int n, int[][] edges, int s) {
        int dist[] = new int[n];
        Arrays.fill(dist, MAX);

        dist[s] = 0;
        for (int k = 1; k < n; k++) {
            for (int[] e : edges) {
                int u = e[0];
                int v = e[1];
                int weight = e[2];

                if (dist[u] > dist[v] + weight) {
                    dist[u] = dist[v] + weight;
                }

                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        return dist;
    }
}