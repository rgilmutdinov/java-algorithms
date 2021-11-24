public static class RUCaches {
	public interface Cache<K, V> 
	{
		public V get(K key);
		public void set(K key, V value);  
		public boolean isFull();  
		public int size();
	}
	
	public abstract class AbstractRUCache<K, V> implements Cache<K, V>
	{
		static class ListNode<K, V> {
			public final K key;
			public V value;

			public ListNode<K, V> next;
			public ListNode<K, V> prev;

			public ListNode(K key, V value) {
				this.key = key;
				this.value = value;
			}

			@Override
			public String toString() {
				return String.format("[%s: %s]", key, value);
			}
		}

		protected int capacity;
		protected HashMap<K, ListNode<K, V>> map;
		protected ListNode<K, V> head;
		protected ListNode<K, V> tail;

		public AbstractRUCache(int capacity) {
			this.capacity = capacity;
			this.map = new HashMap<>();
			
			head = new ListNode<K, V>(null, null);
			tail = new ListNode<K, V>(null, null);

			head.next = tail;
			tail.prev = head;
		}
		
		public V get(K key) {       
			ListNode<K, V> node = map.get(key);
			if (node == null) {
				return null;
			}
			
			detach(node);
			putLastAccessed(node);
			
			return node.value;        
		}

		public void set(K key, V value) {
			ListNode<K, V> node = map.get(key);
			if (node != null) {
				node.value = value;

				detach(node);
				putLastAccessed(node);
			} else {
				boolean full = isFull();
				node = new ListNode<>(key, value);
				map.put(key, node);
				
				putLastAccessed(node);
				if (full) {                
					evict();                
				}
			}
		}

		public int size() {
			return map.size();
		}

		public boolean isFull() {
			return capacity == map.size();
		}

		private ListNode<K, V> popLast() {            
			ListNode<K, V> last = tail.prev;
			detach(last);
			return last;    
		}

		protected void evict() {
			ListNode<K, V> last = popLast();
			
			System.out.println(this);
			map.remove(last.key);
		}

		protected abstract void putLastAccessed(ListNode<K, V> node);

		protected void insertAfter(ListNode<K, V> front, ListNode<K, V> node) {
			node.next = front.next;
			node.prev = front;
			
			front.next.prev = node;
			front.next = node;      
		}

		protected void detach(ListNode<K, V> node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			
			node.prev = null;
			node.next = null;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			ListNode<K, V> curr = head.next;
			while (curr != tail) {
				sb.append(curr);
				curr = curr.next;
			}
			return sb.toString();
		}
	}
	
	public class LRUCache<K, V> extends AbstractRUCache<K, V>
	{
		public LRUCache(int capacity) {
			super(capacity);
		}

		@Override
		protected void putLastAccessed(ListNode<K, V> node) {
			insertAfter(head, node);
		}
	}
	
	public class MRUCache<K, V> extends AbstractRUCache<K, V>
	{
		public MRUCache(int capacity) {
			super(capacity);
		}

		@Override
		protected void putLastAccessed(ListNode<K, V> node) {
			insertAfter(tail.prev, node);
		}
	}
}