public class Kasuraju {
    private boolean[] marked;
    private int[] id;
    private int count;

    public Kasuraju(List<Integer>[] adjList) {
        int n = adjList.length;
        marked = new boolean[n];
        id = new int[n];

        List<Integer>[] revList = reverseGraph(adjList);
        for (int s : reversePostorder(revList)) {
            if (!marked[s]) {
                dfs(adjList, s);
                count++;
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    private List<Integer>[] reverseGraph(List<Integer>[] adjList) {
        int n = adjList.length;
        List<Integer>[] revList = new List[n];
        for (int i = 0; i < adjList.length; i++) {
            revList[i] = new ArrayList<>();
        }

        for (int v = 0; v < n; v++) {
            for (int w : adjList[v]) {
                revList[w].add(v);
            }
        }
        return revList;
    }

    private void dfs(List<Integer>[] adjList, int v) {
        marked[v] = true;
        id[v] = count;

        for (int w : adjList[v]) {
            if (!marked[w]) {
                dfs(adjList, w);
            }
        }
    }

    private Iterable<Integer> reversePostorder(List<Integer>[] adjList) {
        int n = adjList.length;
        boolean[] visited = new boolean[n];
        Deque<Integer> reversePost = new ArrayDeque<>();
        for (int v = 0; v < n; v++)
            if (!visited[v]) {
                traverse(adjList, reversePost, visited, v);
            }

        return reversePost;
    }

    private void traverse(List<Integer>[] adjList, Deque<Integer> reversePost, boolean[] visited, int v) {
        visited[v] = true;
        for (int w : adjList[v]) {
            if (!visited[w]) {
                traverse(adjList, reversePost, visited, w);
            }
        }
        reversePost.push(v);
    }
}