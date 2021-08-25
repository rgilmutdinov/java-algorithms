import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre; // vertices in preorder
    private Queue<Integer> post; // vertices in postorder
    private Deque<Integer> reversePost; // vertices in reverse postorder

    public DepthFirstOrder(List<Integer>[] adjList) {
        int n = adjList.length;
        pre = new ArrayDeque<>();
        post = new ArrayDeque<>();
        reversePost = new ArrayDeque<>();
        marked = new boolean[n];
        for (int v = 0; v < n; v++)
            if (!marked[v]) {
                dfs(adjList, v);
            }
    }

    private void dfs(List<Integer>[] adjList, int v) {
        pre.offer(v);
        marked[v] = true;
        for (int w : adjList[v])
            if (!marked[w])
                dfs(adjList, w);
        post.offer(v);
        reversePost.addFirst(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}