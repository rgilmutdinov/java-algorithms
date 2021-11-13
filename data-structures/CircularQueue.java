public class MyCircularQueue {
    private int[] data;
    private int front;
    private int capacity;
    private int count;

    public MyCircularQueue(int k) {
        data = new int[k];
        capacity = k;
        count = 0;
        front = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        data[(front + count) % capacity] = value;
        count++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % capacity;
        count--;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }

        return data[front];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }

        return data[(front + count - 1) % capacity];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }
}