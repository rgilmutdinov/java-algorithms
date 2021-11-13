class Solution {

	public int[] previousLess(int[] A) {
		// prevLess[i] = j means A[j] is the previous less element of A[i].
		// prevLess[i] = -1 means there is no previous less element of A[i].
		int[] prevLess = new int[A.length];
		Arrays.fill(prevLess, -1);

		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < A.length; i++) {
			while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
				stack.pop();
			}
			prevLess[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(i);
		}

		return prevLess;
	}

	public int[] nextLess(int[] A) {
		// nextLess[i] = j means A[j] is the next less element of A[i].
		// nextLess[i] = -1 means there is no next less element of A[i].
		int[] nextLess = new int[A.length];
		Arrays.fill(nextLess, -1);

		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < A.length; i++) {
			while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
				int x = stack.pop();
				nextLess[x] = i;
			}

			stack.push(i);
		}

		return nextLess;
	}
}