class Solution {            
    public List<Integer> getEulerCircuit(List<Integer>[] adjList, int v) {
        int n = adjList.length;
        
        List<Integer> circuit = new ArrayList<>();
        Deque<Integer> currPath = new ArrayDeque<>();
        
        currPath.push(v); // euler path starts from v
            
        while (!currPath.isEmpty()){
            int u = currPath.peekLast();

            if (adjList[u].size() == 0) {
                // if all edges from u are visited
                // pop u and push it to euler path    
                circuit.add(u);
                currPath.removeLast();
            } else {
                // if all edges from u are not visited
                // select any edge (u, v)
                // push v to current path and remove edge (u, v) from the graph                
                int last = adjList[u].size() - 1;
                currPath.addLast(adjList[u].get(last));
                adjList[u].remove(last);
            }    
        }
        
        return circuit;
    }
}