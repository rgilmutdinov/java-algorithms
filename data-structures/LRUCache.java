public class LRUCache {
    class ListNode {
        public ListNode prev;
        public ListNode next;
        public int val;
        public int key;

        public ListNode(int key, int val) {
            this(key, val, null, null);
        }

        public ListNode(int key, int val, ListNode prev, ListNode next) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private int capacity;
    private ListNode head;
    private ListNode tail;
    private HashMap<Integer, ListNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);

        head.next = tail;
        tail.prev = head;

        map = new HashMap<>();
    }

    public int get(int key) {
        ListNode node = map.get(key);
        if (node != null) {
            moveToFront(node);
            return node.val;
        }

        return -1;
    }

    public void put(int key, int value) {
        ListNode node = map.get(key);
        if (node != null) {
            node.val = value;
            moveToFront(node);
        } else {
            node = new ListNode(key, value);
            map.put(key, node);
            insertAfter(head, node);

            if (map.size() > capacity) {
                map.remove(popTail().key);
            }
        }
    }

    private void moveToFront(ListNode node) {
        detach(node);
        insertAfter(head, node);
    }

    private ListNode popTail() {
        ListNode last = tail.prev;
        detach(last);
        return last;
    }

    private void insertAfter(ListNode front, ListNode node) {
        node.next = front.next;
        node.prev = front;

        front.next.prev = node;
        front.next = node;
    }

    private void detach(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
    }
}