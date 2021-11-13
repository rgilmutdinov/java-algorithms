class Solution {
    private final int MAX = 1_000_000;

    // n - number of vertices
    // edges[v][u] - weight of edge v->u (u->v)
    // s - starting vertex
    public int[] bellman(int n, int[][] edges, int s) {
        int dist[] = new int[n];
        Arrays.fill(dist, MAX);

        dist[s] = 0;
        for (int k = 1; k < n; k++) {
            boolean any = false;
            for (int[] e : edges) {
                int u = e[0];
                int v = e[1];
                int weight = e[2];

                if (dist[u] > dist[v] + weight) {
                    dist[u] = dist[v] + weight;
                    any = true;
                }

                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    any = true;
                }
            }
            if (!any)
                break;
        }

        return dist;
    }

    // This implementation allows you to check whether there exists a cycle of
    // negative weight in the graph
    public int[] bellmanEx(int n, int[][] edges, int s) {
        int[] dist = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dist, MAX);
        Arrays.fill(parent, -1);

        dist[s] = 0;
        int x = -1;
        for (int k = 1; k < n; k++) {
            boolean any = false;
            x = -1;
            for (int[] e : edges) {
                int u = e[0];
                int v = e[1];
                int weight = e[2];

                if (dist[u] > dist[v] + weight) {
                    dist[u] = dist[v] + weight;
                    x = v;
                    any = true;
                }
            }
            if (!any)
                break;
        }

        if (x == -1) {
            System.out.println("No negative cycle found.");
        } else {
            for (int i = 0; i < n; ++i)
                x = parent[x];

            LinkedList<Integer> cycle = new LinkedList<>();
            for (int v = x;; v = parent[v]) {
                cycle.addFirst(v);
                if (v == x && cycle.size() > 1)
                    break;
            }
        }

        return dist;
    }
}