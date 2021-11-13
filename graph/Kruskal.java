class Solution {
    public class UF {

        private int[] parent; // parent[i] = parent of i
        private byte[] rank;  // rank[i] = rank of subtree rooted at i (never more than 31)
        private int count;    // number of components

        public UF(int n) {
            if (n < 0)
                throw new IllegalArgumentException();
            count = n;
            parent = new int[n];
            rank = new byte[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]]; // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public int count() {
            return count;
        }

        @Deprecated
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public boolean union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return false;

            // make root of smaller rank point to root of larger rank
            if (rank[rootP] < rank[rootQ])
                parent[rootP] = rootQ;
            else if (rank[rootP] > rank[rootQ])
                parent[rootQ] = rootP;
            else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
            return true;
        }
    }

    // Time complexity: O(E * log E + (V + E) * alpha(V))
    // Sorts edges by weight
    public class Kruskal {
        private int weight = 0;
        private int count = 0;

        // Find MST from the list of edges
        // edge - [u, v, weight]
        public Kruskal(int n, int[][] edges) {
            Arrays.sort(edges, (e1, e2) -> Integer.compare(e1[2], e2[2]));

            UF uf = new UF(n);
            for (int[] edge : edges) {
                if (uf.union(edge[0], edge[1])) {
                    weight += edge[2];
                }
            }

            count = uf.count();
        }

        public int weight() {
            return weight;
        }

        private boolean hasMst() {
            return count == 1;
        }
    }

    // Time complexity: O(E * log E + (V + E) * alpha(V))
    // Uses priority queue
    public class Kruskal2 {
        private int weight = 0;
        private int count = 0;
        private List<int[]> mst;

        // Find MST from the list of edges
        // edge - [u, v, weight]
        public Kruskal(int n, int[][] edges) {
            mst = new ArrayList<>();

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
            for (int[] edge : edges) {
                pq.add(edge);
            }

            UF uf = new UF(n);
            while (!pq.isEmpty() && mst.size() < n - 1) {
                int[] edge = pq.poll();
                if (uf.union(edge[0], edge[1])) {
                    weight += edge[2];
                    mst.add(edge);
                }
            }

            count = uf.count();
        }

        public int weight() {
            return weight;
        }

        private boolean hasMst() {
            return count == 1;
        }

        private List<int[]> mst() {
            return mst;
        }
    }
}