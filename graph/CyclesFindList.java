class Solution {
   
    private final int WHITE = 0;
    private final int GRAY = 1;
    private final int BLACK = 2;
        
    private List<List<Integer>> cycles = new ArrayList<>();
    private void findCycles(List<Integer>[] adjList, int u, int p, int[] color, int[] parents) { 
        // already (completely) visited vertex.
        if (color[u] == BLACK) {
            return;
        }
 
        // seen vertex, but was not completely visited -> cycle detected.
        // backtrack based on parents to find the complete cycle.
        if (color[u] == GRAY) { 
            LinkedList<Integer> cycle = new LinkedList<>();
            
            int cur = p;
            cycle.addFirst(cur);
            // backtrack the vertex which are
            // in the current cycle thats found
            while (cur != u) {
                cur = parents[cur];
                cycle.addFirst(cur);
            }
            cycles.add(cycle);
            return;
        }
        
        parents[u] = p;
 
        // partially visited.
        color[u] = GRAY;
 
        // simple dfs on graph
        for (int v : adjList[u]) { 
            // if it has not been visited previously
            if (v == parents[u]) {
                continue;
            }
            
            findCycles(adjList, v, u, color, parents);
        }
 
        // completely visited.
        color[u] = BLACK;
    }
    
    private void printCycles() {
        // print all the vertex with same cycle
        int i = 1;
        for (List<Integer> cycle : cycles) {
            // Print the i-th cycle
            System.out.printf("Cycle Number %d: ", i++);
            for (int v : cycle)
                System.out.printf("%d ", v);
            System.out.println();
        }
    }
    
    public void findCycles(List<Integer>[] adjList) {
        int n = adjList.length;
        int[] color = new int[n];        
        int[] parents = new int[n];
        cycles = new ArrayList<>();
        findCycles(adjList, 0, -1, color, parents);
        printCycles();
    }
}