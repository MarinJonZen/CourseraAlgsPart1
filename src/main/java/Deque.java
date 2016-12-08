import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by jmarin on 12/6/16.
 */
public class Deque<Item> implements Iterable<Item> {
    private class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;
    }

    private Node<Item> first;
    private Node<Item> last;
    private int count;

    public Deque() {
        count = 0;
    }
    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else if (first == null) {
            first = new Node<Item>();
            first.item = item;
            last = first;
            count += 1;
        } else {
            Node<Item> tmp = new Node<Item>();
            tmp.item = item;
            tmp.next = first;

            first.prev = tmp;
            first = tmp;
            count += 1;
        }

    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else if (last == null) {
            addFirst(item);
        } else {
            Node<Item> tmp = new Node<Item>();
            tmp.item = item;
            tmp.prev = last;

            last.next = tmp;
            last = tmp;
            count +=  1;
        }
    }
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Node<Item> tmp = first;
            first = tmp.next;
            tmp.next = null;
            count -= 1;

            if (tmp == last) {
                last = null;
            } else {
                first.prev = null;
            }

            return tmp.item;
        }
    }
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Node<Item> tmp = last;
            last = tmp.prev;
            tmp.prev = null;
            count -= 1;

            if (tmp == first) {
                first = null;
            } else {
                last.next = null;
            }

            return tmp.item;
        }
    }
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> curr = first;
            @Override
            public boolean hasNext() {
                return curr != null;
            }
            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Item nextItem = curr.item;
                    curr = curr.next;
                    return nextItem;
                }
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {

        int [] nums = {1, 2, 3, 4, 5, 6, 7, 9};

        Deque<Integer> deq = new Deque<>();
        deq.addFirst(1);
        deq.addLast(10);
        deq.removeFirst();
        deq.removeLast();
        deq.addFirst(1);
        deq.addLast(10);
        deq.removeFirst();
        deq.removeLast();
        StdOut.println("Test..");

        testIterator(nums);
        testFirstFirst(nums);
        testFirstLast(nums);
        testLastFirst(nums);
        testLastLast(nums);

    }

    private static void testLastLast(int[] nums) {
        StdOut.println("Using Last/Last..");
        Deque<Integer> deq = new Deque<Integer>();
        Arrays.stream(nums).forEach(n -> deq.addLast(n));

        while (!deq.isEmpty()) {
            StdOut.println(deq.removeLast());
        }
    }

    private static void testLastFirst(int[] nums) {
        StdOut.println("Using Last/First..");
        Deque<Integer> deq = new Deque<Integer>();
        Arrays.stream(nums).forEach(n -> deq.addLast(n));

        while (!deq.isEmpty()) {
            StdOut.println(deq.removeFirst());
        }
    }

    private static void testFirstLast(int[] nums) {
        StdOut.println("Using First/Last..");
        Deque<Integer> deq = new Deque<Integer>();
        Arrays.stream(nums).forEach(n -> deq.addFirst(n));

        while (!deq.isEmpty()) {
            StdOut.println(deq.removeLast());
        }
    }

    private static void testFirstFirst(int[] nums) {
        StdOut.println("Using First/First..");
        Deque<Integer> deq = new Deque<Integer>();
        Arrays.stream(nums).forEach(n -> deq.addFirst(n));

        while (!deq.isEmpty()) {
            StdOut.println(deq.removeFirst());
        }
    }

    private static void testIterator(int[] nums) {
        StdOut.println("Using Iterator..");
        Deque<Integer> deq = new Deque<Integer>();
        Arrays.stream(nums).forEach(n -> deq.addFirst(n));


        for (Integer item : deq) {
            StdOut.println(item);
        }
    }
}
