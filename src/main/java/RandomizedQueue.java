import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;




    /** construct an empty randomized queue.
     *
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    private void resize() {

        int capacity = 0;
        if (n == a.length) {
            capacity = a.length*2;
        } else if (n == a.length/4) {
            capacity = a.length/2;
        }

        if (capacity != 0) {
            a = copyOf(a, n, capacity);
        }
    }

    private Item[] copyOf(Item[] a, int n, int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        return temp;

    }



    /** is the queue empty?
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }
    /** return the number of items on the queue.
     * @return
     */
    public int size() {
        return n;
    }
    /** add the item.
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        resize();

        if (n != 0) {
            int other = StdRandom.uniform(n);
            a[n] = a[other];
            a[other] = item;
        } else {
            a[n] = item;
        }
        n++;
    }


    /** remove and return a random item.
     * @return
     */
    public Item dequeue() {

        if (n > 1) {
            int idx = StdRandom.uniform(0, n - 1);
            swap(a, n - 1, idx);
        }

        Item item = a[n-1];
        a[n-1] = null;
        n--;
        resize();
        return item;
    }

    private void swap(Item[] arr, int i, int idx) {
        Item tmp = arr[i];
        arr[i] = arr[idx];
        arr[idx] = tmp;
    }

    /** return (but do not remove) a random item.
     * @return
     */
    public Item sample() {
        int idx = (n == 1 ? 0 : StdRandom.uniform(0, n-1));
        return a[idx];
    }
    /** return an independent iterator over items in random order.
     * @return
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] rand;
        private int index = 0;

        public RandomizedQueueIterator() {
            rand = copyOf(a, n, n);
            StdRandom.shuffle(rand);
        }

        @Override
        public boolean hasNext() {
            return index < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return rand[index++];
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        assert queue.isEmpty();
        queue.enqueue(1);
        assert queue.size() == 1;
        assert queue.dequeue() == 1;
        assert queue.size() == 0;

        assert queue.isEmpty();
        queue.enqueue(2);
        assert queue.size() == 1;
        assert queue.dequeue() == 2;
        assert queue.size() == 0;

        assert queue.isEmpty();
        for (int i = 0; i <= 5; i++) {
            queue.enqueue(i);
        }

        for (int i : queue) {
            StdOut.println("Sample: "+queue.sample());
            StdOut.println(String.format("%d ", i));
        }

        StdOut.println("Success");
    }

}
