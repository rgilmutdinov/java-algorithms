public class Tarjan {                
	private int[] disc;  // disc[v] = discovered time of v
	private int[] low;   // low[v] = low number of v        
	private List<int[]> bridges; // bridges
	
	private int time;        
	public Tarjan(List<Integer>[] adjList) {
		int n = adjList.length;
					
		disc = new int[n];
		low = new int[n];
		time = 1;
		
		bridges = new ArrayList<>();
		for (int v = 0; v < n; v++) { 
			if (disc[v] == 0) {
				dfs(adjList, v, -1);
			}
		}
	}
	
	public List<int[]> bridges() {
		return bridges;
	}
	
	public void dfs(List<Integer>[] adjList, int v, int parent) {
		int n = adjList.length;
		
		int currTime = time;
		low[v] = disc[v] = time++;
		
		for (int u : adjList[v]) {
			if (u == parent) continue;
			if (disc[u] == 0) {
				dfs(adjList, u, v);                    
			}
			
			low[v] = Math.min(low[v], low[u]);
			if (currTime < low[u]) {
				bridges.add(new int[] { v, u });
			}
		}
	}
}