import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Randomized queue.
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int n;          // size of the randomized queue
    private Node pre;       // sentinel before first item
    private Node post;      // sentinel after last item

    // linked list node helper data type
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        pre = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
        n = 0;
    }

    /**
     * Is the queue empty?
     * @return true if the deque is empty and false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Return the number of items on the queue
     * @return the number of items on the queue
     */
    public int size() {
        return n;
    }

    /**
     * Add the item
     * @param item the item to be added to the queue
     */
    public void enqueue(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException(
                    "Item should not be null");
        Node last = post.prev;
        Node x = new Node();
        x.item = item;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        n++;
    }

    /**
     * Remove and return a random item
     * @return Item a random Item
     */
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Cannot dequeue from an empty queue");

        // StdOut.println("debug: n= " + n);
        int index = StdRandom.uniform(0, n);
        // StdOut.println("debug: removing index " + index);
        Node current = pre.next;
        int currentIndex = 0;
        while (current.item != null) {
            if (index == currentIndex) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                n--;
                return current.item;
            }
            currentIndex++;
            current = current.next;
        }
        System.out.println("Error!");
        return null;
    }

    /**
     * Return (but do not remove) a random item
     * @return Item a random item
     */
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Cannot dequeue from an empty queue");

        // StdOut.println("debug: n= " + n);
        int index = StdRandom.uniform(0, n);
        // StdOut.println("debug: removing index " + index);

        Node current = pre.next; // todo add backward going
        int currentIndex = 0;
        while (current.item != null) {
            if (index == currentIndex)
                return current.item;

            currentIndex++;
            current = current.next;
        }
        System.out.println("Error!");
        return null;
    }

    /**
     * Return an independent iterator over items in random order
     * @return Iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Item[] shuffled = shuffle();
            private int i = n;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public Item next() {
                if (!hasNext())
                    throw new java.util.NoSuchElementException(
                            "No more items");
                return shuffled[--i];
            }

            @Override
            public void remove() {
                throw new java.lang.UnsupportedOperationException(
                        "Unsupported method");
            }

            private Item[] shuffle() {
                Item[] items = (Item[]) new Object[n];
                Node current = pre.next;
                int index = 0;
                while (current.item != null) {
                    items[index] = current.item;
                    current = current.next;
                    index++;
                }

    /*            for (Item i : items)
                    StdOut.println("debug: item " + i);*/
                StdRandom.shuffle(items);
                return items;
            }

        };
    }

    /**
     * Unit testing (optional)
     * @param args
     */
    public static void main(String[] args) {
        StdOut.println("Compiled");

        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        StdOut.println("? empty " + rq.isEmpty());
        StdOut.println("? size " + rq.size());

        StdOut.println();
        rq.enqueue(1);
        StdOut.println("Engueued 1:");
        StdOut.println("? empty " + rq.isEmpty());
        StdOut.println("? size " + rq.size());

        StdOut.println("Iterator: ");
        for (Integer i: rq)
            StdOut.println(i);

        StdOut.println();
        StdOut.println("Now dequeue:");
        StdOut.println("dequeued " + rq.dequeue());
        StdOut.println("? empty " + rq.isEmpty());
        StdOut.println("? size " + rq.size());

        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);

        StdOut.println();
        StdOut.println("Enqueued 3");

        StdOut.println("Iterator: ");
        for (Integer i: rq)
            StdOut.println(i);
    }
}
